package com.sphtest.data.network.models

import com.google.gson.annotations.SerializedName

class VolumeData {
    var success: String? = null
    var result: Result? = null

    inner class Result {
        var records: ArrayList<Record>? = null

        inner class Record {
            @SerializedName("_id")
            var id: Int? = null
            var quarter: String? = null
            var volume_of_mobile_data: String? = null
        }
    }
}