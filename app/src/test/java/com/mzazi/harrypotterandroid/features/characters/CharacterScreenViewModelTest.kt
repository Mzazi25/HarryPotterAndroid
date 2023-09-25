package com.mzazi.harrypotterandroid.features.characters

import app.cash.turbine.test
import com.google.common.truth.Truth
import com.mzazi.harrypotterandroid.MainCoroutineRule
import com.mzazi.harrypotterandroid.domain.repo.CharactersRepo
import com.mzazi.harrypotterandroid.domain.usecases.GetCharacterListImplUseCase
import com.mzazi.harrypotterandroid.domain.usecases.GetCharacterListUseCase
import com.mzazi.harrypotterandroid.domain.usecases.SearchCharacterImplUseCase
import com.mzazi.harrypotterandroid.domain.usecases.SearchCharacterUseCase
import com.mzazi.harrypotterandroid.fakeMappedCharacters
import com.mzazi.harrypotterandroid.utils.ErrorType
import io.mockk.coEvery
import io.mockk.mockk
import com.mzazi.harrypotterandroid.utils.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import com.mzazi.harrypotterandroid.R
import com.mzazi.harrypotterandroid.characterSearch
import io.mockk.justRun
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CharacterScreenViewModelTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule()
    @Test
    fun `when we init the viewmodel, then display loading state`() = runTest {
        val characterRepo = mockk<CharactersRepo> (relaxed = true)

        val characterListUseCase = GetCharacterListImplUseCase(repository = characterRepo)
        val searchCharacterUseCase = SearchCharacterImplUseCase(repository = characterRepo)

        val viewModel = createViewModel(
            getCharacterListUseCase = characterListUseCase,
            searchCharacterUseCase = searchCharacterUseCase
        )

        val expectedState = CharacterScreenState(
            isLoading = true,
            characters = emptyList(),
            errorMsg = null
        )

        viewModel.state.test {
            awaitItem().also { state ->
                Truth.assertThat(state).isEqualTo(expectedState)
            }
        }
    }
    @Test
    fun `when we init the viewmodel, then fetch and display the characters`() = runTest {
        val characterRepo = mockk<CharactersRepo> {
            coEvery { getCharacters() } returns Result.Success(fakeMappedCharacters)
        }

        val characterListUseCase = GetCharacterListImplUseCase(repository = characterRepo)
        val searchCharacterUseCase = SearchCharacterImplUseCase(repository = characterRepo)

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
            val characterListUseCase = GetCharacterListImplUseCase(repository = characterRepo)
            val searchCharacterUseCase = SearchCharacterImplUseCase(repository = characterRepo)

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
    @Test
    fun `when we search for characters and it is found, then display the character list`() =
        runTest {
            val characterRepo = mockk<CharactersRepo> {
                coEvery { getCharacters() } returns Result.Success(fakeMappedCharacters)
            }
            val characterListUseCase = GetCharacterListImplUseCase(repository = characterRepo)
            val searchCharacterUseCase = SearchCharacterImplUseCase(repository = characterRepo)

            val viewModel = createViewModel(
                getCharacterListUseCase = characterListUseCase,
                searchCharacterUseCase = searchCharacterUseCase
            )

            viewModel.processIntent(CharacterScreenIntent.SearchCharacter("name"))

            val expectedState = CharacterScreenState(
                isLoading = false,
                characters = listOf(characterSearch),
                errorMsg = null
            )

            viewModel.state.test {
                awaitItem() // skip the initial state
                awaitItem().also { state ->
                    Truth.assertThat(state).isEqualTo(expectedState)
                }
            }
        }
    @Test
    fun `when we init the search state, then display the search bar`() =
        runTest {
            val characterRepo = mockk<CharactersRepo> {
                coEvery { getCharacters() } returns Result.Success(fakeMappedCharacters)
            }
            val characterListUseCase = GetCharacterListImplUseCase(repository = characterRepo)
            val searchCharacterUseCase = SearchCharacterImplUseCase(repository = characterRepo)

            val viewModel = createViewModel(
                getCharacterListUseCase = characterListUseCase,
                searchCharacterUseCase = searchCharacterUseCase
            )

            viewModel.processIntent(CharacterScreenIntent.DisplaySearchScreen)


            val expectedState = CharacterScreenState(
                isLoading = false,
                errorMsg = null,
                isSearching = true
            )

            viewModel.state.test {
                awaitItem().also { state ->
                    Truth.assertThat(state).isEqualTo(expectedState)
                }
            }
        }
    private fun createViewModel(
        getCharacterListUseCase: GetCharacterListUseCase,
        searchCharacterUseCase: SearchCharacterUseCase
    ) =
        CharacterScreenViewModel(
            searchCharacterUseCase = searchCharacterUseCase,
            characterListUseCase = getCharacterListUseCase
        )
}