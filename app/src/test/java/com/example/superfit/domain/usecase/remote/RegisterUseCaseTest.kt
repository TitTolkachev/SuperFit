package com.example.superfit.domain.usecase.remote

import com.example.superfit.data.repository.FakeAuthRepository
import com.example.superfit.domain.model.RegisterRequestBody
import com.example.superfit.domain.repository.remote.AuthRepository
import com.example.superfit.domain.util.Resource
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class RegisterUseCaseTest {
    private lateinit var fakeRepository: AuthRepository
    private lateinit var registerUseCase: RegisterUseCase

    @Before
    fun setUp() {
        fakeRepository = FakeAuthRepository()
        registerUseCase = RegisterUseCase(fakeRepository)
    }

    @Test
    fun `test registers user correctly`() {
        val credentials = RegisterRequestBody("abc@abc.abc", 1234)

        val result = runBlocking {registerUseCase.execute(credentials) }
        Assert.assertTrue(result is Resource.Success)
    }

    @Test
    fun `test returns error when registers user twice with the same login`() {
        val credentials = RegisterRequestBody("abc@abc.abc", 1234)

        runBlocking {registerUseCase.execute(credentials)}
        val result = runBlocking {registerUseCase.execute(credentials) }

        Assert.assertTrue(result is Resource.NetworkError)
    }
}