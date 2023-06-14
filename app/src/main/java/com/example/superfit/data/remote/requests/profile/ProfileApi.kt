package com.example.superfit.data.remote.requests.profile

import com.example.superfit.data.remote.dto.BodyParamsDto
import com.example.superfit.data.remote.dto.PhotoInfoDto
import com.example.superfit.data.remote.dto.ProfileResponseDto
import com.example.superfit.data.remote.dto.SimpleMessageResponseDto
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface ProfileApi {

    @GET("/api/profile")
    suspend fun getProfile(): Response<ProfileResponseDto>

    @POST("/api/profile/params")
    suspend fun updateBodyParameters(@Body body: BodyParamsDto): Response<SimpleMessageResponseDto>

    @GET("/api/profile/params/history")
    suspend fun getHistory(): Response<List<BodyParamsDto>>

    @GET("/api/profile/photos")
    suspend fun getPhotos(): Response<List<PhotoInfoDto>>

    @Multipart
    @POST("/api/profile/photos")
    suspend fun uploadPhoto(@Part file: MultipartBody.Part): Response<PhotoInfoDto>

    @GET("/api/profile/photos/{id}")
    suspend fun downloadPhoto(@Path("id") id: String): Response<ResponseBody>

    @DELETE("/api/profile/photos/{id}")
    suspend fun deletePhoto(@Path("id") id: String): Response<SimpleMessageResponseDto>
}