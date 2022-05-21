package com.isilon.mvvm_beginner.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.isilon.mvvm_beginner.R
import com.isilon.mvvm_beginner.data.model.User


class MainAdapter(
    private val users: ArrayList<User>
) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(view: View): RecyclerView.ViewHolder(view){
        val textViewUserName: TextView
        val textViewEmail: TextView
        val imageViewAvatar: ImageView
        init {
            textViewUserName = view.findViewById(R.id.textViewUserName)
            textViewEmail = view.findViewById(R.id.textViewUserEmail)
            imageViewAvatar = view.findViewById(R.id.imageViewAvatar)

        }

        fun bind(user: User){

            textViewUserName.text = user.name
            textViewEmail.text = user.email
            Glide.with(imageViewAvatar.context)
                .load(user.avatar)
                .into(imageViewAvatar)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.DataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_xml, parent,false)
        return MainAdapter.DataViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainAdapter.DataViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size

    fun addData(list: List<User>){
        users.addAll(list)
    }
}