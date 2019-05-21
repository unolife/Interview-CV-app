import sys
import json
import logging
import rds_config
import pymysql
from datetime import datetime

#rds settings
rds_host  = rds_config.db_host
name = rds_config.db_username
password = rds_config.db_password
db_name = rds_config.db_name


logger = logging.getLogger()
logger.setLevel(logging.INFO)

try:
    conn = pymysql.connect(rds_host, user=name, passwd=password, db=db_name, charset='utf8', connect_timeout=5)
    print("o")
except:
    print("x")
    logger.error("ERROR: Unexpected error: Could not connect to MySql instance.")
    sys.exit()

logger.info("SUCCESS: Connection to RDS mysql instance succeeded")
def handler():
    """
    This function fetches content from mysql RDS instance
    """

    item_count = 0
    result = []
    today = str(datetime.today())[:10]
    print(today)
    with conn.cursor() as cur:
        cur.execute("SELECT deadline, com_name, job_title, task1 FROM jobs WHERE deadline >= {} ORDER BY id LIMIT 10".format(today))

        for row in cur:
            item_count += 1
            logger.info(row)
            each = {"deadline":row[0], "com_name":row[1], "job_title":row[2], "task1":row[3]}
            result.append(each)

    dic = {i: result[i] for i in range(len(result))}
    print(dic)

    return {
        'statusCode': 200,
        'body': dic
    }

handler()