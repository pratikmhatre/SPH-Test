package com.sphtest.di.modules

import android.content.Context
import com.sphtest.Utils
import com.sphtest.application.MyApplication
import com.sphtest.data.DataManager
import com.sphtest.data.DataManagerFace
import com.sphtest.data.network.ApiList
import com.sphtest.di.ApplicationContext
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class AppModule(val myApplication: MyApplication) {
    @ApplicationContext
    @Provides
    fun provideApplicationContext(): Context {
        return myApplication
    }

    @Provides
    fun provideRetrofit(): ApiList {
        val retrofit = Retrofit.Builder().baseUrl(Utils.BASE_URL).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
        return retrofit.create(ApiList::class.java)
    }

    @Provides
    fun provideDataManagerFace(dataManager: DataManager): DataManagerFace {
        return dataManager
    }
}