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

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mzazi.harrypotterandroid.features.characterdetails.CharacterDetailsIntent
import com.mzazi.harrypotterandroid.features.characterdetails.CharacterDetailsScreen
import com.mzazi.harrypotterandroid.features.characterdetails.CharacterDetailsViewModel
import com.mzazi.harrypotterandroid.features.characters.CharacterScreen
import com.mzazi.harrypotterandroid.features.characters.CharacterScreenIntent
import com.mzazi.harrypotterandroid.features.characters.CharacterScreenViewModel

@Composable
fun HarryPotterAppNavHost(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Destinations.CHARACTERS.route) {
        composable(route = Destinations.CHARACTERS.route) {
            val viewModel = hiltViewModel<CharacterScreenViewModel>()
            viewModel.state.collectAsState().value.let { state ->
                CharacterScreen(
                    state = state,
                    onLoadMore = {
                        viewModel.processIntent(CharacterScreenIntent.LoadLatestCharacters)
                    },
                    onSearch = { query ->
                        viewModel.processIntent(CharacterScreenIntent.DisplaySearchScreen)
                        viewModel.processIntent(CharacterScreenIntent.SearchCharacter(query))
                    },
                    onErrorActionClicked = {
                        viewModel.processIntent(CharacterScreenIntent.LoadLatestCharacters)
                    },
                    onCharacterSelected = { character ->
                        navController.navigate("${Destinations.CHARACTERS_DETAILS.route}/${character.id}")
                    }
                )
            }
        }
        composable(route = Destinations.CHARACTERS_DETAILS.route + "/{characterId}") { navBackStackEntry ->
            val characterId = navBackStackEntry.arguments?.getString("characterId")
            requireNotNull(characterId)
            val viewModel = hiltViewModel<CharacterDetailsViewModel>()
            viewModel.processIntent(CharacterDetailsIntent.LoadCharacterDetail(characterId))
            viewModel.state.collectAsState().value.let { state ->
                CharacterDetailsScreen(
                    state = state,
                    onErrorAction = {
                        viewModel.processIntent(CharacterDetailsIntent.LoadCharacterDetail(characterId))
                    },
                    onBackPressed = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}

// TODO refactor to use sealed class and support arguments
enum class Destinations(val route: String) {
    CHARACTERS("characters"),
    CHARACTERS_DETAILS("characters_details")
}