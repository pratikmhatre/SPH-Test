package com.sphtest.data.network

import com.sphtest.data.network.models.VolumeData
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiList {
    @GET("/api/action/datastore_search")
    fun fetchVolumeRecords(@Query("resource_id") resourceId: String): Single<Response<VolumeData>>
}