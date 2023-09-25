package com.mzazi.harrypotterandroid.data.utils

import com.mzazi.harrypotterandroid.domain.models.Characters


object CharactersPaginationStore {

    private val characters = mutableSetOf<Characters>()

    fun addCharacters(characters: List<Characters>) {
        this.characters.addAll(characters)
    }

    fun clearCharacters() {
        characters.clear()
    }

    fun getCharacters(): List<Characters> = characters.toList()

}
