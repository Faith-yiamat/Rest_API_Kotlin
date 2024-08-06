package com.example.helloo.Ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.helloo.Model.CommentsData
import com.example.helloo.databinding.CommentsListItemBinding

class CommentsAdapter(var commentsList: List<CommentsData>): RecyclerView.Adapter<CommentsViewHolder> (){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        val binding = CommentsListItemBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return CommentsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        val comment = commentsList[position]
        holder.binding.tvName.text = comment.name
        holder.binding.tvComment.text = comment.body
    }

    override fun getItemCount(): Int {
       return commentsList.size
    }
}

class CommentsViewHolder(val binding: CommentsListItemBinding): RecyclerView.ViewHolder(binding.root){

}