package com.example.kotlin_materialdesign_nasa.recycler.mViewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_materialdesign_nasa.databinding.FragmentRecyclerEarthBinding
import com.example.kotlin_materialdesign_nasa.recycler.Data

 class Earth(view: View) : BaseViewHolder(view) {
     override fun mBind(data: Data) {
            (FragmentRecyclerEarthBinding.bind(itemView)).apply {
                textEarth.text = data.mText
                descriptionTextView.text = data.mText


            }
        }

}