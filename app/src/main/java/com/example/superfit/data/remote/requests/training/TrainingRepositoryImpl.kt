package com.example.superfit.data.remote.requests.training

import com.example.superfit.data.remote.dto.TrainingDto
import com.example.superfit.domain.model.Training
import com.example.superfit.domain.repository.remote.TrainingRepository
import com.example.superfit.domain.util.Resource
import javax.inject.Inject

class TrainingRepositoryImpl @Inject constructor(private val api: TrainingApi) :
    TrainingRepository {

    override suspend fun getTrainings(): Resource<List<Training>> {

        return try {
            val request = api.getTrainings()

            if (request.isSuccessful) {
                Resource.Success(
                    request.body()?.map { Training(it.date, it.exercise, it.repeatCount) })
            } else {
                Resource.NetworkError(request.message())
            }

        } catch (e: Exception) {
            Resource.Exception(e)
        }
    }

    override suspend fun saveTrainings(training: Training): Resource<Training> {

        return try {
            val request = api.saveTrainings(
                TrainingDto(
                    training.date,
                    training.exercise,
                    training.repeatCount
                )
            )

            if (request.isSuccessful) {
                val data = request.body()!!
                Resource.Success(
                    Training(data.date, data.exercise, data.repeatCount)
                )
            } else {
                Resource.NetworkError(request.message())
            }

        } catch (e: Exception) {
            Resource.Exception(e)
        }
    }
}