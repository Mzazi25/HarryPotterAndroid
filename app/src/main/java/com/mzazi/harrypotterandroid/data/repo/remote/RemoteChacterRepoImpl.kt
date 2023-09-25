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
package com.mzazi.harrypotterandroid.data.repo.remote

import com.mzazi.harrypotterandroid.data.mappers.asCoreModel
import com.mzazi.harrypotterandroid.data.mappers.mapResponseCodeToThrowable
import com.mzazi.harrypotterandroid.data.network.CharactersService
import com.mzazi.harrypotterandroid.data.utils.CharactersPaginationStore
import com.mzazi.harrypotterandroid.domain.models.Characters
import com.mzazi.harrypotterandroid.domain.repo.remote.RemoteCharactersRepo
import timber.log.Timber
import javax.inject.Inject

class RemoteChacterRepoImpl @Inject constructor(
    private val charactersService: CharactersService
) : RemoteCharactersRepo {
    override suspend fun getCharacters(fromCache: Boolean): List<Characters> {
        if (fromCache) {
            return CharactersPaginationStore.getCharacters()
        }
        val response = charactersService.getCharactersData()
        val characterResponse = response.body()
        return if (response.isSuccessful && response.body() != null) {
            val mappedCharacters = characterResponse!!.map { it.asCoreModel() }
            CharactersPaginationStore.addCharacters(mappedCharacters)
            CharactersPaginationStore.getCharacters()
        } else {
            val throwable = mapResponseCodeToThrowable(response.code())
            Timber.e("throwable-----------${response.code()}")
            throw throwable
        }
    }
}