package com.s1.scpr.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.s1.scpr.R
import com.s1.scpr.databinding.ListItemBinding
import com.s1.scpr.interfaces.Function
import com.s1.scpr.models.Clip
import com.s1.scpr.utils.Utils
import com.squareup.picasso.Picasso
import java.util.ArrayList

class ClipsAdapter(private val callback: Function<Clip>) :
    RecyclerView.Adapter<ClipsAdapter.ViewHolder?>() {
    private val dataSet: MutableList<Clip> = ArrayList()
    @SuppressLint("NotifyDataSetChanged")
    fun updateDataSet(dataSet: List<Clip>?, reset: Boolean) {
        if (dataSet != null) {
            if (reset) {
                this.dataSet.clear()
            }
            this.dataSet.addAll(dataSet)
            notifyDataSetChanged()
        }
    }

    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.getContext())
        val itemView: View = inflater.inflate(R.layout.list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(@NonNull holder: ViewHolder, position: Int) {
        val clip = dataSet[position]
        val title = if (clip.getTitle().isEmpty()) "Title not provided" else clip.getTitle()
        val contentRating = String.format(
            "Content-Rating: %s",
            if (clip.getContentRating().isEmpty()) "Unrated" else clip.getContentRating()
        )
        val duration = String.format(
            "Duration: %s", Utils.secondsToHourMinsAndSecondsStr(
                clip.durationSeconds!!
            )
        )
        val desc = clip.description
        if (desc == null || desc.trim { it <= ' ' }.isEmpty()) {
            holder.itemBinding.tv4.visibility = View.GONE
        } else {
            holder.itemBinding.tv4.text = desc
            holder.itemBinding.tv4.isSelected = true
        }
        holder.itemBinding.tv1.text = title
        holder.itemBinding.tv2.text = contentRating
        holder.itemBinding.tv3.text = duration
        Picasso.get().load(clip.imageUrl).resize(70, 70).placeholder(R.drawable.loading)
            .error(R.drawable.error_loading_image)
            .into(holder.itemBinding.imgView)
    }



    inner class ViewHolder(@NonNull itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        var itemBinding: ListItemBinding

        init {
            itemBinding = ListItemBinding.bind(itemView!!)
            itemBinding.getRoot()
                .setOnClickListener { v -> callback.call(dataSet[getAdapterPosition()]) }
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}