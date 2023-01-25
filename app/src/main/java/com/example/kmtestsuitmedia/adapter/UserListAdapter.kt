package com.example.kmtestsuitmedia.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kmtestsuitmedia.R
import com.example.kmtestsuitmedia.views.SecondScreenActivity
import com.example.kmtestsuitmedia.databinding.RvUserListBinding
import com.example.kmtestsuitmedia.model.Data
import com.squareup.picasso.Picasso

class UserListAdapter : RecyclerView.Adapter<UserListAdapter.RecyclerviewHolder>() {
    companion object {
        const val SELECTED_USER_NAME = "selected_name"
    }
    private var data = ArrayList<Data>()

    inner class RecyclerviewHolder (
        private val binding: RvUserListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind (userData: Data) {
            binding.apply {
                Picasso.get()
                    .load(userData.avatar)
                    .placeholder(R.drawable.ic_image)
                    .fit()
                    .into(userImage)
                userFirstname.text = userData.firstName
                userLastname.text = userData.lastName
                userEmail.text = userData.email
                itemView.setOnClickListener {
                    val i = Intent(itemView.context, SecondScreenActivity::class.java)
                    i.putExtra(SELECTED_USER_NAME, userData)
                    itemView.context.startActivity(i)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerviewHolder {
        val itemBinding = RvUserListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RecyclerviewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RecyclerviewHolder, position: Int) {
        val data = this.data[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return this.data.size
    }

    fun setData(dataList: List<Data>) {
        this.data.clear()
        this.data.addAll(dataList)
        notifyDataSetChanged()
    }

}