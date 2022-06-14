package com.example.kotlin_materialdesign_nasa.recycler.mViewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_materialdesign_nasa.databinding.FragmentRecyclerMarsBinding
import com.example.kotlin_materialdesign_nasa.recycler.Data

class Mars {

    class mViewHolderMars(view: View): RecyclerView.ViewHolder(view) {
        fun textBindMars(data: Data){
            (FragmentRecyclerMarsBinding.bind(itemView)).apply {
                textMars.text = data.mText
                descriptionTextView.text = data.mDesc
            }
        }
    }
}