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
package com.mzazi.harrypotterandroid.domain.usecases

import com.mzazi.harrypotterandroid.data.mappers.asCoreModel
import com.mzazi.harrypotterandroid.domain.model.Characters
import com.mzazi.harrypotterandroid.domain.repo.CharactersRepo
import com.mzazi.harrypotterandroid.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import timber.log.Timber
import javax.inject.Inject

class CharacterListUseCase @Inject constructor(
    private val repository: CharactersRepo
) {
    operator fun invoke(): Flow<DataState<List<Characters>>> = flow{
        repository.getCharacters()
            .onStart {
                emit(DataState.loading())
            }.catch { throwable->
                emit(DataState.error(error = throwable))
            }.collect { entity->
                val characterList = entity.map { it.asCoreModel() }
                emit(DataState.success(characterList))
            }
    }

}