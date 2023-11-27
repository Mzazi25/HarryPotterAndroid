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

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.mzazi.database.model.CharacterEntity
import com.mzazi.data.repo.CharactersRepo
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.flow.flowOf
import org.junit.Test

class CharacterListUseCaseTest {

    @MockK
    val mockCharactersRepo = mockk<com.mzazi.data.repo.CharactersRepo>()

    @Test
    fun `invoke should emit success state with combined data`() = runTest {
        val fakeCharacterEntity = listOf(
            com.mzazi.database.model.CharacterEntity(
                actor = "actor",
                alive = true,
                alternateNames = listOf(
                    "name",
                    "fake"
                ),
                ancestry = "ancestry",
                dateOfBirth = null,
                eyeColour = "eyeColor",
                gender = "Male",
                hairColour = "Blue",
                house = "Stark",
                id = "id",
                image = "image",
                name = "name",
                patronus = "patronus",
                species = "species",
                yearOfBirth = null
            ),
            com.mzazi.database.model.CharacterEntity(
                actor = "actor1",
                alive = false,
                alternateNames = listOf(
                    "name1",
                    "fake1"
                ),
                ancestry = "ancestry1",
                dateOfBirth = null,
                eyeColour = "eyeColor1",
                gender = "Famale",
                hairColour = "Red",
                house = "Stark1",
                id = "id1",
                image = "images",
                name = "names",
                patronus = "patronusss",
                species = "species1",
                yearOfBirth = null
            )
        )

        coEvery { mockCharactersRepo.getCharacters() } returns flowOf(fakeCharacterEntity)

        val getCharacterListUseCase = com.mzazi.domain.usecases.CharacterListUseCase(mockCharactersRepo)

        getCharacterListUseCase().test {
            assertThat(awaitItem().loading).isTrue()
            assertThat(awaitItem().data?.size).isEqualTo(2)
            awaitComplete()
        }
    }

    @Test
    fun `invoke should emit error state on throwing exception`() = runTest {
        val fakeCharacterEntity = listOf(
            com.mzazi.database.model.CharacterEntity(
                actor = "actor",
                alive = true,
                alternateNames = listOf(
                    "name",
                    "fake"
                ),
                ancestry = "ancestry",
                dateOfBirth = null,
                eyeColour = "eyeColor",
                gender = "Male",
                hairColour = "Blue",
                house = "Stark",
                id = "id",
                image = "image",
                name = "name",
                patronus = "patronus",
                species = "species",
                yearOfBirth = null
            ),
            com.mzazi.database.model.CharacterEntity(
                actor = "actor1",
                alive = false,
                alternateNames = listOf(
                    "name1",
                    "fake1"
                ),
                ancestry = "ancestry1",
                dateOfBirth = null,
                eyeColour = "eyeColor1",
                gender = "Famale",
                hairColour = "Red",
                house = "Stark1",
                id = "id1",
                image = "images",
                name = "names",
                patronus = "patronusss",
                species = "species1",
                yearOfBirth = null
            )
        )

        coEvery { mockCharactersRepo.getCharacters() } throws Exception("Error!")

        val getAllCharactersUseCase = com.mzazi.domain.usecases.CharacterListUseCase(mockCharactersRepo)

        getAllCharactersUseCase().test {
            awaitError()
            assertThat(awaitError().message).isEqualTo("Error!")
        }
    }
}