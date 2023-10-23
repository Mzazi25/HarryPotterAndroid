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

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.mzazi.harrypotterandroid.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    onSearchParamChange: (String) -> Unit,
    showSearchBar: Boolean = false
) {
    Surface {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
                text = stringResource(id = R.string.character_list_title),
                style = typography.headlineMedium
            )
            AnimatedVisibility(visible = showSearchBar) {
                Box(
                    modifier = Modifier
                        .padding(bottom = 4.dp)
                        .clip(CircleShape)
                        .background(colorScheme.onSurface.copy(alpha = .08F))
                        .fillMaxWidth()
                        .height(54.dp)
                ) {
                    var searchParam: String by remember { mutableStateOf("") }
                    val focusRequester = remember { FocusRequester() }
                    val focusManager = LocalFocusManager.current

                    TextField(
                        value = searchParam,
                        onValueChange = { value ->
                            onSearchParamChange(value)
                            searchParam = value
                        },
                        modifier = Modifier
                            .fillMaxSize()
                            .focusRequester(focusRequester = focusRequester),
                        singleLine = true,
                        placeholder = {
                            Text(
                                text = "Search...",
                                color = colorScheme.onSurface.copy(alpha = .32F)
                            )
                        },
                        colors = ExposedDropdownMenuDefaults.textFieldColors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        keyboardOptions = KeyboardOptions(
                            autoCorrect = true,
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Search
                        ),
                        keyboardActions = KeyboardActions(
                            onSearch = {
                                onSearchParamChange(searchParam)
                            }
                        ),
                        trailingIcon = {
                            Row {
                                AnimatedVisibility(visible = searchParam.trim().isNotEmpty()) {
                                    IconButton(onClick = {
                                        focusManager.clearFocus()
                                        searchParam = ""
                                        onSearchParamChange(searchParam)
                                    }) {
                                        Icon(
                                            imageVector = Icons.Default.Clear,
                                            contentDescription = null
                                        )
                                    }
                                }

                                IconButton(onClick = {
                                    onSearchParamChange(searchParam)
                                }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_search),
                                        contentDescription = null
                                    )
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarWithBackArrow(onBackPressed: (() -> Unit)? = null) {
    TopAppBar(
        title = { },
        navigationIcon = {
            BackArrowIcon(
                onClick = { onBackPressed?.invoke() }
            )
        }
    )
}

@Composable
fun BackArrowIcon(onClick: () -> Unit?) {
    IconButton(
        onClick = { onClick() },
        content = {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back Arrow"
            )
        }
    )
}