package com.example.superfit.data.remote.requests.profile

import com.example.superfit.data.remote.dto.BodyParamsDto
import com.example.superfit.domain.model.BodyParamsBody
import com.example.superfit.domain.model.PhotoInfoBody
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

    override suspend fun updateBodyParameters(params: BodyParamsBody): Resource<String> {
        return try {
            val request =
                api.updateBodyParameters(BodyParamsDto(params.weight, params.height, params.date))

            if (request.isSuccessful) {
                Resource.Success(request.body()?.message)
            } else {
                Resource.NetworkError(request.message())
            }

        } catch (e: Exception) {
            Resource.Exception(e)
        }
    }

    override suspend fun getHistory(): Resource<List<BodyParamsBody>> {
        return try {
            val request = api.getHistory()

            if (request.isSuccessful) {
                Resource.Success(
                    request.body()?.map { BodyParamsBody(it.weight, it.height, it.date) })
            } else {
                Resource.NetworkError(request.message())
            }

        } catch (e: Exception) {
            Resource.Exception(e)
        }
    }

    override suspend fun getPhotos(): Resource<List<PhotoInfoBody>> {
        return try {
            val request = api.getPhotos()

            if (request.isSuccessful) {
                Resource.Success(
                    request.body()?.map { PhotoInfoBody(it.id, it.uploaded) })
            } else {
                Resource.NetworkError(request.message())
            }

        } catch (e: Exception) {
            Resource.Exception(e)
        }
    }

    override suspend fun deletePhoto(id: String): Resource<String> {
        return try {
            val request = api.deletePhoto(id)

            if (request.isSuccessful) {
                Resource.Success(request.body()?.message)
            } else {
                Resource.NetworkError(request.message())
            }

        } catch (e: Exception) {
            Resource.Exception(e)
        }
    }
}