package space

import android.view.View
import androidx.recyclerview.widget.RecyclerView


abstract class AbstractBaseViewHolder (view: View): RecyclerView.ViewHolder(view){
    abstract fun MyBind(data: Pair<Data, Boolean>)

}