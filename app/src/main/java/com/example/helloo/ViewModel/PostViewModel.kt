package com.example.helloo.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.helloo.Model.Post
import com.example.helloo.Repository.PostRepository
import kotlinx.coroutines.launch


class PostViewModel: ViewModel() {
    val postRepo = PostRepository()
    var errorLiveData = MutableLiveData<String>()
    val postLiveData = MutableLiveData<List<Post>>()

    fun fetchPosts(){
        viewModelScope.launch {
            val response = postRepo.fetchPosts()
            if (response.isSuccessful){
                postLiveData.postValue(response.body())
            }
            else{
                errorLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
}