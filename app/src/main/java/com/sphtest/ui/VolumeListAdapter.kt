package com.sphtest.ui

import android.graphics.Color
import android.graphics.PorterDuff
import android.support.v4.content.ContextCompat
import android.support.v7.util.DiffUtil
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
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
        myHolder.bindItems(listActivity = listActivity, model = arrayList[p1], position = p1)
    }


    inner class VolumeDataHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(listActivity: ListActivity, model: CustomModel, position: Int) {
            with(itemView) {
                val tvYear = findViewById<TextView>(R.id.tv_year)
                val tvVolume = findViewById<TextView>(R.id.tv_volume)
                val tvVolumeQ1 = findViewById<TextView>(R.id.tv_q1)
                val tvVolumeQ2 = findViewById<TextView>(R.id.tv_q2)
                val tvVolumeQ3 = findViewById<TextView>(R.id.tv_q3)
                val tvVolumeQ4 = findViewById<TextView>(R.id.tv_q4)
                val btnViewDetails = findViewById<ImageView>(R.id.btn_details)
                btnViewDetails.visibility = View.GONE
                tvVolumeQ1.visibility = View.GONE
                tvVolumeQ2.visibility = View.GONE
                tvVolumeQ3.visibility = View.GONE
                tvVolumeQ4.visibility = View.GONE



                btnViewDetails.setOnClickListener {
                    Toast.makeText(listActivity, listActivity.getString(R.string.view_details), Toast.LENGTH_SHORT).show()
                }

                tvYear.text = model.year
                tvVolume.text = model.totalVolume

                for (position in model.quartersList!!.indices) {
                    val quarter = model.quartersList!![position]
                    if (quarter.isDecreased) {
                        btnViewDetails.visibility = View.VISIBLE
                    }

                    when (position) {
                        0 -> {
                            val volume = "Q1 : ${quarter.value}"
                            tvVolumeQ1.apply {
                                text = volume
                                visibility = View.VISIBLE
                                background.setColorFilter(ContextCompat.getColor(listActivity, if (quarter.isDecreased) android.R.color.holo_red_light else android.R.color.holo_green_light), PorterDuff.Mode.SRC)
                                btnViewDetails.visibility = if (quarter.isDecreased) View.VISIBLE else View.GONE
                            }

                        }
                        1 -> {
                            val volume = "Q2 : ${quarter.value}"
                            tvVolumeQ2.apply {
                                text = volume
                                visibility = View.VISIBLE
                                background.setColorFilter(ContextCompat.getColor(listActivity, if (quarter.isDecreased) android.R.color.holo_red_light else android.R.color.holo_green_light), PorterDuff.Mode.SRC)
                            }
                        }
                        2 -> {
                            val volume = "Q3 : ${quarter.value}"
                            tvVolumeQ3.apply {
                                text = volume
                                visibility = View.VISIBLE
                                background.setColorFilter(ContextCompat.getColor(listActivity, if (quarter.isDecreased) android.R.color.holo_red_light else android.R.color.holo_green_light), PorterDuff.Mode.SRC)
                            }
                        }
                        3 -> {
                            val volume = "Q4 : ${quarter.value}"
                            tvVolumeQ4.apply {
                                text = volume
                                visibility = View.VISIBLE
                                background.setColorFilter(ContextCompat.getColor(listActivity, if (quarter.isDecreased) android.R.color.holo_red_light else android.R.color.holo_green_light), PorterDuff.Mode.SRC)
                            }
                        }
                    }
                }

            }
        }
    }

    fun addVolumes(arrayList: ArrayList<CustomModel>) {
        val callbacks = VolumeListUtils(oldList = this.arrayList, newList = arrayList)
        val results = DiffUtil.calculateDiff(callbacks)
        this.arrayList.clear()
        this.arrayList.addAll(arrayList)
        results.dispatchUpdatesTo(this)
    }

    fun addVolume(model: CustomModel) {
        this.arrayList.add(model)
        notifyDataSetChanged()
    }

}