package com.example.chatbot_application;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RetrofitApi {

    @GET
    Call<msgModel> getMessage(@Url String Uri);

}
