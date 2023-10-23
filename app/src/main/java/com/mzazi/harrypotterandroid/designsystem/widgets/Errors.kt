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
package com.mzazi.harrypotterandroid.ui.widgets

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.mzazi.harrypotterandroid.ui.theme.Padding

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