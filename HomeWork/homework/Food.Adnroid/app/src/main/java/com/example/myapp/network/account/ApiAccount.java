package com.example.myapp.network.account;

import com.example.myapp.network.account.dto.LoginDto;
import com.example.myapp.network.account.dto.LoginResultDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiAccount {
    @POST("/api/account/login")
    public Call<LoginResultDto> login(@Body LoginDto loginDto);
}
