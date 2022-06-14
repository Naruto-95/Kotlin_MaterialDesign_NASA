package com.example.kotlin_materialdesign_nasa.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_materialdesign_nasa.databinding.FragmentRecyclerEarthBinding
import com.example.kotlin_materialdesign_nasa.databinding.FragmentRecyclerMarsBinding
import com.example.kotlin_materialdesign_nasa.recycler.mViewHolders.BaseViewHolder
import com.example.kotlin_materialdesign_nasa.recycler.mViewHolders.Earth
import com.example.kotlin_materialdesign_nasa.recycler.mViewHolders.Mars
import com.example.kotlin_materialdesign_nasa.utils.EARTH_R
import com.example.kotlin_materialdesign_nasa.utils.MARS_R

class RecyclerViewAdapter(
    private var listData: List<Data>,
) :
    RecyclerView.Adapter<BaseViewHolder>() {


    override fun getItemViewType(position: Int): Int {
        return listData[position].mType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            EARTH_R -> {
                val view = FragmentRecyclerEarthBinding.inflate(LayoutInflater.from(parent.context))
                Earth(view.root)
            }
            MARS_R -> {
                val view = FragmentRecyclerMarsBinding.inflate(LayoutInflater.from(parent.context))
                Mars(view.root)
            }
            else -> {
                val view = FragmentRecyclerMarsBinding.inflate(LayoutInflater.from(parent.context))
                Mars (view.root)
            }
        }

    }


    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.mBind(listData[position])


    }


override fun getItemCount(): Int {
    return listData.size
}


}



