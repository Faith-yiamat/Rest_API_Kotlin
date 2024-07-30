package com.example.helloo

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.helloo.databinding.PostListItemBinding

class PostAdapter(var posts: List<Post>, val context: Context):RecyclerView.Adapter<PostViewHolder> (){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = PostListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PostViewHolder(binding)

    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]
        holder.binding.apply {
            tvBody.text = post.body
            tvTitle.text = post.title
            clPost.setOnClickListener {
                val intent = Intent(context, Comments::class.java)
                intent.putExtra("POST_ID", post.id)
                context.startActivity(intent)
            }
        }

    }

    override fun getItemCount(): Int {
        return posts.size
    }
}
class PostViewHolder(val binding: PostListItemBinding):RecyclerView.ViewHolder(binding.root){

}

fun displayPosts(){

}