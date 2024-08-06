package com.example.helloo.Repository

import com.example.helloo.API.ApiClient
import com.example.helloo.API.PostApiInterface
import com.example.helloo.Model.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class PostRepository {
    val apiClient = ApiClient.buildAPIInterface(PostApiInterface::class.java)

    suspend fun fetchPosts(): Response<List<Post>>{
        return withContext(Dispatchers.IO){
            apiClient.fetchPosts()
        }
    }

}