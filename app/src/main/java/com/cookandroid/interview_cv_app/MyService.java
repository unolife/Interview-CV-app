package com.cookandroid.interview_cv_app;



import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface MyService {
    @Headers({
            "Accept:application/json, charset=UTF-8",
            "Content-Type:application/json; charset=UTF-8"
    })
    @GET("prod_test/{method}")
    Call<ResponseBody> getData(@Path("method") String mName);
}
