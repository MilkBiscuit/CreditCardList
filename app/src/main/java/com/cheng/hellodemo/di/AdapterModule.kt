package com.cheng.hellodemo.di

import com.cheng.hellodemo.adapter.RestApiRemote
import com.cheng.hellodemo.domain.adapterinterface.IRestApiRemote
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RestApiRemoteModule {
    @Binds
    abstract fun bindRestApiRemote(inst: RestApiRemote): IRestApiRemote
}
