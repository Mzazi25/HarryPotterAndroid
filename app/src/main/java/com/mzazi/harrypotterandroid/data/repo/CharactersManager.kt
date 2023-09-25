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
package com.mzazi.harrypotterandroid.data.repo

import com.mzazi.harrypotterandroid.data.mappers.mapThrowableToErrorType
import com.mzazi.harrypotterandroid.domain.models.Characters
import com.mzazi.harrypotterandroid.domain.repo.CharactersRepo
import com.mzazi.harrypotterandroid.domain.repo.remote.RemoteCharactersRepo
import com.mzazi.harrypotterandroid.utils.Result
import javax.inject.Inject

class CharactersManager @Inject constructor(
    private val remoteCharactersRepo: RemoteCharactersRepo
) : CharactersRepo {
    override suspend fun getCharacters(fromCache: Boolean): Result<List<Characters>> =
        try {
            val characters = remoteCharactersRepo.getCharacters(fromCache = fromCache)
            Result.Success(data = characters)
        } catch (throwable: Throwable) {
            val error = mapThrowableToErrorType(throwable)
            Result.Error(error)
        }
}