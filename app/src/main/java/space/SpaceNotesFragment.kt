package space

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.kotlin_materialdesign_nasa.databinding.FragmentRecyclerNotesSpaceBinding
import com.example.kotlin_materialdesign_nasa.utils.HEADER_SPACE
import com.example.kotlin_materialdesign_nasa.utils.NOTES_SPACE


class SpaceNotesFragment : Fragment(), OnListSpaceItemClickListener {
    private var _binding: FragmentRecyclerNotesSpaceBinding? = null
    private val binding: FragmentRecyclerNotesSpaceBinding
        get() {
            return _binding!!
        }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }

    val list = arrayListOf(
        Pair(Data("To-do list", HEADER_SPACE), false),
        Pair(Data("Training in the morning", NOTES_SPACE), false),
        Pair(Data("Work", NOTES_SPACE), false),
        Pair(Data("Study", NOTES_SPACE), false),
        Pair(Data("Practical task", NOTES_SPACE), false),
        Pair(Data("Playing sports", NOTES_SPACE), false),
        Pair(Data("Exploring the NASA website", NOTES_SPACE), false),
        Pair(Data("application development", NOTES_SPACE), false)


    )
    lateinit var adapter: RecyclerSpaceNotesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        adapter = RecyclerSpaceNotesAdapter(this)
        adapter.setList(list)
        binding.recyclerView.adapter = adapter


          ItemTouchHelper(ItemTouchHelperNotesCallback(adapter)).attachToRecyclerView( binding.recyclerView)

        //list.add(0, Pair(Data("Header", HEADER_SPACE), false))

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecyclerNotesSpaceBinding.inflate(inflater, container, false)
        return binding.root
    }



    companion object {

        @JvmStatic
        fun newInstance() = SpaceNotesFragment()
    }

    override fun onAddItemClick(position: Int) {
        list.add(position, Pair(Data("Notes", NOTES_SPACE), false))
        adapter.setAddList(list, position)
    }

    override fun onRemoveItemClick(position: Int) {
        list.removeAt(position)
        adapter.setRemoveList(list, position)
    }




}