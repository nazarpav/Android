package com.example.myapp.network.profile;

import com.example.myapp.network.image.ChangeImage;
import com.example.myapp.network.profile.dto.ProfileResultDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiWebRequest {
    @GET("/api/user/profile")
    public Call<ProfileResultDTO> profile();
    @POST("/api/user/change-image")
    public Call<Void> changeImage(@Body ChangeImage uploadImageDto);
    @GET("/api/user/users")
    public Call<List<ProfileResultDTO>> users();
}
