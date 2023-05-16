package com.example.superfit.domain.repository.remote

import com.example.superfit.domain.model.BodyParamsBody
import com.example.superfit.domain.model.PhotoInfoBody
import com.example.superfit.domain.model.ProfileResponseBody
import com.example.superfit.domain.util.Resource

interface ProfileRepository {

    suspend fun getProfile(): Resource<ProfileResponseBody>
    suspend fun updateBodyParameters(params: BodyParamsBody): Resource<String>
    suspend fun getHistory(): Resource<List<BodyParamsBody>>
    suspend fun getPhotos(): Resource<List<PhotoInfoBody>>

    // TODO------------------------------------------------
//    suspend fun uploadPhoto(@Part file: MultipartBody.Part): Resource<PhotoInfoBody>
//    suspend fun downloadPhoto(id: String): Resource<String>
    suspend fun deletePhoto(id: String): Resource<String>
}