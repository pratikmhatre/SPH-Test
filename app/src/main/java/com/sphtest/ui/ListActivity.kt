package com.sphtest.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.sphtest.R
import com.sphtest.application.MyApplication
import com.sphtest.data.db.tables.VolumeDataTable
import com.sphtest.data.network.models.CustomModel
import com.sphtest.di.components.DaggerActivityComponent
import com.sphtest.di.modules.ActivityModule
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class ListActivity : AppCompatActivity() {
    @Inject
    lateinit var listModelFactory: ListModelFactory

    @Inject
    lateinit var volumeListAdapter: VolumeListAdapter

    lateinit var listViewModel: ListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val activityComponent = DaggerActivityComponent.builder().appComponent((applicationContext as MyApplication).appComponent).activityModule(ActivityModule(this)).build()
        activityComponent.inject(this@ListActivity)
        listViewModel = ViewModelProviders.of(this, listModelFactory)[ListViewModel::class.java]


        val connectivityService = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeInfo = connectivityService.activeNetworkInfo


        //check internet
        if (activeInfo == null || !activeInfo.isConnected) {
            Toast.makeText(this, getString(R.string.network_error), Toast.LENGTH_SHORT).show()
        }



        recycler_volumes.apply {
            layoutManager = LinearLayoutManager(this@ListActivity)
            adapter = volumeListAdapter
        }

        listViewModel.getVolumeDataList(false).observe(this, Observer<ArrayList<VolumeDataTable>> {
            if (it != null) {
                doCalc()
            }
        })

        swipe_list.setOnRefreshListener {
            if (connectivityService.activeNetworkInfo == null || !connectivityService.activeNetworkInfo.isConnected) {
                swipe_list.isRefreshing = false
                Toast.makeText(this, getString(R.string.network_error), Toast.LENGTH_SHORT).show()
            } else {
                listViewModel.getVolumeDataList(true).observe(this, Observer<ArrayList<VolumeDataTable>> {
                    if (it != null) {
                        doCalc()
                    }
                })
            }
        }
    }

    private fun doCalc() {
        listViewModel.doCalculations().observe(this, Observer<ArrayList<CustomModel>> {
            if (it != null) {
                volumeListAdapter.addVolumes(it)
                swipe_list.apply {
                    if (isRefreshing) {
                        isRefreshing = false
                    }
                }
            }
        })
    }
}