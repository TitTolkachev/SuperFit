package com.example.superfit.domain.usecase.remote

import com.example.superfit.domain.model.ProfileResponseBody
import com.example.superfit.domain.repository.remote.ProfileRepository
import com.example.superfit.domain.util.Resource
import kotlinx.coroutines.runBlocking

import org.junit.Assert.assertEquals
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.junit.Test
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class GetProfileUseCaseTest {

    @Test
    fun `test returns the same data as in repository`() {

        val testProfile = ProfileResponseBody(login = "login")
        val repository = mock<ProfileRepository> {
            onBlocking { getProfile() } doReturn Resource.Success(testProfile)
        }

        val expected = ProfileResponseBody(login = "login")
        val actual = runBlocking {
            repository.getProfile().data
        }

        assertEquals(expected, actual)
        runBlocking {
            verify(repository, times(1)).getProfile()
        }
    }
}