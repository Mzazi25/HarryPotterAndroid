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
package com.mzazi.domain.di

import com.mzazi.data.repo.CharactersRepo
import com.mzazi.domain.usecases.CharacterDetailsUseCase
import com.mzazi.domain.usecases.CharacterListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
internal class UseCaseModule {

  @ViewModelScoped
  @Provides
  fun providesCharacterListUseCase(
    repository: CharactersRepo,
  ): CharacterListUseCase {
    return CharacterListUseCase(repository)
  }

  @ViewModelScoped
  @Provides
  fun providesCharacterDetailsUseCase(
    repository: CharactersRepo,
  ): CharacterDetailsUseCase {
    return CharacterDetailsUseCase(repository)
  }
}
