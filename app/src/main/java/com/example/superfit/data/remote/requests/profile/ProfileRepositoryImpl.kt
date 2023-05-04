package com.example.superfit.data.remote.requests.profile

import com.example.superfit.domain.model.ProfileResponseBody
import com.example.superfit.domain.repository.remote.ProfileRepository
import com.example.superfit.domain.util.Resource
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(private val api: ProfileApi) : ProfileRepository {

    override suspend fun getProfile(): Resource<ProfileResponseBody> {
        return try {
            val request = api.getProfile()

            if (request.isSuccessful) {
                Resource.Success(ProfileResponseBody(request.body()?.login ?: ""))
            } else {
                Resource.NetworkError(request.message())
            }

        } catch (e: Exception) {
            Resource.Exception(e)
        }
    }
}