package com.sphtest.di.components

import com.sphtest.di.PerActivity
import com.sphtest.di.modules.ActivityModule
import com.sphtest.ui.ListActivity
import dagger.Component

@PerActivity
@Component(dependencies = [AppComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(listActivity: ListActivity)
}