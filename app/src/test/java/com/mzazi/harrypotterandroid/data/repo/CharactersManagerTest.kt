/*
 * Copyright 2023 HarryPotterAndroid
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mzazi.harrypotterandroid.data.repo

import com.google.common.truth.Truth
import com.mzazi.harrypotterandroid.data.network.CharactersService
import com.mzazi.harrypotterandroid.data.repo.remote.RemoteChacterRepoImpl
import com.mzazi.harrypotterandroid.domain.repo.CharactersRepo
import com.mzazi.harrypotterandroid.domain.repo.remote.RemoteCharactersRepo
import com.mzazi.harrypotterandroid.fakeCharacterResponse
import com.mzazi.harrypotterandroid.fakeMappedCharacters
import com.mzazi.harrypotterandroid.utils.ErrorType
import com.mzazi.harrypotterandroid.utils.Result
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Test
import retrofit2.Response
import java.io.IOException

class CharactersManagerTest {
    @MockK
    val mockCharacterService = mockk<CharactersService>()

    @Test
    fun `when we fetch characters,then we get a list of characters`() = runTest {
        coEvery {
            mockCharacterService.getCharactersData()
        } returns Response.success(fakeCharacterResponse)

        val remoteDataSource = createRemoteCharacterRepository(
            charactersService = mockCharacterService
        )

        val characterRepository = createCharactersRepository(
            remoteCharactersRepo = remoteDataSource
        )
        // When
        val characters = characterRepository.getCharacters()

        // Then
        val expectedResults = fakeMappedCharacters

        Truth.assertThat(characters).isInstanceOf(Result.Success::class.java)
        Truth.assertThat((characters as Result.Success).data).isEqualTo(expectedResults)
    }

    @Test
    fun `when we fetch characters and a server error occurs,then we get the right error type`() =
        runTest {
            // Given
            coEvery {
                mockCharacterService.getCharactersData()
            } returns Response.error(500, "".toResponseBody())

            val remoteDataSource = createRemoteCharacterRepository(
                charactersService = mockCharacterService
            )

            val characterRepository = createCharactersRepository(
                remoteCharactersRepo = remoteDataSource
            )
            // When
            val characters = characterRepository.getCharacters()

            // Then
            Truth.assertThat(characters).isInstanceOf(Result.Error::class.java)
            Truth.assertThat((characters as Result.Error).errorType).isEqualTo(ErrorType.SERVER)
        }

    @Test
    fun `when we fetch a list of character and a client error occurs,then we get the right error type`() =
        runTest {
            // Given
            coEvery {
                mockCharacterService.getCharactersData()
            } returns Response.error(406, "".toResponseBody())

            val remoteDataSource = createRemoteCharacterRepository(
                charactersService = mockCharacterService
            )

            val characterRepository = createCharactersRepository(
                remoteCharactersRepo = remoteDataSource
            )
            // When
            val characters = characterRepository.getCharacters()

            // Then
            Truth.assertThat(characters).isInstanceOf(Result.Error::class.java)
            Truth.assertThat((characters as Result.Error).errorType).isEqualTo(ErrorType.CLIENT)
        }

    @Test
    fun `when we fetch a list of characters and a generic error occurs,then we get the right error type`() =
        runTest {
            // Given
            coEvery {
                mockCharacterService.getCharactersData()
            } returns Response.error(605, "".toResponseBody())

            val remoteDataSource = createRemoteCharacterRepository(
                charactersService = mockCharacterService
            )

            val characterRepository = createCharactersRepository(
                remoteCharactersRepo = remoteDataSource
            )
            // When
            val characters = characterRepository.getCharacters()

            // Then
            Truth.assertThat(characters).isInstanceOf(Result.Error::class.java)
            Truth.assertThat((characters as Result.Error).errorType).isEqualTo(ErrorType.GENERIC)
        }

    @Test
    fun `when an io exception is thrown,then we get the right error type`() =
        runTest {
            // Given
            coEvery {
                mockCharacterService.getCharactersData()
            } throws IOException()

            val remoteDataSource = createRemoteCharacterRepository(
                charactersService = mockCharacterService
            )

            val characterRepository = createCharactersRepository(
                remoteCharactersRepo = remoteDataSource
            )
            // When
            val characters = characterRepository.getCharacters()

            // Then
            Truth.assertThat(characters).isInstanceOf(Result.Error::class.java)
            Truth.assertThat((characters as Result.Error).errorType).isEqualTo(ErrorType.IO_CONNECTION)
        }
    private fun createCharactersRepository(
        remoteCharactersRepo: RemoteCharactersRepo
    ): CharactersRepo = CharactersManager(
        remoteCharactersRepo = remoteCharactersRepo
    )

    private fun createRemoteCharacterRepository(
        charactersService: CharactersService = mockCharacterService
    ): RemoteCharactersRepo = RemoteChacterRepoImpl(
        charactersService = charactersService
    )
}