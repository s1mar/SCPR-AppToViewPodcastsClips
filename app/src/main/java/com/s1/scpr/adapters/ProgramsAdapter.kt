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
import com.s1.scpr.models.Program
import com.squareup.picasso.Picasso
import java.util.ArrayList


class ProgramsAdapter(private val callback: Function<String>) :
    RecyclerView.Adapter<ProgramsAdapter.ViewHolder?>() {
    private val dataSet: MutableList<Program> = ArrayList<Program>()
    @SuppressLint("NotifyDataSetChanged")
    fun updateDataSet(dataSet: List<Program>?, reset: Boolean) {
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
        val program: Program = dataSet[position]
        holder.itemBinding.tv2.text =
            program.getAuthor().ifEmpty { "Author not given" }
        holder.itemBinding.tv3.text =
            if (program.category?.isEmpty() == true) "Category not specified" else program.category
        holder.itemBinding.tv1.text =
            if (program.name?.isEmpty() == true) "Name not specified" else program.name
        val desc: String? = program.description
        if (desc == null || desc.trim { it <= ' ' }.isEmpty()) {
            holder.itemBinding.tv4.visibility = View.GONE
        } else {
            holder.itemBinding.tv4.text = desc
            holder.itemBinding.tv4.isSelected = true
        }
        Picasso.get().load(program.artworkUrl).resize(70, 70).placeholder(R.drawable.loading)
            .error(R.drawable.error_loading_image)
            .into(holder.itemBinding.imgView)
    }



    inner class ViewHolder(@NonNull itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        var itemBinding: ListItemBinding

        init {
            itemBinding = ListItemBinding.bind(itemView!!)
            itemBinding.getRoot().setOnClickListener { v ->
                val program: Program = dataSet[getAdapterPosition()]
                program.id?.let { callback.call(it) }
            }
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}