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

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

/**
 * Utility object for creating an instance of OkHttpClient for making HTTP requests.
 */
object HttpClient {
    /**
     * Creates and configures an OkHttpClient instance with the necessary interceptors and timeouts.
     * @return An instance of OkHttpClient.
     */
    fun create(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(LoggingInterceptor.create())
            .connectTimeout(API_CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(API_READ_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }
}