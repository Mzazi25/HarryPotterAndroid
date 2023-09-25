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
package com.mzazi.harrypotterandroid.di

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.mzazi.harrypotterandroid.BuildConfig
import com.mzazi.harrypotterandroid.data.network.CharactersService
import com.mzazi.harrypotterandroid.utils.HARRY_POTTER_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ClientModule {

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun provideRetrofit(
        @ApplicationContext context: Context
    ): Retrofit {
        val okHttpClient = provideOkhttpClient(context = context)
        val json = providesJson()
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(HARRY_POTTER_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }

    @Provides
    @Singleton
    fun providesCharactersService(retrofit: Retrofit): CharactersService =
        retrofit.create(CharactersService::class.java)

    private fun providesJson(): Json = Json { ignoreUnknownKeys = true }

    private fun provideOkhttpClient(
        context: Context
    ): OkHttpClient {
        val loggingInterceptor = provideLoggingInterceptor()
        return OkHttpClient.Builder()
            .connectTimeout(60L, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return HttpLoggingInterceptor().also {
            it.level = level
        }
    }
}