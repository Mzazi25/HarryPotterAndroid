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
package com.mzazi.characters.characters

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mzazi.designsystem.widgets.CharacterListAncestry
import com.mzazi.designsystem.widgets.CharacterListName
import com.mzazi.designsystem.widgets.CharacterListPoster
import com.mzazi.designsystem.widgets.ErrorDialog
import com.mzazi.designsystem.widgets.LoadingCharacterListShimmer
import com.mzazi.designsystem.widgets.NothingHere
import com.mzazi.designsystem.widgets.SearchAppBar
import com.mzazi.features.characters.R
import com.mzazi.models.Characters
import com.mzazi.utils.getErrorMessage

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CharacterScreen(
  isLoading: Boolean,
  characters: List<Characters>,
  error: Throwable?,
  onSearch: (String) -> Unit,
  onErrorActionClicked: () -> Unit,
  onCharacterSelected: (Characters) -> Unit,
) {
  var shouldShowSearchBar by rememberSaveable { mutableStateOf(false) }
  var searchQuery by rememberSaveable { mutableStateOf("") }

  Scaffold(
    topBar = {
      AnimatedVisibility(
        visible = !shouldShowSearchBar,
        enter = slideInHorizontally(
          initialOffsetX = { fullWidth -> fullWidth },
        ),
        exit = slideOutHorizontally(
          targetOffsetX = { fullWidth -> fullWidth },
        ),
      ) {
        TopAppBar(
          title = {
            Text(text = "HarryPorter")
          },
          colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
          actions = {
            IconButton(
              onClick = { shouldShowSearchBar = true },
              modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(end = dimensionResource(R.dimen.size_8)),
            ) {
              Icon(Icons.Rounded.Search, "search")
            }
          },
        )
      }
      AnimatedVisibility(
        visible = shouldShowSearchBar,
        enter = slideInHorizontally(
          initialOffsetX = { fullWidth -> fullWidth },
        ),
        exit = slideOutHorizontally(
          targetOffsetX = { fullWidth -> fullWidth },
        ),
      ) {
        SearchAppBar(
          query = searchQuery,
          onBackClicked = {
            searchQuery = ""
            shouldShowSearchBar = false
          },
          onClearClicked = { searchQuery = "" },
          onQueryChanged = { query -> searchQuery = query },
          onSearch = onSearch,
        )
      }
    },
  ) { paddingValues ->
    Box(
      modifier = Modifier
        .background(color = MaterialTheme.colorScheme.surface)
        .padding(paddingValues),
    ) {
      if (isLoading && characters.isEmpty()) {
        LoadingCharacterListShimmer(
          imageHeight = dimensionResource(R.dimen.size200),
        )
      } else if (characters.isEmpty()) {
        NothingHere()
      } else {
        LazyColumn(
          state = rememberLazyListState(),
        ) {
          items(
            items = characters,
          ) { character ->
            CharacterItem(
              character = character,
              onCharacterSelected = { selectedCharacter ->
                onCharacterSelected(selectedCharacter)
              },
            )
          }
        }
      }
      error?.let {
        ErrorDialog(
          text = stringResource(error.getErrorMessage()),
          dismissError = onErrorActionClicked,
        )
      }
    }
  }
}

@Composable
private fun CharacterItem(
  character: Characters,
  onCharacterSelected: (Characters) -> Unit,
) {
  Surface(
    onClick = { onCharacterSelected(character) },
    shape = MaterialTheme.shapes.large,
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(16.dp),
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
    ) {
      CharacterListPoster(
        posterUrl = character.image,
        modifier = Modifier
          .size(58.dp)
          .clip(CircleShape),
      )
      Column {
        CharacterListName(
          title = character.name,
        )
        Spacer(Modifier.height(4.dp))
        CharacterListAncestry(
          title = character.ancestry,
        )
      }
    }
  }
}
