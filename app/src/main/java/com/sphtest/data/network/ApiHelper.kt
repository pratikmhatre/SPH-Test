package com.sphtest.data.network

import com.sphtest.data.network.models.VolumeData
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class ApiHelper @Inject constructor(val apiList: ApiList) : ApiHelperFace {
    override fun fetchVolumeData(resourceId: String): Single<Response<VolumeData>> {
        return apiList.fetchVolumeRecords(resourceId = resourceId)
    }
}
