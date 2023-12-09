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
package com.mzazi.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharactersResponse(
  @SerialName("actor") val actor: String,
  @SerialName("alive") val alive: Boolean,
  @SerialName("alternate_names") val alternateNames: List<String>,
  @SerialName("ancestry")val ancestry: String,
  @SerialName("dateOfBirth") val dateOfBirth: String?,
  @SerialName("eyeColour") val eyeColour: String,
  @SerialName("gender") val gender: String,
  @SerialName("hairColour") val hairColour: String,
  @SerialName("house") val house: String,
  @SerialName("id") val id: String,
  @SerialName("image") val image: String,
  @SerialName("name") val name: String,
  @SerialName("patronus") val patronus: String,
  @SerialName("species") val species: String,
  @SerialName("yearOfBirth") val yearOfBirth: Int?,
)
