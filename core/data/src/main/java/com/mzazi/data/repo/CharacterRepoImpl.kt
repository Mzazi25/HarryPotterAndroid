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
package com.mzazi.data.repo

import com.mzazi.data.mappers.toCoreEntity
import com.mzazi.network.CharactersService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject

internal class CharacterRepoImpl @Inject constructor(
    private val characterDao: com.mzazi.database.dao.CharacterDao,
    private val api: CharactersService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : CharactersRepo {
    override fun getCharacters(): Flow<List<com.mzazi.database.model.CharacterEntity>> = flow {
        try {
            val mappedCharacters = api.getCharactersData().map { it.toCoreEntity() }
            characterDao.insertCharacters(mappedCharacters)
        } catch (e: Exception) {
            // Something went wrong with the query to the API
            // Log the error and proceed
            Timber.e("Exception caught--------------$e")
        }
        val sourceOfTruth = characterDao.getCharacter()

        emit(sourceOfTruth)
    }.flowOn(dispatcher)
}