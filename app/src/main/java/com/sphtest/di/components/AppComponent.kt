package com.sphtest.di.components

import android.content.Context
import com.sphtest.data.DataManagerFace
import com.sphtest.di.ApplicationContext
import com.sphtest.di.modules.AppModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    @ApplicationContext
    fun provideApplicationCOntext(): Context
    fun provideDataManagerFace():DataManagerFace
}