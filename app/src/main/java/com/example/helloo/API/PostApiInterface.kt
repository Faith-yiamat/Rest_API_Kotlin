package com.example.helloo.API

import com.example.helloo.Model.Post
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PostApiInterface {
    @GET("/posts")
   suspend fun fetchPosts(): Response<List<Post>>

    @GET("/posts/{postId}")
    fun fetchPostById(  @Path("postId") postId: Int): Call<Post>
}