package com.sphtest.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.sphtest.data.DataManagerFace
import com.sphtest.data.db.tables.VolumeDataTable
import com.sphtest.data.network.models.CustomModel
import javax.inject.Inject

class ListViewModel @Inject constructor(val dataManagerFace: DataManagerFace, val listRepository: ListRepository) : ViewModel() {
    fun getVolumeDataList(): LiveData<ArrayList<VolumeDataTable>> {
        if (dataManagerFace.getAllVolumes() != null && dataManagerFace.getAllVolumes()!!.isNotEmpty()) {
            val mutableData = MutableLiveData<ArrayList<VolumeDataTable>>()
            mutableData.value = dataManagerFace.getAllVolumes()
            return mutableData
        } else {
            return listRepository.getLisData()
        }
    }

    fun doCalculations(year: String): LiveData<CustomModel> {
        val mutableData = MutableLiveData<CustomModel>()
        val list = dataManagerFace.getVolumeListByYear(year)

        if (list == null || list.isEmpty()) {
            return mutableData
        }

        //adding all volumes
        var totalVolume = 0
        list.forEach {
            totalVolume += it.volume.toInt()
        }


        //parsing individual quarter datas
        val tempArrayList = ArrayList<CustomModel.Quarter>()
        for (i in list.indices) {
            val quarter = CustomModel.Quarter()
            quarter.isDecreased = false
            quarter.value = list[i].volume
            tempArrayList.add(quarter)
        }

        val customModel = CustomModel()
        customModel.year = list[0].year
        customModel.totalVolume = totalVolume.toString()
        customModel.quartersList = tempArrayList

        mutableData.value = customModel


        return mutableData
    }
}