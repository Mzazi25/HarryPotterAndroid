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

import androidx.annotation.StringRes

data class CharacterDetailsState(
    val actor: String? = null,
    val alive: Boolean? = null,
    val alternateNames: List<String>? = emptyList(),
    val ancestry: String? = null,
    val dateOfBirth: String? = null,
    val eyeColour: String? = null,
    val gender: String? = null,
    val hairColour: String? = null,
    val house: String? = null,
    val id: String? = null,
    val image: String? = null,
    val name: String? = null,
    val patronus: String? = null,
    val species: String? = null,
    val yearOfBirth: Int? = null,
    val isLoading: Boolean = false,
    @StringRes val errorMsg: Int? = null
) {
    fun onError(@StringRes errorMsg: Int): CharacterDetailsState {
        return copy(isLoading = false, errorMsg = errorMsg)
    }

    fun onCharacterDetailsLoaded(
        actor: String,
        alive: Boolean,
        alternateNames: List<String>,
        ancestry: String,
        dateOfBirth: String,
        eyeColour: String,
        gender: String,
        hairColour: String,
        house: String,
        id: String,
        image: String,
        name: String,
        patronus: String,
        species: String,
        yearOfBirth: Int
    ): CharacterDetailsState {
        return copy(
            actor = actor,
            alive = alive,
            alternateNames = alternateNames,
            ancestry = ancestry,
            dateOfBirth = dateOfBirth,
            eyeColour = eyeColour,
            gender = gender,
            hairColour = hairColour,
            house = house,
            id = id,
            image = image,
            name = name,
            patronus = patronus,
            species = species,
            yearOfBirth = yearOfBirth,
            isLoading = false,
            errorMsg = null
        )
    }
}