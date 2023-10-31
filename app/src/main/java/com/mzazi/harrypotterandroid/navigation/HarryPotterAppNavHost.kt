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

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mzazi.harrypotterandroid.features.characterdetails.CharacterDetailsScreen
import com.mzazi.harrypotterandroid.features.characterdetails.CharacterDetailsViewModel
import com.mzazi.harrypotterandroid.features.characters.CharacterScreen
import com.mzazi.harrypotterandroid.features.characters.CharacterScreenViewModel
import com.mzazi.harrypotterandroid.navigation.HarryPorterDestinations.CharacterScreen
import com.mzazi.harrypotterandroid.navigation.HarryPorterDestinations.CharacterDetailScreen
import timber.log.Timber

@Composable
fun HarryPotterAppNavHost(
    navController: NavHostController,
) {
    NavHost(navController = navController, startDestination = CharacterScreen.route) {
        composable(route = CharacterScreen.route) {
            val viewModel: CharacterScreenViewModel = hiltViewModel()
            val state by viewModel.state.collectAsStateWithLifecycle()
            CharacterScreen(
                isLoading = state.isLoading,
                characters = state.characters,
                error = state.error,
                onSearch = viewModel::onSearch,
                onErrorActionClicked = viewModel::dismissError,
                onCharacterSelected = { character ->
                    navController.navigateToDetailScreen(character.id)
                }
            )
        }
        composable(
            route = CharacterDetailScreen.routeWithArgs,
            arguments = CharacterDetailScreen.arguments,
        ) { navBackStackEntry ->
            val characterId =
                navBackStackEntry.arguments?.getString(CharacterDetailScreen.characterIdArgs)!!
            val viewModel: CharacterDetailsViewModel = hiltViewModel()
            val state by viewModel.state.collectAsStateWithLifecycle()
            Timber.e("Details---------${state.characterDetails}")
            CharacterDetailsScreen(
                characterId = characterId,
                getCharacterDetails = { assetId -> viewModel.getCharacterId(assetId) },
                loading = state.isLoading,
                error = state.error,
                details = state.characterDetails,
                onErrorAction = viewModel::dismissError,
                onNavBack = { navController.navigateUp() }
            )
        }
    }
}


private fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }

fun NavHostController.navigateToDetailScreen(characterId: String) {
    this.navigateSingleTopTo("${CharacterDetailScreen.route}/$characterId")
}