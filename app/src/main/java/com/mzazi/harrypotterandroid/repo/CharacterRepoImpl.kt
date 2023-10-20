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
package com.mzazi.harrypotterandroid.repo

import com.mzazi.harrypotterandroid.cache.CharacterDao
import com.mzazi.harrypotterandroid.mappers.asCoreModel
import com.mzazi.harrypotterandroid.mappers.mapResponseCodeToThrowable
import com.mzazi.harrypotterandroid.mappers.mapThrowableToErrorType
import com.mzazi.harrypotterandroid.mappers.toCoreEntity
import com.mzazi.harrypotterandroid.network.CharactersService
import com.mzazi.harrypotterandroid.ui.model.Characters
import com.mzazi.harrypotterandroid.utils.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class CharacterRepoImpl @Inject constructor(
    private val characterDao: CharacterDao,
    private val api: CharactersService,
) : CharactersRepo {
    override suspend fun getCharacters(): Result<List<Characters>> =
        withContext(Dispatchers.IO) {
            try {
                val apiResponse = api.getCharactersData()
                if (apiResponse.isSuccessful && apiResponse.body() !=null){
                    val mappedResponse = apiResponse.body()!!.map { it.toCoreEntity() }
                    characterDao.insertCharacters(mappedResponse)
                }else {
                    val throwable = mapResponseCodeToThrowable(apiResponse.code())
                    throw throwable
                }
            } catch (throwable: Throwable) {
                Timber.e("Throwable-----------$throwable")
            }
            val sourceOfTruth = characterDao.getCharacter().map { it.asCoreModel() }
            Result.Success(sourceOfTruth)
        }
}