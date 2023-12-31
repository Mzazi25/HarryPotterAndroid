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
package com.mzazi.utils

import androidx.annotation.StringRes
import com.mzazi.core.utils.R
import retrofit2.HttpException
import java.io.IOException

/**
 * Extension functions for the throwable class
 */
@StringRes
fun Throwable.getErrorMessage() = when (this) {
  is HttpException -> {
    when (this.code()) {
      404 -> R.string.resource_not_found
      429 -> R.string.too_many_requests
      500 -> R.string.server_error
      else -> R.string.something_went_wrong_network
    }
  }

  is IOException -> {
    R.string.no_internet
  }

  else -> {
    R.string.something_went_wrong_please_try_again
  }
}
