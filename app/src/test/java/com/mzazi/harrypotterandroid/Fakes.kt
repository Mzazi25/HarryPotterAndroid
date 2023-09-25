package com.mzazi.harrypotterandroid

import com.mzazi.harrypotterandroid.data.network.CharactersResponse

val fakeCharacterResponse = listOf(
    CharactersResponse(
        actor = "actor",
        alive = true,
        alternateNames = listOf(
            "name", "fake"
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
            "name1", "fake1"
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
    Character(
        actor = "actor",
        alive = true,
        alternateNames = listOf(
            "name", "fake"
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
    Character(
        actor = "actor1",
        alive = false,
        alternateNames = listOf(
            "name1", "fake1"
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

val characterSearch = Character(
    actor = "actor",
    alive = true,
    alternateNames = listOf(
        "name", "fake"
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
