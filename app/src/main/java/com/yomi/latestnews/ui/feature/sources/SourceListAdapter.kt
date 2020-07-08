package com.yomi.latestnews.ui.feature.sources

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yomi.latestnews.R
import com.yomi.latestnews.ui.model.SourceScreenModel
import kotlinx.android.synthetic.main.item_source.view.*

/**
 * Created by Yomi Joseph on 2020-07-08.
 */
class SourceListAdapter(private val sources: ArrayList<SourceScreenModel>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var itemClick: ((SourceScreenModel, Boolean) -> Unit)? = null

    fun updateData(list: List<SourceScreenModel>){
        sources.clear()
        sources.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder  {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_source, parent, false)
        return SourceViewHolder(view)
    }

    override fun getItemCount() = sources.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as SourceViewHolder
        viewHolder.bindView(sources[position])

    }

    inner class SourceViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindView(model: SourceScreenModel) {
            with(itemView) {
                txt_source_name.text = model.name
                checkbox_source.isChecked = model.selected
                checkbox_source.setOnCheckedChangeListener { btnView, isChecked ->
                    itemClick?.invoke(model, isChecked)
                }
                item_source.setOnClickListener{
                    checkbox_source.toggle()
                }
            }
        }
    }
}