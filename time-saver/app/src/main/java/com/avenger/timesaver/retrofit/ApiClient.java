package com.avenger.timesaver.retrofit;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiClient {

    @POST("/notification/data")
    Call<Object> sendPushNotification(@Header("token") String token,
                                        @Header("title") String title,
                                        @Header("message") String message);
}
