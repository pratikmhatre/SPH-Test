package com.sphtest.data.db

import android.content.Context
import com.sphtest.application.MyApplication
import com.sphtest.data.db.tables.VolumeDataTable
import com.sphtest.data.db.tables.VolumeDataTableDao
import com.sphtest.di.ApplicationContext
import javax.inject.Inject

class DbHelper @Inject constructor(@ApplicationContext val context: Context) : DbHelperFace {
    private val volumeDao = (context as MyApplication).daoSession.volumeDataTableDao
    override fun addSingleVolumeData(volumeData: VolumeDataTable) {
        volumeDao.insert(volumeData)
    }

    override fun addAllVolumes(arrayList: ArrayList<VolumeDataTable>) {
        volumeDao.insertInTx(arrayList)
    }

    override fun getSingleVolume(id: Long): VolumeDataTable? {
        val volumesList = volumeDao.queryBuilder().where(VolumeDataTableDao.Properties.Id.eq(id)).list()
        return if (volumesList.isNotEmpty()) {
            volumesList[0]
        } else {
            null
        }
    }

    override fun getAllVolumes(): ArrayList<VolumeDataTable>? {


        val volumesList = volumeDao.loadAll()
        return ArrayList(volumesList)
    }

    override fun getVolumeListByYear(year: String): ArrayList<VolumeDataTable>? {
        val volumesList = volumeDao.queryBuilder().where(VolumeDataTableDao.Properties.Year.eq(year)).list()
        return ArrayList(volumesList)
    }
}