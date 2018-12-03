package com.sphtest.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.sphtest.data.DataManagerFace
import com.sphtest.data.db.tables.VolumeDataTable
import com.sphtest.data.network.models.CustomModel
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListViewModel @Inject constructor(val dataManagerFace: DataManagerFace, val listRepository: ListRepository) : ViewModel() {
    val TAG = "ListViewModel"
    fun getVolumeDataList(isRefresh: Boolean): LiveData<ArrayList<VolumeDataTable>> {
        if (!isRefresh && dataManagerFace.getAllVolumes() != null && dataManagerFace.getAllVolumes()!!.isNotEmpty()) {
            val mutableData = MutableLiveData<ArrayList<VolumeDataTable>>()
            mutableData.value = dataManagerFace.getAllVolumes()
            return mutableData
        } else {
            return listRepository.getLisData()
        }
    }

    fun doCalculations(): LiveData<ArrayList<CustomModel>> {
        val mutableData = MutableLiveData<ArrayList<CustomModel>>()
        val years = arrayOf("2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018")
        val tempVolumeList = ArrayList<CustomModel>()
        Observable.fromIterable(years.toList())
                .map {
                    val list = dataManagerFace.getVolumeListByYear(it)!!
                    //adding all volumes
                    var totalVolume = 0.0
                    list.forEach {
                        totalVolume += it.volume.toDouble()
                    }


                    //parsing individual quarter datas
                    val tempArrayList = ArrayList<CustomModel.Quarter>()
                    for (i in list.indices) {
                        val quarter = CustomModel.Quarter()
                        quarter.isDecreased = list[i].isVolumeDecreased
                        quarter.value = list[i].volume
                        tempArrayList.add(quarter)
                    }

                    val customModel = CustomModel()
                    customModel.year = list[0].year
                    customModel.totalVolume = totalVolume.toString()
                    customModel.quartersList = tempArrayList

                    customModel
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<CustomModel> {
                    override fun onComplete() {
                        mutableData.value = tempVolumeList
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: CustomModel) {
                        tempVolumeList.add(t)
                    }

                    override fun onError(e: Throwable) {
                        Log.e(TAG, "Error occurred : ${e.localizedMessage}")
                    }
                })
        return mutableData
    }
}