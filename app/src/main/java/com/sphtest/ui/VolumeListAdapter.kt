package com.sphtest.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.sphtest.R
import com.sphtest.data.db.tables.VolumeDataTable
import com.sphtest.data.network.models.CustomModel
import javax.inject.Inject

class VolumeListAdapter @Inject constructor(val listActivity: ListActivity, val arrayList: ArrayList<CustomModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return VolumeDataHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_volume_data, p0, false))
    }

    override fun getItemCount() = arrayList.size

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        val myHolder = p0 as VolumeDataHolder
        myHolder.bindItems(listActivity = listActivity, model = arrayList[p1])
    }


    class VolumeDataHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(listActivity: ListActivity, model: CustomModel) {
            with(itemView) {
                val tvYear = findViewById<TextView>(R.id.tv_year)
                val tvVolume = findViewById<TextView>(R.id.tv_volume)
                val tvVolumeQ1 = findViewById<TextView>(R.id.tv_q1)
                val tvVolumeQ2 = findViewById<TextView>(R.id.tv_q2)
                val tvVolumeQ3 = findViewById<TextView>(R.id.tv_q3)
                val tvVolumeQ4 = findViewById<TextView>(R.id.tv_q4)
                val btnViewDetails = findViewById<ImageView>(R.id.btn_details)
                btnViewDetails.visibility = View.GONE

                tvYear.text = model.year
                tvVolume.text = model.totalVolume
                for (position in model.quartersList!!.indices) {
                    val quarter = model.quartersList!![position]
                    when (position) {
                        0 -> {
                            val volume = "Q1 : ${quarter.value}"
                            tvVolumeQ1.text = volume
                        }
                        1 -> {
                            val volume = "Q2 : ${quarter.value}"
                            tvVolumeQ2.text = volume
                        }
                        2 -> {
                            val volume = "Q3 : ${quarter.value}"
                            tvVolumeQ3.text = volume
                        }
                        3 -> {
                            val volume = "Q4 : ${quarter.value}"
                            tvVolumeQ4.text = volume
                        }
                    }
                }

            }
        }
    }

    fun addVolumes(arrayList: ArrayList<CustomModel>) {
        this.arrayList.clear()
        this.arrayList.addAll(arrayList)
        notifyDataSetChanged()
    }

    fun addVolume(model: CustomModel) {
        this.arrayList.add(model)
        notifyDataSetChanged()
    }

}