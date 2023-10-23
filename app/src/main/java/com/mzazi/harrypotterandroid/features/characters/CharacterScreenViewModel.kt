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

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mzazi.harrypotterandroid.domain.model.Characters
import com.mzazi.harrypotterandroid.domain.usecases.CharacterListUseCase
import com.mzazi.harrypotterandroid.domain.usecases.SearchCharacterUseCase
import com.mzazi.harrypotterandroid.utils.Result
import com.mzazi.harrypotterandroid.utils.toStringResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterScreenViewModel @Inject constructor(
    private val searchCharacterUseCase: SearchCharacterUseCase,
    private val characterListUseCase: CharacterListUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CharacterScreenState(isLoading = true))
    val state: StateFlow<CharacterScreenState> = _state.asStateFlow()

    private var searchJob: Job? = null

    init {
        processIntent(CharacterScreenIntent.LoadLatestCharacters)
    }

    fun processIntent(intent: CharacterScreenIntent) {
        when (intent) {
            is CharacterScreenIntent.DisplaySearchScreen -> {
                setState {
                    copy(
                        isSearching = !isSearching
                    )
                }
            }
            is CharacterScreenIntent.LoadLatestCharacters -> {
                viewModelScope.launch {
                    val resultList = characterListUseCase()
                    processResult(resultList)
                }
            }
            is CharacterScreenIntent.SearchCharacter -> {
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(REQUEST_QUEUE_DELAY)
                    setState {
                        copy(
                            isSearching = true
                        )
                    }
                    val searchedResult = searchCharacterUseCase(intent.query)
                    processResult(searchedResult)
                }
            }
        }
    }
    private fun processResult(result: Result<List<Characters>>) {
        when (result) {
            is Result.Success -> {
                val characters = result.data
                _state.value = _state.value.onCharacterLoaded(characters)
            }

            is Result.Error -> {
                _state.value = _state.value.onError(result.errorType.toStringResource())
            }
        }
    }
    companion object {
        private val REQUEST_QUEUE_DELAY = 300L
    }
    private fun setState(stateReducer: CharacterScreenState.() -> CharacterScreenState) {
        viewModelScope.launch {
            _state.emit(stateReducer(state.value))
        }
    }
}