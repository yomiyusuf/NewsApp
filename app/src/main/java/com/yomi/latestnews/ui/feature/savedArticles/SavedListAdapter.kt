package com.yomi.latestnews.ui.feature.savedArticles

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yomi.latestnews.R
import com.yomi.latestnews.ui.model.HeadlineScreenModel
import kotlinx.android.synthetic.main.item_saved.view.*

/**
 * Created by Yomi Joseph on 2020-07-08.
 */
class SavedListAdapter(private val savedHeadline: ArrayList<HeadlineScreenModel>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var itemClick: ((HeadlineScreenModel) -> Unit)? = null
    var deleteClick: ((HeadlineScreenModel) -> Unit)? = null

    fun updateData(list: List<HeadlineScreenModel>){
        savedHeadline.clear()
        savedHeadline.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder  {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_saved, parent, false)
        return HeadlineViewHolder(view)
    }

    override fun getItemCount() = savedHeadline.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as HeadlineViewHolder
        viewHolder.bindView(savedHeadline[position])
    }

    inner class HeadlineViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindView(model: HeadlineScreenModel) {
            with(itemView) {
                txt_saved_title.text = model.title
                txt_saved_desc.text = model.description
                btn_saved_delete.setOnClickListener{ deleteClick?.invoke(model) }
                setOnClickListener{ itemClick?.invoke(model) }
            }
        }
    }
}