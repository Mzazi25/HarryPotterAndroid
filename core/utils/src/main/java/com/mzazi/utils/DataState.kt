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

/**
 * A generic data class representing the state of an operation with optional data, error, and loading indicators.
 * @property data The data result of the operation.
 * @property error The error that occurred during the operation.
 * @property loading Indicates whether the operation is in progress.
 */
data class DataState<out T>(
  val data: T? = null,
  val error: Throwable? = null,
  val loading: Boolean = false,
) {
  companion object {

    /**
     * Creates a success state with the specified data.
     * @param data The data result.
     * @return A DataState instance representing the success state with data.
     */
    fun <T> success(data: T): DataState<T> {
      return DataState(data = data)
    }

    /**
     * Creates an error state with the specified error.
     * @param error The error that occurred.
     * @return A DataState instance representing the error state with the error.
     */
    fun <T> error(error: Throwable?): DataState<T> {
      return DataState(error = error)
    }

    /**
     * Creates a loading state.
     * @return A DataState instance representing the loading state.
     */
    fun <T> loading(): DataState<T> = DataState(loading = true)
  }
}
