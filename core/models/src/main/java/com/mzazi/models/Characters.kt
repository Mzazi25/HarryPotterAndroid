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
package com.mzazi.models

data class Characters(
  val actor: String,
  val alive: Boolean,
  val alternateNames: List<String>,
  val ancestry: String,
  val dateOfBirth: String?,
  val eyeColour: String,
  val gender: String,
  val hairColour: String,
  val house: String,
  val id: String,
  val image: String,
  val name: String,
  val patronus: String,
  val species: String,
  val yearOfBirth: Int?,
)
