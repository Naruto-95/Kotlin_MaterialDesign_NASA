package com.example.kotlin_materialdesign_nasa.recycler.mViewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_materialdesign_nasa.recycler.Data

abstract class BaseViewHolder (view: View): RecyclerView.ViewHolder(view){
    abstract fun mBind(data: Data)
}