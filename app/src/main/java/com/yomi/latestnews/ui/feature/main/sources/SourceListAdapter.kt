package com.yomi.latestnews.ui.feature.main.sources

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
    RecyclerView.Adapter<SourceListAdapter.SourceViewHolder>() {

    var itemClick: ((SourceScreenModel, Boolean) -> Unit)? = null

    fun updateData(list: List<SourceScreenModel>){
        sources.clear()
        sources.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourceViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_source, parent, false)
        return SourceViewHolder(view)
    }

    override fun getItemCount() = sources.size

    override fun onBindViewHolder(holder: SourceViewHolder, position: Int) {
        val item = sources[position]
        holder.setIsRecyclable(false)
        holder.view.txt_source_name.text = item.name
        holder.view.checkbox_source.isChecked = item.selected
        holder.view.checkbox_source.setOnCheckedChangeListener { btnView, isChecked ->
                updateSourceSelectState(item.id, isChecked)
                itemClick?.invoke(item, isChecked)
        }

    }

    /**
     * Update the state of the source list in memory
     */
    private fun updateSourceSelectState(id: String, isSelected: Boolean) {
        sources.apply {
            find { it.id == id }?.selected = isSelected
        }
    }

    inner class SourceViewHolder(var view: View): RecyclerView.ViewHolder(view) {
    }
}