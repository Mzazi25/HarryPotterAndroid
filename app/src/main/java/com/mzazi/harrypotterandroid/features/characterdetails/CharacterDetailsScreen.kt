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
package com.mzazi.harrypotterandroid.features.characterdetails

import CharacterDetailsImage
import CharacterDetailsTitle
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mzazi.harrypotterandroid.R
import com.mzazi.harrypotterandroid.designsystem.theme.Padding
import com.mzazi.harrypotterandroid.designsystem.widgets.ErrorScreen
import com.mzazi.harrypotterandroid.designsystem.widgets.LoadingScreen
import com.mzazi.harrypotterandroid.designsystem.widgets.TopBarWithBackArrow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailsScreen(
    state: CharacterDetailsState,
    onErrorAction: () -> Unit,
    onBackPressed: () -> Unit
) {
    Scaffold(
        topBar = {
            TopBarWithBackArrow(onBackPressed = onBackPressed)
        }
    ) { padding ->
        AnimatedContent(targetState = state, label = "Character Detail Animate") { state ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(padding)
            ) {
                if (state.isLoading) {
                    LoadingScreen()
                } else if (state.errorMsg != null) {
                    ErrorScreen(errorMsg = state.errorMsg, errorActionTitle = R.string.error_retry) {
                        onErrorAction()
                    }
                } else {
                    CharacterDetailsContent(state)
                }
            }
        }
    }
}

@Composable
private fun CharacterDetailsContent(state: CharacterDetailsState) {
    state.image?.let { image ->
        CharacterDetailsImage(
            image = image,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
    state.name?.let { name ->
        CharacterDetailsTitle(
            title = name,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = Padding.Medium,
                    vertical = Padding.Small
                )
        )
    }
}