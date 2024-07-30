package com.example.helloo

import ApiClient
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.helloo.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        fetchPosts()
        binding.rvPosts.layoutManager = LinearLayoutManager(this)
    }
    fun fetchPosts(){
        val apiInterface = ApiClient.buildAPIInterface(PostApiInterface::class.java)
        val request = apiInterface.fetchPosts()
        request.enqueue(object: Callback<List<Post>>{
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
               if (response.isSuccessful){

                   val posts = response.body()
                   if (posts != null) {
                       displayPosts(posts!!)
                   }
                   Toast.makeText(baseContext, "Fetched ${posts!!.size} posts", Toast.LENGTH_LONG).show()
               }
                else{
                    Toast.makeText(baseContext,response.errorBody()?.string(), Toast.LENGTH_LONG)
               }
            }

            override fun onFailure(call: Call<List<Post>>, response: Throwable) {
               Toast.makeText(baseContext, response.message, Toast.LENGTH_LONG ).show()
            }

        })

    }
    fun displayPosts(posts: List<Post>){
        val postAdapter = PostAdapter(posts,this)
        binding.rvPosts.adapter = postAdapter

    }
}
