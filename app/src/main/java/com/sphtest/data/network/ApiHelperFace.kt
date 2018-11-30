package com.sphtest.data.network

import com.sphtest.data.network.models.VolumeData
import io.reactivex.Single
import retrofit2.Response

interface ApiHelperFace {
    fun fetchVolumeData(resourceId:String): Single<Response<VolumeData>>
}