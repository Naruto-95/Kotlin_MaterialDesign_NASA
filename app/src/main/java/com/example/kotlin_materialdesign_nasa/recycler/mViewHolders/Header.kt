package com.example.kotlin_materialdesign_nasa.recycler.mViewHolders

import android.view.View
import com.example.kotlin_materialdesign_nasa.databinding.FragmentHeaderBinding
import com.example.kotlin_materialdesign_nasa.recycler.Data

class Header(view: View): BaseViewHolder(view) {
    override fun mBind(data: Data) {
        (FragmentHeaderBinding.bind(itemView)).apply {
            headerRecycler.text = data.mText
        }
    }
}