package com.mzazi.harrypotterandroid.designsystem.widgets/*
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
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.mzazi.harrypotterandroid.designsystem.theme.Padding

@Composable
fun CharacterListName(title: String, modifier: Modifier = Modifier) {
    Text(
        text = title,
        modifier = modifier,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1
    )
}

@Composable
fun CharacterListAncestry(title: String, modifier: Modifier = Modifier) {
    Text(
        text = (if (title.isNotBlank() || title.isNotEmpty())"Ancestry - $title" else "Ancestry -N/A"),
        modifier = modifier,
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.Normal,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun CharacterPatronus(title: String, modifier: Modifier = Modifier) {
    Text(
        text = (if (title.isNotBlank() || title.isNotEmpty())"Patronus - $title" else "Patronus -N/A"),
        modifier = modifier,
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.Normal,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun InfoText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier
            .padding(Padding.Medium),
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Medium,
        style = MaterialTheme.typography.bodyMedium
    )
}

@Composable
fun CharacterDetailsTitle(title: String, modifier: Modifier = Modifier) {
    Text(
        text = title,
        modifier = modifier,
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun CharactersAlternativeNames(names: List<String>, modifier: Modifier = Modifier) {
    names.forEach {
        Text(
            text = it,
            modifier = modifier,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun CharacterContent(title: String, contentName: String, modifier: Modifier = Modifier) {
    Text(
        text = (if (title.isNotBlank() || title.isNotEmpty()) "$contentName - $title" else "$contentName - N/A"),
        modifier = modifier,
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.Medium
    )
}