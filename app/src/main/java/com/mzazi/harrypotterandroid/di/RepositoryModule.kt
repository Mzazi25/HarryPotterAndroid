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

import com.mzazi.harrypotterandroid.data.repo.CharactersManager
import com.mzazi.harrypotterandroid.data.repo.remote.RemoteChacterRepoImpl
import com.mzazi.harrypotterandroid.domain.repo.CharactersRepo
import com.mzazi.harrypotterandroid.domain.repo.remote.RemoteCharactersRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {
    @Binds
    fun bindCharacterRepository(characterManager: CharactersManager): CharactersRepo

    @Binds
    fun bindRemoteCharacterRepository(remoteCharactersRepo: RemoteChacterRepoImpl): RemoteCharactersRepo
}