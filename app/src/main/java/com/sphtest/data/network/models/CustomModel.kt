package com.sphtest.data.network.models

class CustomModel {
    var totalVolume: String? = null
    var year: String? = null
    var quartersList: ArrayList<Quarter>? = null

    class Quarter {
        var value: String? = null
        var isDecreased: Boolean = false
    }
}