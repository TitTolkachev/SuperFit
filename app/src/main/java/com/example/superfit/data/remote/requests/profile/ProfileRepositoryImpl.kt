package com.example.superfit.data.remote.requests.profile

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.superfit.data.remote.dto.BodyParamsDto
import com.example.superfit.domain.model.BodyParamsBody
import com.example.superfit.domain.model.PhotoInfoBody
import com.example.superfit.domain.model.ProfileResponseBody
import com.example.superfit.domain.repository.remote.ProfileRepository
import com.example.superfit.domain.util.Resource
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(private val api: ProfileApi) : ProfileRepository {

    override suspend fun getProfile(): Resource<ProfileResponseBody> {
        return try {
            val request = api.getProfile()

            if (request.isSuccessful) {
                Resource.Success(ProfileResponseBody(request.body()?.login ?: ""))
            } else {
                val error = JSONObject(request.errorBody()!!.string())
                Resource.NetworkError(error.getString("message"))
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
                val error = JSONObject(request.errorBody()!!.string())
                Resource.NetworkError(error.getString("message"))
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
                val error = JSONObject(request.errorBody()!!.string())
                Resource.NetworkError(error.getString("message"))
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
                val error = JSONObject(request.errorBody()!!.string())
                Resource.NetworkError(error.getString("message"))
            }

        } catch (e: Exception) {
            Resource.Exception(e)
        }
    }

    override suspend fun uploadPhoto(image: Bitmap): Resource<PhotoInfoBody> {
        return try {
            val imageMultipartBody = convertBitmapToMultipartBody(image)
            val request = api.uploadPhoto(imageMultipartBody)

            if (request.isSuccessful) {
                Resource.Success(
                    PhotoInfoBody(request.body()?.id ?: "", request.body()?.uploaded ?: 0)
                )
            } else {
                val error = JSONObject(request.errorBody()!!.string())
                Resource.NetworkError(error.getString("message"))
            }

        } catch (e: Exception) {
            Resource.Exception(e)
        }
    }

    override suspend fun downloadPhoto(id: String): Resource<Bitmap> {
        return try {
            val request = api.downloadPhoto(id)

            if (request.isSuccessful) {
                val bitmap = BitmapFactory.decodeByteArray(
                    request.body()?.bytes(),
                    0,
                    request.body()?.contentLength()?.toInt() ?: 0
                )
                Resource.Success(bitmap)
            } else {
                val error = JSONObject(request.errorBody()!!.string())
                Resource.NetworkError(error.getString("message"))
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
                val error = JSONObject(request.errorBody()!!.string())
                Resource.NetworkError(error.getString("message"))
            }

        } catch (e: Exception) {
            Resource.Exception(e)
        }
    }

    private fun convertBitmapToMultipartBody(bitmap: Bitmap): MultipartBody.Part {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        val byteArray = stream.toByteArray()
        return MultipartBody.Part.createFormData(
            "file", "image.png",
            byteArray.toRequestBody("image/png".toMediaTypeOrNull(), 0, byteArray.size)
        )
    }
}