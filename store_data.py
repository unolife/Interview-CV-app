from sqlalchemy import create_engine, Column, Integer, String, Sequence
from sqlalchemy.engine.url import URL
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker
from bs4 import BeautifulSoup
from datetime import datetime
from operator import itemgetter
import rds_config # 해당 파일에 db name, name, password를 저장
import urllib.request as req
import sys
import logging

import json
import rds_config
import pymysql


# rds 세팅
rds_host = rds_config.db_host
name = rds_config.db_username
password = rds_config.db_password
db_name = rds_config.db_name

logger = logging.getLogger()
logger.setLevel(logging.INFO)

try:
# db 연결
    db_url = {
    'database': name,
    'drivername': 'mysql+pymysql',
    'username': db_name,
    'password': password,
    'host': rds_host,
    'query': {'charset': 'utf8'},  
    }
    engine = create_engine(URL(**db_url), encoding="utf8")
    conn = engine.connect()
except:
    logger.error("ERROR: Unexpected error: Could not connect to MySQL instance.")
    sys.exit()

logger.info("SUCCESS: Connection to RDS MySQL instance succeeded")


def lambda_handler(event, context):

    # 사이트 스크래핑
    url = "http://www.saramin.co.kr/zf_user/jobs/major-public/list?sort=ed&quick_apply=y&search_day=&keyword=&mj_cd%5B%5D=3#listTop"
    res = req.urlopen(url)
    soup = BeautifulSoup(res, "html.parser")
    tbody = soup.findAll('table')[0].findAll('tr')

    # 오늘, 내일 날짜 저장 - 크롤링 한 웹 페이지의 날짜 표시 형식에 맞게 변환 / "~ 04/05(월)" 
    year = str(datetime.today().year)
    month = datetime.today().month 
    if month < 10:
        month = "0" + str(month)
    else:
        str(month)
        
    day = datetime.today().day
    tomorrow_day = day + 1
    if day < 10:
        day = "0" + str(day)
    else:
        day = str(day)

    if tomorrow_day < 10:
        tomorrow_day = "0" + str(tomorrow_day)
    else:
        tomorrow_day = str(tomorrow_day)

    r = datetime.today().weekday()
    t = ["(월)","(화)","(수)","(목)","(금)","(토)","(일)"]
    date = t[r]
    tomorrow_date = t[r+1]

    today = "~ " + month+ "/" + day + date
    tomorrow = "~ " + month+ "/" + tomorrow_day + tomorrow_date

    # 스크래핑한 내용 전처리 및 리스트에 저장
    total_list = []
    for i in range(len(tbody)):
        #스크래핑 한 사이트에서 필요한 정보만 추출
        span1 = tbody[i].find_all("span")
        p1 = tbody[i].find_all("p")
        content = []
        sum1 = []
        
        # 날짜 변환 - 쿼리에서 filter하기 위해서 날짜를 2019-04-05의 형식으로 변환
        if(p1[-2].string == "오늘마감" or p1[-2].string == "16시 마감"):
            p1[-2].string = today
        elif(p1[-2].string == "내일마감"):
            p1[-2].string = tomorrow
    
        a = p1[-2].string.split('/')
        b = a[0].split()
        c = a[-1].split('(')

        p1[-2].string = year + "-" + b[-1] + "-" + c[0]

        # 리스트에 스크래핑 내용 저장
        for j in range(1,len(span1)):
            content.append(str(span1[j].string))
        for k in range(len(p1)-1):
            content.append(str(p1[k].string))
        # 인자 개수를 앞에 담고, 뒷 부분에 인자를 리스트로 담음 /  [11, [회사명, 공채제목, 직무1 ....]]
        sum1.append(len(content))
        sum1.append(content)
        total_list.append(sum1)

    # 인자 개수별로 나누기 - 스크래핑된 내용의 갯수가 불규칙 하기 때문에, 인자 개수별로 나눠서 처리
    ten = []
    eleven = []
    twelve = []
    thirteen = []
    fourteen = []

    for i in range(len(total_list)):            
        if(total_list[i][0] == 10):
            ten.append(total_list[i])
        elif(total_list[i][0] == 11):
            eleven.append(total_list[i])
        elif(total_list[i][0] == 12):
            twelve.append(total_list[i])
        elif(total_list[i][0] == 13):
            thirteen.append(total_list[i])
        else:
            fourteen.append(total_list[i])
    
    
    # ORM class 생성 - 좀 더 효율적인 방법을 찾지 못 해서 실습 pdf의 예제를 그대로 사용
    Base = declarative_base()
    class Job(Base):
        __tablename__ = 'jobs'
    
        id = Column(Integer, Sequence('job_id_seq'), primary_key=True)
        com_name = Column(String(40))
        job_title = Column(String(100))
        task1 = Column(String(100))
        task2 = Column(String(100))
        task3 = Column(String(100))
        applynow = Column(String(40))
        career_year = Column(String(40))
        edu = Column(String(40))
        part_time = Column(String(40))
        location = Column(String(40))
        salary = Column(String(40))
        deadline = Column(String(40))

        def __init__(self, a, b, c, d, e, f, g, h, i, j, k, l): # self 빼고 12개
            self.com_name = a
            self.job_title = b
            self.task1 = c
            self.task2 = d
            self.task3 = e
            self.applynow = f
            self.career_year = g
            self.edu = h
            self.part_time = i
            self.location = j
            self.salary = k
            self.deadline = l
            
        def __repr__(self):
            id = ""
            if self.id is not None:
                id = str(self.id)
            else:
                id = "None"
                
            return "<job('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')>"% (id, self.com_name, self.job_title, self.task1, self.task2, self.task3, self.applynow, self.career_year, self.edu, self.part_time, self.location, self.salary, self.deadline)

    # 세션 생성
    from sqlalchemy.orm import sessionmaker
    Session = sessionmaker(bind=engine)
    session = Session()

    today = year + "-" + month + "-" + day
    # 오늘 이후 데이터 삭제
    session.query(Job).filter(Job.deadline > today).delete()
    session.commit()
    # select * from Job where deadline < today - 오늘 날짜 이전 값들을 가져옴
    temp = session.query(Job).filter(Job.deadline <= today).all()

    # id값 설정을 위해 마지막 인덱스 저장
    if(len(temp) == 0):
        n = 0
    else:
        n = temp[-1].id + 1

    # 인자별로 전처리해서 객체 생성
    job_list = []

    def mkobj(list1, length, job_list):
        # 인자 개수별 리스트, 인자 길이, 결과를 담을 리스트를 전달
        for i in range(len(list1)):        
            # list1[i]의 값은 [11, [회사명, 공채제목, 직무1 ....]]와 같은 형태이기 때문에 [1]을 붙여서 내부 리스트를 변수에 담음
            each = list1[i][1]
            if length == 10:
                each.insert(3,"None")            
                each.insert(4,"None")  
                
            elif length == 11:
                if(each[1] == "None"):
                    del each[1]
                    each.insert(3,"None")            
                elif(each[2] == "new"):
                    del each[2]
                    each.insert(3,"None")            
                each.insert(4,"None")
            
            elif length == 12:
                if(each[1] == "관심기업 등록"):
                    del each[1:3]
                    each.insert(3,"None")            
                    each.insert(4,"None")
                
                elif(each[1] == "None"):
                    del each[1]
                    each.insert(4,"None")            
                
            elif length == 13:
                if(each[1] == "관심기업 등록"):
                    del each[1:3]
                    each.insert(4,"None")            
                
                elif(each[1] == "None"):
                    del each[1]
                    
            elif length == 14:
                if(each[1] == "관심기업 등록"):
                    del each[1:3]            
                elif(each[1] == "None"):
                    del each[1]
                
                if(each[2]=="new"):
                    del each[2]
            
            # 인자 개수별로 상이한 데이터를 객체 생성시 전달되는 인자들만 남게끔 전처리 후 job_list에 담음
            job_list.append(each)
            
        return job_list

    mkobj(ten, 10, job_list)
    mkobj(eleven, 11, job_list)
    mkobj(twelve, 12, job_list)
    mkobj(thirteen, 13, job_list)
    mkobj(fourteen, 14, job_list)

    # 날짜별로 정렬
    job_list.sort(key=itemgetter(11))
    
    # 객체 생성하고 id를 부여한 후에 obj_list에 저장
    obj_list = []
    for i in range(len(job_list)):   
        new = Job(
            job_list[i][0],
            job_list[i][1],
            job_list[i][2],
            job_list[i][3],
            job_list[i][4],
            job_list[i][5],
            job_list[i][6],
            job_list[i][7],
            job_list[i][8],
            job_list[i][9],
            job_list[i][10],
            job_list[i][11]
        )
        new.id = n + i
        obj_list.append(new)

    # add_all을 이용해 리스트 전체를 add하고 commit 하기
    session.add_all(obj_list)
    session.commit()

    # 연결 끊기
    conn.close()
    engine.dispose()
    
    return "success"
