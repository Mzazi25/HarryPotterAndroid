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
package com.mzazi.harrypotterandroid.features.characters

import CharacterListAncestry
import CharacterListName
import com.mzazi.harrypotterandroid.designsystem.widgets.CharacterListPoster
import CharacterPatronus
import InfoText
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mzazi.harrypotterandroid.R
import com.mzazi.harrypotterandroid.designsystem.theme.Padding
import com.mzazi.harrypotterandroid.designsystem.widgets.ErrorScreen
import com.mzazi.harrypotterandroid.designsystem.widgets.LoadingScreen
import com.mzazi.harrypotterandroid.designsystem.widgets.StatusBar
import com.mzazi.harrypotterandroid.designsystem.widgets.TopBar
import com.mzazi.harrypotterandroid.domain.models.Characters

@Composable
fun CharacterScreen(
    state: CharacterScreenState,
    onLoadMore: () -> Unit,
    onSearch: (String) -> Unit,
    onErrorActionClicked: () -> Unit,
    onCharacterSelected: (Characters) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        val lazyColumnState = rememberLazyListState()
        TopBar(
            onSearchParamChange = { query ->
                onSearch(query)
            },
            showSearchBar = lazyColumnState.isScrollingUp()
        )
        if (state.isLoading) {
            LoadingScreen()
        } else if (state.errorMsg != null) {
            Spacer(modifier = Modifier.weight(0.5f))
            ErrorScreen(errorMsg = state.errorMsg, errorActionTitle = R.string.error_retry) {
                onErrorActionClicked()
            }
            Spacer(modifier = Modifier.weight(0.5f))
        } else {
            if (state.characters.isEmpty()) {
                EmptyListScreen()
            } else {
                CharacterListItems(
                    state = state,
                    onCharacterSelected = onCharacterSelected,
                    onLoadMore = onLoadMore
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun CharacterListItems(
    state: CharacterScreenState,
    onCharacterSelected: (Characters) -> Unit,
    onLoadMore: () -> Unit
) {
    val listState = rememberLazyListState()

    LazyColumn(state = listState) {
        items(state.characters) { character ->
            CharacterItem(character = character, modifier = Modifier.animateItemPlacement()) { selectedCharacter ->
                onCharacterSelected(selectedCharacter)
            }
        }
    }

    val scrollContext = rememberScrollContext(listState)

    if (scrollContext.isBottom && !state.isSearching) {
        onLoadMore()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CharacterItem(
    character: Characters,
    modifier: Modifier = Modifier,
    onCharacterSelected: (Characters) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(Padding.Small),
        onClick = { onCharacterSelected(character) }
    ) {
        Row {
            CharacterListPoster(
                posterUrl = character.image,
                modifier = Modifier
                    .size(120.dp)
            )
            Column {
                CharacterListName(
                    title = character.name,
                    modifier = Modifier.padding(top = Padding.Medium, start = Padding.Medium)
                )
                CharacterListAncestry(
                    title = character.ancestry,
                    modifier = Modifier.padding(top = Padding.Small, start = Padding.Medium)
                )
                CharacterPatronus(
                    title = character.patronus,
                    modifier = Modifier.padding(bottom = Padding.Medium, start = Padding.Medium)
                )
                StatusBar(
                    text = character.alive,
                    modifier = Modifier
                        .padding(start = Padding.Medium)
                )
            }
        }
    }
}

@Composable
private fun ColumnScope.EmptyListScreen() {
    Spacer(modifier = Modifier.weight(0.5f))
    InfoText(
        text = stringResource(R.string.error_character_not_found),
        modifier = Modifier.align(Alignment.CenterHorizontally)
    )
    Spacer(modifier = Modifier.weight(0.5f))
}