package com.sphtest.data.db

import com.sphtest.data.db.tables.VolumeDataTable
import com.sphtest.data.network.models.VolumeData

interface DbHelperFace {
    fun addSingleVolumeData(volumeData: VolumeDataTable)
    fun addAllVolumes(arrayList: ArrayList<VolumeDataTable>)
    fun getSingleVolume(id: Long):VolumeDataTable?
    fun getAllVolumes(): ArrayList<VolumeDataTable>?
}