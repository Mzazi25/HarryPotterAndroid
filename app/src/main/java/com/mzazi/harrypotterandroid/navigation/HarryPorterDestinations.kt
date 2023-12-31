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
package com.mzazi.harrypotterandroid.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

interface HarryPorterDestinations {
  val route: String

  object CharacterScreen : HarryPorterDestinations {
    override val route = "com.mzazi.harrypotterandroid.characterscreen"
  }
  object CharacterDetailScreen : HarryPorterDestinations {
    override val route = "com.mzazi.harrypotterandroid.deatailscreen"
    const val characterIdArgs = "characterId"
    val routeWithArgs = "$route/{$characterIdArgs}"
    val arguments = listOf(
      navArgument(characterIdArgs) { type = NavType.StringType },
    )
  }
}
