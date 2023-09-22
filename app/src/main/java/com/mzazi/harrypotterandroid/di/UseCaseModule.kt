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

import com.mzazi.harrypotterandroid.domain.usecases.GetCharacterDetailsImplUseCase
import com.mzazi.harrypotterandroid.domain.usecases.GetCharacterDetailsUseCase
import com.mzazi.harrypotterandroid.domain.usecases.GetCharacterListImplUseCase
import com.mzazi.harrypotterandroid.domain.usecases.GetCharacterListUseCase
import com.mzazi.harrypotterandroid.domain.usecases.SearchCharacterImplUseCase
import com.mzazi.harrypotterandroid.domain.usecases.SearchCharacterUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {
    @Binds
    fun bindGetCharacterListUseCase(getCharacterListImplUseCase: GetCharacterListImplUseCase): GetCharacterListUseCase

    @Binds
    fun bindGetCharacterDetailsUseCase(getCharacterDetailsImplUseCase: GetCharacterDetailsImplUseCase): GetCharacterDetailsUseCase

    @Binds
    fun bindSearchCharactersUseCase(searchCharacterImplUseCase: SearchCharacterImplUseCase): SearchCharacterUseCase
}