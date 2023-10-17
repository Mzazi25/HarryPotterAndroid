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
package com.mzazi.harrypotterandroid.data.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class CharacterEntity(
    @PrimaryKey
    val id: String,
    val actor: String,
    val alive: Boolean,
    @ColumnInfo(name = "alternate_names")
    val alternateNames: List<String>,
    val ancestry: String,
    @ColumnInfo(name = "date_of_birth")
    val dateOfBirth: String?,
    @ColumnInfo(name = "eye_colour")
    val eyeColour: String,
    val gender: String,
    @ColumnInfo(name = "hair_colour")
    val hairColour: String,
    val house: String,
    val image: String,
    val name: String,
    val patronus: String,
    val species: String,
    @ColumnInfo(name = "year_of_birth")
    val yearOfBirth: Int?
)