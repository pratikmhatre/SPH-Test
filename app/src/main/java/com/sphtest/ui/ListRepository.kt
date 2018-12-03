package com.sphtest.ui

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.sphtest.Utils
import com.sphtest.data.DataManagerFace
import com.sphtest.data.db.tables.VolumeDataTable
import com.sphtest.data.network.models.VolumeData
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class ListRepository @Inject constructor(val dataManagerFace: DataManagerFace) {
    val TAG = "ListRepository"
    fun getLisData(): LiveData<ArrayList<VolumeDataTable>> {
        val mutableLiveData = MutableLiveData<ArrayList<VolumeDataTable>>()

        dataManagerFace.fetchVolumeData(Utils.RESOURCE_ID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<Response<VolumeData>> {
                    override fun onSuccess(t: Response<VolumeData>) {
                        if (t.code() == 200 && t.body() != null && t.body()?.result?.records != null && t.body()?.result?.records!!.isNotEmpty()) {
                            saveList(mutableLiveData, t.body()?.result?.records!!)
                        } else {
                            Log.e(TAG, "Error loading volume data from network : ${t.code()}")
                        }
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        Log.e(TAG, "Error loading volume data from network : ${e.localizedMessage}")
                    }
                })


        return mutableLiveData
    }

    fun saveList(mutableLiveData: MutableLiveData<ArrayList<VolumeDataTable>>, arrayList: ArrayList<VolumeData.Result.Record>) {
        val tempVolumeArray = ArrayList<VolumeDataTable>()
        var lastVolume = 0.0
        Observable.fromIterable(arrayList)
                .filter {
                    //pass records between 2008 & 2018 only
                    val year = it.quarter?.replace("(\\d+)-\\w+".toRegex(), "$1")?.toInt()
                    return@filter ((year != null) && (year >= 2008) && (year <= 2018))
                }
                .map {
                    val year = it.quarter?.replace("(\\d+)-\\w+".toRegex(), "$1")
                    val volumeDataTable = VolumeDataTable().apply {
                        id = it.id.toString()
                        quarter = it.quarter
                        volume = it.volume_of_mobile_data
                        this.year = year
                    }
                    volumeDataTable
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<VolumeDataTable> {
                    override fun onComplete() {
                        mutableLiveData.value = dataManagerFace.getAllVolumes()
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: VolumeDataTable) {
                        t.apply {
                            isVolumeDecreased = volume.toDouble() < lastVolume
                        }
                        tempVolumeArray.add(t)
                        dataManagerFace.addSingleVolumeData(t)
                        lastVolume = t.volume.toDouble()
                    }

                    override fun onError(e: Throwable) {
                        Log.e(TAG, "Error saving data in db : ${e.localizedMessage}")
                        e.printStackTrace()
                    }
                })
    }
}