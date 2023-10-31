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

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.mzazi.harrypotterandroid.data.cache.dao.CharacterDao
import com.mzazi.harrypotterandroid.data.cache.model.CharacterEntity
import com.mzazi.harrypotterandroid.data.network.CharactersService
import com.mzazi.harrypotterandroid.data.network.models.CharactersResponse
import com.mzazi.harrypotterandroid.domain.repo.CharactersRepo
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test

class CharacterRepositoryImpl {

    private val mockCharacterService: CharactersService = mockk()
    private val mockCharacterDao: CharacterDao = mockk()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val characterRepository: CharactersRepo = CharacterRepoImpl(mockCharacterDao, mockCharacterService, UnconfinedTestDispatcher())

    @Test
    fun `getAllCoins should emit non empty list`() = runTest {
        val mockCharacters = listOf(
            CharactersResponse(
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
            CharactersResponse(
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

        val fakeCharacterEntity = listOf(
            CharacterEntity(
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
            CharacterEntity(
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

        coEvery { mockCharacterService.getCharactersData() } returns mockCharacters
        coEvery { mockCharacterDao.getCharacter() } returns fakeCharacterEntity

        characterRepository.getCharacters().test {
            assertThat(awaitItem().size).isEqualTo(2)
            awaitComplete()
        }
    }

    @Test
    fun `getAllCharacters should emit non empty list even with network exception`() = runTest {
        val fakeCharacterEntity = listOf(
            CharacterEntity(
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
            CharacterEntity(
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
        coEvery { mockCharacterService.getCharactersData() } throws Exception()
        coEvery { mockCharacterDao.getCharacter() } returns fakeCharacterEntity

        characterRepository.getCharacters().test {
            assertThat(awaitItem().size).isEqualTo(2)
            awaitComplete()
        }
    }

}