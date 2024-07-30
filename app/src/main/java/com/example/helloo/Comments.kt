package com.example.helloo

import ApiClient
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.helloo.databinding.ActivityCommentsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Comments : AppCompatActivity() {
    var postId = 0
    lateinit var binding: ActivityCommentsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommentsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extra = intent.extras
        if (extra != null) {
            postId = extra.getInt("POST_ID")
            fetchPosts(postId)
        }

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