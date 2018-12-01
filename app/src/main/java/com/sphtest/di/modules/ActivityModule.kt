package com.sphtest.di.modules

import android.support.v7.app.AppCompatActivity
import com.sphtest.data.DataManagerFace
import com.sphtest.ui.ListActivity
import com.sphtest.ui.ListModelFactory
import com.sphtest.ui.ListRepository
import com.sphtest.ui.VolumeListAdapter
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(val appCompatActivity: AppCompatActivity) {
    @Provides
    fun provideListModelfactory(dataManagerFace: DataManagerFace, listRepository: ListRepository): ListModelFactory {
        return ListModelFactory(dataManagerFace, listRepository)
    }

    @Provides
    fun provideListAdapter(): VolumeListAdapter {
        return VolumeListAdapter(listActivity = appCompatActivity as ListActivity, arrayList = ArrayList())
    }
}

