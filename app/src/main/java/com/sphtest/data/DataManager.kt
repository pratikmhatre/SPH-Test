package com.sphtest.data

import com.sphtest.data.db.DbHelper
import com.sphtest.data.db.tables.VolumeDataTable
import com.sphtest.data.network.ApiHelper
import com.sphtest.data.network.models.VolumeData
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class DataManager @Inject constructor(val apiHelper: ApiHelper, val dbHelper: DbHelper) : DataManagerFace {
    override fun addSingleVolumeData(volumeData: VolumeDataTable) {
        dbHelper.addSingleVolumeData(volumeData = volumeData)
    }

    override fun addAllVolumes(arrayList: ArrayList<VolumeDataTable>) {
        dbHelper.addAllVolumes(arrayList = arrayList)
    }

    override fun getSingleVolume(id: Long): VolumeDataTable? {
        return dbHelper.getSingleVolume(id = id)
    }

    override fun getAllVolumes(): ArrayList<VolumeDataTable>? {
        return dbHelper.getAllVolumes()
    }

    override fun fetchVolumeData(resourceId: String): Single<Response<VolumeData>> {
        return apiHelper.fetchVolumeData(resourceId = resourceId)
    }

    override fun getVolumeListByYear(year: String): ArrayList<VolumeDataTable>? {
        return dbHelper.getVolumeListByYear(year)
    }
}