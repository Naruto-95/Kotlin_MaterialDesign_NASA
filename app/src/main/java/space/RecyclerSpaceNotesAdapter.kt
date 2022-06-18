package space

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_materialdesign_nasa.databinding.FragmentHeaderSpaceNotesBinding
import com.example.kotlin_materialdesign_nasa.databinding.FragmentSpaceNotesBinding
import com.example.kotlin_materialdesign_nasa.recycler.ItemTouchHelperViewHolder
import com.example.kotlin_materialdesign_nasa.utils.HEADER_SPACE
import com.example.kotlin_materialdesign_nasa.utils.NOTES_SPACE

class RecyclerSpaceNotesAdapter(private var onListSpaceItemClickListener:OnListSpaceItemClickListener


) :
    RecyclerView.Adapter<AbstractBaseViewHolder>(),ItemTouchHelperClick {

    private lateinit var listItem: MutableList<Pair<Data, Boolean>>

    override fun getItemViewType(position: Int): Int {
        return listItem[position].first.mType
    }
    fun setList(newDataList:List<Pair<Data, Boolean>>){
        this.listItem = newDataList.toMutableList()
    }
    fun setAddList(newDataList:List<Pair<Data, Boolean>>, position: Int){
        this.listItem = newDataList.toMutableList()
        notifyItemChanged(position)
    }
    fun setRemoveList(newDataList:List<Pair<Data, Boolean>>, position: Int){
        this.listItem = newDataList.toMutableList()
        notifyItemRemoved(position)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbstractBaseViewHolder {
        return when (viewType) {
            NOTES_SPACE -> {
                val view = FragmentSpaceNotesBinding.inflate(LayoutInflater.from(parent.context))
                mNotesViewHolder(view.root)
            }
            HEADER_SPACE -> {
                val view =
                    FragmentHeaderSpaceNotesBinding.inflate(LayoutInflater.from(parent.context))
                mHeaderViewHolder(view.root)
            }
            else -> {
                val view = FragmentSpaceNotesBinding.inflate(LayoutInflater.from(parent.context))
                mNotesViewHolder(view.root)
            }
        }

    }

    override fun onBindViewHolder(holder: AbstractBaseViewHolder, position: Int) {
       holder.MyBind(listItem[position])
    }


    override fun getItemCount(): Int {
        return listItem.size
    }


   inner class mNotesViewHolder(view: View) : AbstractBaseViewHolder(view),
       ItemTouchNotesHelperViewHolder {
        override fun MyBind(data: Pair<Data, Boolean>) {
            (FragmentSpaceNotesBinding.bind(itemView)).apply {
                textApp.text = data.first.mText

                addItemImageView.setOnClickListener {
                    onListSpaceItemClickListener.onAddItemClick(layoutPosition)

                }
                removeItemImageView.setOnClickListener {
                    onListSpaceItemClickListener.onRemoveItemClick(layoutPosition)
                }


            }


        }

       override fun onItemSelected() {
           itemView.setBackgroundColor(Color.RED)
       }

       override fun onItemClear() {
           itemView.setBackgroundColor(0)
       }
   }

    inner class mHeaderViewHolder(view: View) : AbstractBaseViewHolder(view) {
        override fun MyBind(data: Pair<Data, Boolean>) {
            (FragmentHeaderSpaceNotesBinding.bind(itemView)).apply {
                headerNotes.text = data.first.mText
            }
        }
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        listItem.removeAt(fromPosition).apply {
            listItem.add(toPosition, this)
        }
        notifyItemMoved(fromPosition,toPosition)
    }

    override fun onItemDismiss(position: Int) {
        listItem.removeAt(position)
        notifyItemRemoved(position)
    }

}









