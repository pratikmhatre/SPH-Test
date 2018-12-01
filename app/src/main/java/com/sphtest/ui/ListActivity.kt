package com.sphtest.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
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


        recycler_volumes.apply {
            layoutManager = LinearLayoutManager(this@ListActivity)
            adapter = volumeListAdapter
        }

        listViewModel.getVolumeDataList().observe(this, Observer<ArrayList<VolumeDataTable>> {
            if (it != null) {
                doCalc()
            }
        })
    }

    private fun doCalc() {
        val years = arrayOf("2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018")
        for (year in years) {
            listViewModel.doCalculations(year).observe(this, Observer<CustomModel> {
                if (it != null) {
                    volumeListAdapter.addVolume(it)
                }
            })

        }
    }
}