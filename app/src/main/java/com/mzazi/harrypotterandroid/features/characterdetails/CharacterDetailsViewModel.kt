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
import com.mzazi.harrypotterandroid.domain.models.Characters
import com.mzazi.harrypotterandroid.domain.usecases.GetCharacterDetailsImplUseCase
import com.mzazi.harrypotterandroid.utils.Result
import com.mzazi.harrypotterandroid.utils.toStringResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val getCharacterDetailsImplUseCase: GetCharacterDetailsImplUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CharacterDetailsState(isLoading = true))
    val state: StateFlow<CharacterDetailsState> = _state.asStateFlow()
    fun processIntent(characterDetailsIntent: CharacterDetailsIntent) {
        when (characterDetailsIntent) {
            is CharacterDetailsIntent.LoadCharacterDetail -> {
                viewModelScope.launch {
                    val result = getCharacterDetailsImplUseCase(characterDetailsIntent.characterId)
                    processResult(result)
                }
            }
        }
    }
    private fun processResult(result: Result<Characters>) {
        when (result) {
            is Result.Success -> {
                val character = result.data
                _state.value = CharacterDetailsState(
                    actor = character.actor,
                    alive = character.alive,
                    alternateNames = character.alternateNames,
                    ancestry = character.ancestry,
                    dateOfBirth = character.dateOfBirth,
                    eyeColour = character.eyeColour,
                    gender = character.gender,
                    hairColour = character.hairColour,
                    house = character.house,
                    id = character.id,
                    image = character.image,
                    name = character.name,
                    patronus = character.patronus,
                    species = character.species,
                    yearOfBirth = character.yearOfBirth,
                    isLoading = false
                )
            }

            is Result.Error -> {
                _state.value = CharacterDetailsState(
                    errorMsg = result.errorType.toStringResource()
                )
            }
        }
    }
}