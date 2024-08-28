package com.example.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.core.R
import com.example.core.databinding.ItemUserBinding
import com.example.core.domain.model.Github
import java.util.ArrayList

class GithubAdapter : RecyclerView.Adapter<GithubAdapter.ListViewHolder>() {

    private var listData = ArrayList<Github>()
    var onItemClick: ((Github) -> Unit)? = null

    fun setData(newListData: List<Github>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemUserBinding.bind(itemView)
        fun bind(data: Github) {
            binding.username.text = "${data.login}"
            Glide.with(binding.root.context)
                .load(data.avatarUrl)
                .transform(CircleCrop())
                .into(binding.profil)

//            binding.root.setOnClickListener {
//                listener.onItemClick(users)
//            }

        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}