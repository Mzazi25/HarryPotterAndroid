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
import com.mzazi.harrypotterandroid.domain.repo.CharactersRepo
import com.mzazi.harrypotterandroid.fakeMappedCharacters
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import com.mzazi.harrypotterandroid.utils.Result
import org.junit.Assert.*
import org.junit.Test

class SearchCharacterImplUseCaseTest {
    @MockK
    val mockCharactersRepo = mockk<CharactersRepo>()

    @Test
    fun `when we search for character by title, then return success`() = runTest {
        // given
        coEvery { mockCharactersRepo.getCharacters(fromCache = true) } returns Result.Success(
            fakeMappedCharacters
        )

        val useCase = createSearchCharacterUseCase(mockCharactersRepo)

        // when
        val result = useCase.invoke(query = "name")

        // then
        Truth.assertThat(result).isInstanceOf(Result.Success::class.java)
        Truth.assertThat((result as Result.Success).data).isNotEmpty()
    }

    @Test
    fun `when we search for character by title and it's not found, then return an empty list`() =
        runTest {
            // given
            coEvery { mockCharactersRepo.getCharacters(fromCache = true) } returns Result.Success(
                fakeMappedCharacters
            )

            val useCase = createSearchCharacterUseCase(mockCharactersRepo)

            // when
            val result = useCase.invoke(query = "fake")

            // then
            Truth.assertThat(result).isInstanceOf(Result.Success::class.java)
            Truth.assertThat((result as Result.Success).data).isEmpty()
        }
    private fun createSearchCharacterUseCase(
        repository: CharactersRepo
    ): SearchCharacterUseCase = SearchCharacterImplUseCase(repository = repository)
}