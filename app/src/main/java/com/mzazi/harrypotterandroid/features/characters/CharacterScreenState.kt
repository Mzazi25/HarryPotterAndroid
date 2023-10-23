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

import androidx.annotation.StringRes
import com.mzazi.harrypotterandroid.domain.model.Characters

data class CharacterScreenState(
    val characters: List<Characters> = emptyList(),
    val isLoading: Boolean = false,
    @StringRes val errorMsg: Int? = null,
    val isSearching: Boolean = false
) {
    fun onCharacterLoaded(characters: List<Characters>): CharacterScreenState {
        return copy(characters = characters, isLoading = false, errorMsg = null)
    }

    fun onError(@StringRes errorMsg: Int): CharacterScreenState {
        return copy(isLoading = false, errorMsg = errorMsg)
    }
}