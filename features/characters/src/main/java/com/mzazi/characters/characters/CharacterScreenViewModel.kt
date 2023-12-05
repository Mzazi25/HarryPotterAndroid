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
package com.mzazi.characters.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mzazi.domain.usecases.CharacterListUseCase
import com.mzazi.models.Characters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterScreenViewModel @Inject constructor(
  private val characterListUseCase: CharacterListUseCase,
) : ViewModel() {

  private val _state = MutableStateFlow(CharacterScreenState())
  val state: StateFlow<CharacterScreenState> = _state.asStateFlow()

  private val originalCharacterList = mutableListOf<Characters>()

  init {
    getCharacters()
  }

  private fun getCharacters() {
    characterListUseCase().onEach { dataState ->
      _state.value = _state.value.copy(
        isLoading = dataState.loading,
      )
      dataState.data?.let { characterList ->
        _state.value = _state.value.copy(
          characters = characterList,
        )
      }
      _state.value = _state.value.copy(
        error = dataState.error,
      )
    }.launchIn(viewModelScope)
  }

  fun onSearch(query: String) {
    val filteredFlow = flow {
      if (originalCharacterList.isEmpty()) {
        originalCharacterList.addAll(_state.value.characters)
      }
      val filteredList = if (query.isNotEmpty()) {
        originalCharacterList.filter { character ->
          character.name.contains(query, ignoreCase = true)
        }
      } else {
        originalCharacterList
      }
      emit(filteredList)
    }

    viewModelScope.launch {
      filteredFlow.collect { characterList ->
        _state.value = CharacterScreenState(
          characters = characterList,
        )
      }
    }
  }
  fun dismissError() {
    _state.value = CharacterScreenState(
      error = null,
    )
  }

  override fun onCleared() {
    super.onCleared()
    originalCharacterList.clear()
  }
}
