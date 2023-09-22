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
import com.mzazi.harrypotterandroid.utils.ErrorType
import com.mzazi.harrypotterandroid.utils.Result
import javax.inject.Inject

class GetCharacterDetailsImplUseCase @Inject constructor(
    private val repository: CharactersRepo
) : GetCharacterDetailsUseCase {
    override suspend fun invoke(characterId: String): Result<Characters> {
        return when (val result = repository.getCharacters()) {
            is Result.Success -> {
                val character = result.data.find { it.id == characterId }
                if (character == null) {
                    Result.Error(ErrorType.CHARACTER_NOT_FOUND)
                } else {
                    Result.Success(character)
                }
            }
            is Result.Error -> Result.Error(result.errorType)
        }
    }
}