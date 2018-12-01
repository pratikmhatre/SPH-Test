package com.sphtest.ui

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.sphtest.data.DataManagerFace
import java.lang.IllegalStateException
import javax.inject.Inject

class ListModelFactory @Inject constructor(val dataManagerFace: DataManagerFace, val listRepository: ListRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
            return ListViewModel(dataManagerFace, listRepository) as T
        }
        throw IllegalStateException("no class found")
    }
}