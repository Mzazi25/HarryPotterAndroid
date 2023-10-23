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
package com.mzazi.harrypotterandroid

import com.mzazi.harrypotterandroid.data.network.models.CharactersResponse
import com.mzazi.harrypotterandroid.domain.model.Characters

val fakeCharacterResponse = listOf(
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
val fakeMappedCharacters = listOf(
    Characters(
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
    Characters(
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

val characterSearch = Characters(
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
)