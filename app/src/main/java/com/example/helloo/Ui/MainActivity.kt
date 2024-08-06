package com.example.helloo.Ui

import com.example.helloo.API.ApiClient
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.helloo.API.PostApiInterface
import com.example.helloo.Model.Post
import com.example.helloo.ViewModel.PostViewModel
import com.example.helloo.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var TAG = "MAINACTIVITY"
    val postsViewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        Log.d(TAG,"ONCREATE")
        setContentView(binding.root)
        postsViewModel.fetchPosts()
        binding.rvPosts.layoutManager = LinearLayoutManager(this)
    }
    override fun onStart(){
        super.onStart()
        Log.d(TAG,"ONSTART")

    }

    override fun onResume(){
        super.onResume()
        postsViewModel.postLiveData.observe(this, Observer { postsList -> displayPosts(postsList) })
        postsViewModel.errorLiveData.observe(this, Observer { error -> Toast.makeText(this,error,Toast.LENGTH_SHORT).show() })
    }
    override fun onPause(){
        super.onPause()
        Log.d(TAG,"ONPAUSE")

    }
    override fun onStop(){
        super.onStop()
        Log.d(TAG,"ONSTOP")

    }
    override fun onDestroy(){
        super.onDestroy()
        Log.d(TAG,"ONDESTROY")

    }


    fun displayPosts(posts: List<Post>){
        val postAdapter = PostAdapter(posts,this)
        binding.rvPosts.adapter = postAdapter

    }
}
