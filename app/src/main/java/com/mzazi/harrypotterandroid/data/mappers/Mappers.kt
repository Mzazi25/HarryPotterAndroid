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
package com.mzazi.harrypotterandroid.data.mappers

import com.mzazi.harrypotterandroid.data.cache.model.CharacterEntity
import com.mzazi.harrypotterandroid.data.network.models.CharactersResponse
import com.mzazi.harrypotterandroid.domain.model.Characters

fun CharactersResponse.asCoreModel(): Characters =
    Characters(
        actor = actor,
        alive = alive,
        alternateNames = alternateNames,
        ancestry = ancestry,
        dateOfBirth = dateOfBirth,
        eyeColour = eyeColour,
        gender = gender,
        hairColour = hairColour,
        house = house,
        id = id,
        image = image,
        name = name,
        patronus = patronus,
        species = species,
        yearOfBirth = yearOfBirth
    )

fun CharactersResponse.toCoreEntity(): CharacterEntity =
    CharacterEntity(
        actor = actor,
        alive = alive,
        alternateNames = alternateNames,
        ancestry = ancestry,
        dateOfBirth = dateOfBirth,
        eyeColour = eyeColour,
        gender = gender,
        hairColour = hairColour,
        house = house,
        id = id,
        image = image,
        name = name,
        patronus = patronus,
        species = species,
        yearOfBirth = yearOfBirth
    )

fun CharacterEntity.asCoreModel(): Characters =
    Characters(
        actor = actor,
        alive = alive,
        alternateNames = alternateNames,
        ancestry = ancestry,
        dateOfBirth = dateOfBirth,
        eyeColour = eyeColour,
        gender = gender,
        hairColour = hairColour,
        house = house,
        id = id,
        image = image,
        name = name,
        patronus = patronus,
        species = species,
        yearOfBirth = yearOfBirth
    )

fun Characters.asCoreModel(): CharacterEntity =
    CharacterEntity(
        actor = actor,
        alive = alive,
        alternateNames = alternateNames,
        ancestry = ancestry,
        dateOfBirth = dateOfBirth,
        eyeColour = eyeColour,
        gender = gender,
        hairColour = hairColour,
        house = house,
        id = id,
        image = image,
        name = name,
        patronus = patronus,
        species = species,
        yearOfBirth = yearOfBirth
    )

//fun mapResponseCodeToThrowable(code: Int): Throwable = when (code) {
//    HttpURLConnection.HTTP_UNAUTHORIZED -> UnauthorizedException("Unauthorized access : $code")
//    in 400..499 -> ClientException("Client error : $code")
//    in 500..600 -> ServerException("Server error : $code")
//    else -> GenericException("Generic error : $code")
//}
//
//fun mapThrowableToErrorType(throwable: Throwable): ErrorType {
//    val errorType = when (throwable) {
//        is IOException -> ErrorType.IO_CONNECTION
//        is ClientException -> ErrorType.CLIENT
//        is ServerException -> ErrorType.SERVER
//        is UnauthorizedException -> ErrorType.UNAUTHORIZED
//        else -> ErrorType.GENERIC
//    }
//    return errorType
//}