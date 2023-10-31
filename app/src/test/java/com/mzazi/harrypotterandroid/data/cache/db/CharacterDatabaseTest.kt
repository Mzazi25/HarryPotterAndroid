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