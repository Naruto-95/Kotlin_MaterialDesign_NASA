package com.example.kotlin_materialdesign_nasa.recycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.kotlin_materialdesign_nasa.databinding.FragmentRecyclerBinding
import com.example.kotlin_materialdesign_nasa.databinding.FragmentRecyclerEarthBinding
import com.example.kotlin_materialdesign_nasa.recycler.mViewHolders.BaseViewHolder
import com.example.kotlin_materialdesign_nasa.utils.EARTH_R
import com.example.kotlin_materialdesign_nasa.utils.HEADER_R
import com.example.kotlin_materialdesign_nasa.utils.MARS_R


class RecyclerFragment : Fragment(), OnListItemClickListener {

    private var _binding: FragmentRecyclerBinding? = null
    private val binding: FragmentRecyclerBinding
        get() {
            return _binding!!
        }
    lateinit var adapter: RecyclerViewAdapter
    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }

    private val list = arrayListOf(
        Pair(Data("Header", "", HEADER_R), false),
        Pair(Data("Earth", "", EARTH_R), false),
        Pair(Data("Earth", "", EARTH_R), false),
        Pair(Data("Mars", "", MARS_R), false),
        Pair(Data("Earth", "", EARTH_R), false),
        Pair(Data("Earth", "", EARTH_R), false),
        Pair(Data("Earth", "", EARTH_R), false),
        Pair(Data("Mars", "", MARS_R), false)
    )


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = RecyclerViewAdapter(this)
        adapter.setList(list)
        binding.recyclerView.adapter = adapter

        ItemTouchHelper(ItemTouchHelperCallback(adapter)).attachToRecyclerView( binding.recyclerView)


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecyclerBinding.inflate(inflater, container, false)
        return binding.root
    }


    companion object {

        @JvmStatic
        fun newInstance() = RecyclerFragment()
    }

    override fun onItemClick(data: Data) {
        // TODO("Not yet implemented")
    }

    override fun onAddItemClick(position: Int) {

        list.add(position,  Pair(Data("Earth", "", EARTH_R), false))
        //list.add(position, Data("Mars", "", MARS_R))
        adapter.setAddList(list, position)
    }

    override fun onRemoveItemClick(position: Int) {
        list.removeAt(position)
        adapter.setRemoveList(list, position)
    }
}