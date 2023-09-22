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
package com.mzazi.harrypotterandroid.utils

import androidx.annotation.StringRes
import com.mzazi.harrypotterandroid.R

@StringRes
fun ErrorType.toStringResource(): Int =
    when (this) {
        ErrorType.CLIENT -> R.string.error_client
        ErrorType.SERVER -> R.string.error_server
        ErrorType.IO_CONNECTION -> R.string.error_connection
        ErrorType.GENERIC -> R.string.error_generic
        ErrorType.UNAUTHORIZED -> R.string.error_unauthorized
        ErrorType.CHARACTER_NOT_FOUND -> R.string.error_character_not_found
    }