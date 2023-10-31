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
package com.mzazi.harrypotterandroid.data.cache.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.mzazi.harrypotterandroid.data.cache.converter.Converters
import com.mzazi.harrypotterandroid.data.cache.dao.CharacterDao
import com.mzazi.harrypotterandroid.data.cache.model.CharacterEntity
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CharacterDatabaseTest {
    private lateinit var dao: CharacterDao
    private lateinit var db: CharacterDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, CharacterDatabase::class.java
        )
            .allowMainThreadQueries() //Todo("Delete me")
            .addTypeConverter(Converters())

            .build()
        dao = db.characterDao()
    }

    @Test
    fun `test adding empty list followed by valid data`() = runTest {
        dao.insertCharacters(emptyList())

        assertThat(dao.getCharacter()).isEmpty()

        dao.insertCharacters(
            listOf(
                CharacterEntity(
                    actor = "actor1",
                    alive = false,
                    alternateNames = listOf(
                        "name1",
                        "fake1"
                    ),
                    ancestry = "ancestry1",
                    dateOfBirth = null,
                    eyeColour = "eyeColor1",
                    gender = "Famale",
                    hairColour = "Red",
                    house = "Stark1",
                    id = "id1",
                    image = "images",
                    name = "names",
                    patronus = "patronusss",
                    species = "species1",
                    yearOfBirth = null
                )
            )
        )

        assertThat(dao.getCharacter().size).isEqualTo(1)
    }

    @After
    fun tearDown() {
        db.close()
    }


}