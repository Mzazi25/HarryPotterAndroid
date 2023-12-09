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
package com.mzazi.characters.characterdetails

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mzazi.designsystem.theme.Padding
import com.mzazi.designsystem.widgets.CharacterContent
import com.mzazi.designsystem.widgets.CharacterDetailsImage
import com.mzazi.designsystem.widgets.CharacterDetailsTitle
import com.mzazi.designsystem.widgets.ErrorDialog
import com.mzazi.designsystem.widgets.LoadingCharacterListShimmer
import com.mzazi.models.Characters
import com.mzazi.utils.getErrorMessage

@Composable
fun CharacterDetailsScreen(
  characterId: String,
  getCharacterDetails: (characterId: String) -> Unit,
  error: Throwable?,
  loading: Boolean,
  details: Characters?,
  onErrorAction: () -> Unit,
  onNavBack: () -> Unit,
) {
  LaunchedEffect(key1 = characterId) {
    getCharacterDetails(characterId)
  }

  val scrollState = rememberScrollState()

  Scaffold(
    topBar = {
      TopAppBar(
        navigationIcon = {
          IconButton(onClick = onNavBack) {
            Icon(Icons.Rounded.ArrowBack, "close")
          }
        },
        title = {
          Text(text = "Character Details")
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer),

      )
    },
  ) { padding ->
    Box(
      modifier = Modifier
        .fillMaxSize()
        .scrollable(state = scrollState, orientation = Orientation.Vertical)
        .padding(padding),
    ) {
      if (loading) {
        LoadingCharacterListShimmer(imageHeight = 200.dp)
      } else if (error != null) {
        ErrorDialog(
          text = stringResource(id = error.getErrorMessage()),
          dismissError = {
            onErrorAction()
            onNavBack()
          },
        )
      } else {
        Column {
          if (details != null) {
            CharacterDetailsImage(
              image = details.image,
              modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f),
            )
            CharacterDetailsTitle(
              title = details.name,
              modifier = Modifier
                .fillMaxWidth()
                .padding(
                  horizontal = Padding.Medium,
                  vertical = Padding.Small,
                ),
            )
            CharacterContent(
              title = details.actor,
              contentName = "Actor",
              modifier = Modifier
                .fillMaxWidth()
                .padding(
                  horizontal = Padding.Medium,
                  vertical = Padding.Small,
                ),
            )
            CharacterContent(
              title = details.gender,
              contentName = "Gender",
              modifier = Modifier
                .fillMaxWidth()
                .padding(
                  horizontal = Padding.Medium,
                  vertical = Padding.Small,
                ),
            )
            CharacterContent(
              title = details.hairColour,
              contentName = "Hair Color",
              modifier = Modifier
                .fillMaxWidth()
                .padding(
                  horizontal = Padding.Medium,
                  vertical = Padding.Small,
                ),
            )
            CharacterContent(
              title = details.eyeColour,
              contentName = "Eye Color",
              modifier = Modifier
                .fillMaxWidth()
                .padding(
                  horizontal = Padding.Medium,
                  vertical = Padding.Small,
                ),
            )
            CharacterContent(
              title = details.house,
              contentName = "House",
              modifier = Modifier
                .fillMaxWidth()
                .padding(
                  horizontal = Padding.Medium,
                  vertical = Padding.Small,
                ),
            )
            details.dateOfBirth?.let { dateOfBirth ->
              CharacterContent(
                title = dateOfBirth,
                contentName = "Date of Birth",
                modifier = Modifier
                  .fillMaxWidth()
                  .padding(
                    horizontal = Padding.Medium,
                    vertical = Padding.Small,
                  ),
              )
            }
          }
        }
      }
    }
  }
}
