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

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mzazi.harrypotterandroid.domain.usecases.CharacterDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val getCharacterDetailsUseCase: CharacterDetailsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CharacterDetailsState())
    val state: StateFlow<CharacterDetailsState> = _state.asStateFlow()

    fun getCharacterId(characterId: String) {
        getCharacterDetailsUseCase(characterId).onEach { dataState ->
            _state.value = _state.value.copy(
                isLoading = dataState.loading,
            )
            dataState.data?.let { characters ->
                _state.value = _state.value.copy(
                    characterDetails = characters
                )
            }
            _state.value = _state.value.copy(
                error = dataState.error
            )
        }.launchIn(viewModelScope)
    }

    fun dismissError() {
        _state.value = _state.value.copy(
            error = null
        )
    }
}