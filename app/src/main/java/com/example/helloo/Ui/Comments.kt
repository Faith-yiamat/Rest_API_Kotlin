package com.example.helloo.Ui

import com.example.helloo.API.ApiClient
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.helloo.API.PostApiInterface
import com.example.helloo.Model.Post
import com.example.helloo.databinding.ActivityCommentsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Tag

class Comments : AppCompatActivity() {
    var postId = 0
    val TAG = "MYTAG"
    lateinit var binding: ActivityCommentsBinding
    lateinit var commentsAdapter: CommentsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "COMMENTSACTIVITY ONSTART")
        binding = ActivityCommentsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extra = intent.extras
        if (extra != null) {
            postId = extra.getInt("POST_ID")
            fetchPosts(postId)
        }


    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "COMMENTSACTIVITY ONSTART")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "COMMENTSACTIVITY ONRESUME")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "COMMENTSACTIVITY ONPAUSE")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "COMMENTSACTIVITY ONSTOP")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "COMMENTSACTIVITY ONDESTROY")
    }

    private fun setupRecyclerView() {
        commentsAdapter = CommentsAdapter(emptyList())
        binding.rvComments.layoutManager = LinearLayoutManager(this)
        binding.rvComments.adapter = commentsAdapter
    }

    fun fetchPosts(postId: Int) {
        val apiclient = ApiClient.buildAPIInterface(PostApiInterface::class.java)
        val request = apiclient.fetchPostById(postId)

        request.enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful){
                    val post = response.body()
                    binding.tvPostTitle.text = post?.title
                    binding.tvPostBody.text = post?.body
                }
                else{
                    Toast.makeText(this@Comments,response.errorBody()?.string(),Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
               Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
            }
        })
    }
}