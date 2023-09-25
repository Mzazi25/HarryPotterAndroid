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
package com.mzazi.harrypotterandroid.domain.usecases

import com.google.common.truth.Truth
import com.mzazi.harrypotterandroid.characterSearch
import com.mzazi.harrypotterandroid.domain.repo.CharactersRepo
import com.mzazi.harrypotterandroid.fakeMappedCharacters
import com.mzazi.harrypotterandroid.utils.ErrorType
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import com.mzazi.harrypotterandroid.utils.Result
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test

class GetCharacterDetailsImplUseCaseTest {
    @MockK
    val mockCharacterRepo = mockk<CharactersRepo>()

    @Test
    fun `when we get character details ,then return success`() = runTest {
        // Given
        coEvery { mockCharacterRepo.getCharacters() } returns Result.Success(
            fakeMappedCharacters
        )

        val getLatestMovieDetailsUseCase = createGetCharacterDetailsUseCase(mockCharacterRepo)

        // When
        val result = getLatestMovieDetailsUseCase.invoke(characterId = "id")

        // Then
        val expectedResult = characterSearch

        Truth.assertThat(result).isInstanceOf(Result.Success::class.java)
        Truth.assertThat((result as Result.Success).data).isEqualTo(expectedResult)
    }

    @Test
    fun `when we get a specific character details and it's not found ,then return an error`() =
        runTest {
            // Given
            coEvery { mockCharacterRepo.getCharacters() } returns Result.Success(
                fakeMappedCharacters
            )

            val getLatestMovieDetailsUseCase = createGetCharacterDetailsUseCase(mockCharacterRepo)

            // When
            val result = getLatestMovieDetailsUseCase.invoke(characterId = "random")

            // Then
            Truth.assertThat(result).isInstanceOf(Result.Error::class.java)
            Truth.assertThat((result as Result.Error).errorType)
                .isEqualTo(ErrorType.CHARACTER_NOT_FOUND)
        }

    private fun createGetCharacterDetailsUseCase(
        repository: CharactersRepo
    ): GetCharacterDetailsUseCase {
        return GetCharacterDetailsImplUseCase(repository = repository)
    }
}