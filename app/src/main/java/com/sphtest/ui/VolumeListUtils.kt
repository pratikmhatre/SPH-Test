package com.sphtest.ui

import android.support.v7.util.DiffUtil
import com.sphtest.data.db.tables.VolumeDataTable
import com.sphtest.data.network.models.CustomModel

class VolumeListUtils(val oldList: ArrayList<CustomModel>, val newList: ArrayList<CustomModel>) : DiffUtil.Callback() {
    override fun areItemsTheSame(p0: Int, p1: Int): Boolean {
        return oldList[p0].year == newList[p1].year
    }

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areContentsTheSame(p0: Int, p1: Int) = true
}
