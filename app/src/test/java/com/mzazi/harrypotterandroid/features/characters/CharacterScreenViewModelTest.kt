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
package com.mzazi.harrypotterandroid.features.characters

import app.cash.turbine.test
import com.google.common.truth.Truth
import com.mzazi.harrypotterandroid.MainCoroutineRule
import com.mzazi.harrypotterandroid.domain.repo.CharactersRepo
import com.mzazi.harrypotterandroid.domain.usecases.CharacterListUseCase
import com.mzazi.harrypotterandroid.fakeMappedCharacters
import com.mzazi.harrypotterandroid.utils.ErrorType
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import com.mzazi.harrypotterandroid.R
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CharacterScreenViewModelTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @Test
    fun `when we init the viewmodel, then fetch and display the characters`() = runTest {
        val characterRepo = mockk<CharactersRepo> {
            coEvery { getCharacters() } returns Result.Success(fakeMappedCharacters)
        }

        val characterListUseCase = CharacterListUseCase(repository = characterRepo)
        val searchCharacterUseCase = SearchCharacterUseCase(repository = characterRepo)

        val viewModel = createViewModel(
            getCharacterListUseCase = characterListUseCase,
            searchCharacterUseCase = searchCharacterUseCase
        )

        val expectedState = CharacterScreenState(
            isLoading = false,
            characters = fakeMappedCharacters,
            errorMsg = null
        )

        viewModel.state.test {
            awaitItem().also { state ->
                Truth.assertThat(state).isEqualTo(expectedState)
            }
        }
    }

    @Test
    fun `when we init the viewmodel and an error occurs, then display the error screen`() =
        runTest {
            val characterRepo = mockk<CharactersRepo> {
                coEvery { getCharacters() } returns Result.Error(ErrorType.CLIENT)
            }
            val characterListUseCase = CharacterListUseCase(repository = characterRepo)
            val searchCharacterUseCase = SearchCharacterUseCase(repository = characterRepo)

            val viewModel = createViewModel(
                getCharacterListUseCase = characterListUseCase,
                searchCharacterUseCase = searchCharacterUseCase
            )

            val expectedState = CharacterScreenState(
                isLoading = false,
                characters = emptyList(),
                errorMsg = R.string.error_client
            )

            viewModel.state.test {
                awaitItem().also { state ->
                    Truth.assertThat(state).isEqualTo(expectedState)
                }
            }
        }

//    @Test
//    fun `when we init the search state, then display the search bar`() =
//        runTest {
//            val characterRepo = mockk<CharactersRepo> {
//                coEvery { getCharacters() } returns Result.Success(fakeMappedCharacters)
//            }
//            val characterListUseCase = CharacterListUseCase(repository = characterRepo)
//            val searchCharacterUseCase = SearchCharacterUseCase(repository = characterRepo)
//
//            val viewModel = createViewModel(
//                getCharacterListUseCase = characterListUseCase,
//                searchCharacterUseCase = searchCharacterUseCase
//            )
//
//            viewModel.processIntent(CharacterScreenIntent.DisplaySearchScreen)
//
//            val expectedState = CharacterScreenState(
//                isLoading = false,
//                errorMsg = null,
//                isSearching = true
//            )
//
//            viewModel.state.test {
//                awaitItem().also { state ->
//                    Truth.assertThat(state).isEqualTo(expectedState)
//                }
//            }
//        }
    private fun createViewModel(
        getCharacterListUseCase: CharacterListUseCase,
        searchCharacterUseCase: SearchCharacterUseCase
    ) =
        CharacterScreenViewModel(
            characterListUseCase = getCharacterListUseCase,
            searchCharacterUseCase = searchCharacterUseCase
        )
}