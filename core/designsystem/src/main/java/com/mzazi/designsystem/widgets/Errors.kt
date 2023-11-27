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
package com.mzazi.designsystem.widgets

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.mzazi.core.designsystem.R
import com.mzazi.designsystem.theme.Padding

@Composable
fun ErrorScreen(
    @StringRes errorMsg: Int,
    @StringRes errorActionTitle: Int,
    onErrorActionClicked: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.weight(0.5f))
        Text(
            text = stringResource(id = errorMsg),
            modifier = Modifier
                .padding(Padding.Medium)
                .align(Alignment.CenterHorizontally)
        )
        Button(
            onClick = { onErrorActionClicked() },
            modifier = Modifier
                .padding(Padding.Medium)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(text = stringResource(id = errorActionTitle))
        }
        Spacer(modifier = Modifier.weight(0.5f))
    }
}

@Composable
fun ErrorDialog(
    text: String,
    dismissError: () -> Unit
) {
    AlertDialog(
        onDismissRequest = dismissError,
        title = { Text(stringResource(id = R.string.error)) },
        text = { Text(text) },
        confirmButton = {
            TextButton(onClick = dismissError) {
                Text(stringResource(id = R.string.okay))
            }
        }
    )
}

@Composable
fun NothingHere() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Text(
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.size_8))
                    .align(Alignment.CenterHorizontally),
                text = stringResource(R.string.nothing_illustration),
                style = TextStyle(fontSize = dimensionResource(id = R.dimen.font_size_55).value.sp)
            )
            Text(
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.size_8))
                    .align(Alignment.CenterHorizontally),
                text = stringResource(R.string.there_is_no_data_to_display_at_the_moment),
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}