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
import androidx.room.Room
import com.mzazi.harrypotterandroid.data.cache.CharacterDatabase
import com.mzazi.harrypotterandroid.data.cache.Converters
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun providesCharacterDatabase(@ApplicationContext context: Context): CharacterDatabase =
        Room.databaseBuilder(
            context = context,
            klass = CharacterDatabase::class.java,
            name = "Character_database"
        ).addTypeConverter(Converters())
            .build()

    @Provides
    @Singleton
    fun providesCharacterDao(db: CharacterDatabase) = db.characterDao()
}