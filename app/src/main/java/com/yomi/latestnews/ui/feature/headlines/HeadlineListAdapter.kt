package com.yomi.latestnews.ui.feature.headlines

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.yomi.latestnews.R
import com.yomi.latestnews.ui.main.MainFragmentDirections
import com.yomi.latestnews.ui.model.HeadlineScreenModel
import com.yomi.latestnews.util.getProgressDrawable
import com.yomi.latestnews.util.loadImage
import kotlinx.android.synthetic.main.item_headline.view.*

/**
 * Created by Yomi Joseph on 2020-07-08.
 */
class HeadlineListAdapter(private val headlines: ArrayList<HeadlineScreenModel>):
    RecyclerView.Adapter<HeadlineListAdapter.HeadlineViewHolder>() {

    fun updateData(list: List<HeadlineScreenModel>){
        headlines.clear()
        headlines.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeadlineViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_headline, parent, false)
        return HeadlineViewHolder(view)
    }

    override fun getItemCount() = headlines.size

    override fun onBindViewHolder(holder: HeadlineViewHolder, position: Int) {
        val headline = headlines[position]
        holder.view.txt_headline_title.text = headline.title
        holder.view.txt_headline_desc.text = headline.description
        holder.view.txt_headline_author.text = headline.author
        holder.view.image_headline.loadImage(headline.thumbnailUrl,
            getProgressDrawable(holder.view.context))
        holder.view.item_headline.setOnClickListener{
            val action = MainFragmentDirections.actionDetail(headline)
            Navigation.findNavController(holder.view).navigate(action)
        }
    }

    class HeadlineViewHolder(var view: View): RecyclerView.ViewHolder(view)
}