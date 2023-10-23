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
package com.mzazi.harrypotterandroid.features.characterdetails

import app.cash.turbine.test
import com.google.common.truth.Truth
import com.mzazi.harrypotterandroid.MainCoroutineRule
import com.mzazi.harrypotterandroid.domain.repo.CharactersRepo
import com.mzazi.harrypotterandroid.domain.usecases.CharacterDetailsUseCase
import com.mzazi.harrypotterandroid.fakeMappedCharacters
import com.mzazi.harrypotterandroid.R
import io.mockk.coEvery
import com.mzazi.harrypotterandroid.utils.Result
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CharacterDetailsViewModelTest {
    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @Test
    fun `when we init the viewmodel, then display loading state`() = runTest {
        // Given
        val repository = mockk<CharactersRepo>() {
            coEvery { getCharacters() } returns Result.Success(fakeMappedCharacters)
        }
        val useCase = CharacterDetailsUseCase(repository = repository)

        // When
        val viewModel = createViewModel(
            getCharacterDetails = useCase
        )

        // Then
        val expectedState = CharacterDetailsState(
            isLoading = true
        )

        viewModel.state.test {
            awaitItem().also { state ->
                Truth.assertThat(state).isEqualTo(expectedState)
            }
        }
    }

    @Test
    fun `when we init the viewmodel with a character id, then fetch and display the movie`() = runTest {
        // Given
        val repository = mockk<CharactersRepo>() {
            coEvery { getCharacters() } returns Result.Success(fakeMappedCharacters)
        }
        val useCase = CharacterDetailsUseCase(repository = repository)

        // When
        val viewModel = createViewModel(
            getCharacterDetails = useCase
        )

        viewModel.processIntent(CharacterDetailsIntent.LoadCharacterDetail(characterId = "id"))

        // Then
        val expectedState = CharacterDetailsState(
            isLoading = false,
            errorMsg = null,
            actor = "actor",
            alive = true,
            alternateNames = listOf(
                "name",
                "fake"
            ),
            ancestry = "ancestry",
            dateOfBirth = null,
            eyeColour = "eyeColor",
            gender = "Male",
            hairColour = "Blue",
            house = "Stark",
            id = "id",
            image = "image",
            name = "name",
            patronus = "patronus",
            species = "species",
            yearOfBirth = null
        )

        viewModel.state.test {
            awaitItem().also { state ->
                Truth.assertThat(state).isEqualTo(expectedState)
            }
        }
    }

    @Test
    fun `when we init the viewmodel with a wrong character id, then display the error screen`() = runTest {
        // Given
        val repository = mockk<CharactersRepo>() {
            coEvery { getCharacters() } returns Result.Success(fakeMappedCharacters)
        }
        val useCase = CharacterDetailsUseCase(repository = repository)

        // When
        val viewModel = createViewModel(
            getCharacterDetails = useCase
        )

        viewModel.processIntent(CharacterDetailsIntent.LoadCharacterDetail(characterId = "random"))

        // Then
        val expectedState = CharacterDetailsState(
            errorMsg = R.string.error_character_not_found
        )

        viewModel.state.test {
            awaitItem().also { state ->
                Truth.assertThat(state).isEqualTo(expectedState)
            }
        }
    }

    private fun createViewModel(
        getCharacterDetails: CharacterDetailsUseCase
    ) = CharacterDetailsViewModel(
        getCharacterDetailsImplUseCase = getCharacterDetails
    )
}