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

import com.mzazi.harrypotterandroid.domain.models.Characters
import com.mzazi.harrypotterandroid.domain.repo.CharactersRepo
import com.mzazi.harrypotterandroid.utils.Result
import javax.inject.Inject

class SearchCharacterImplUseCase @Inject constructor(
    private val repository: CharactersRepo
) : SearchCharacterUseCase {
    override suspend fun invoke(query: String): Result<List<Characters>> =
        when (val result = repository.getCharacters(fromCache = true)) {
            is Result.Success -> {
                val characters = result.data
                val filteredCharacters = characters.filter { character ->
                    character.name.contains(query, ignoreCase = true)
                }
                Result.Success(data = filteredCharacters)
            }
            is Result.Error -> result
        }
}