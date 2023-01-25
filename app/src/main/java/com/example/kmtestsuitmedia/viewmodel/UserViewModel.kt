package com.example.kmtestsuitmedia.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.kmtestsuitmedia.repository.UserRepository
import com.example.kmtestsuitmedia.util.Resource
import kotlinx.coroutines.Dispatchers

class UserViewModel (private val repository: UserRepository) : ViewModel() {

    fun getUserList(page: Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getUserList(page)))
        }
        catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occured!"))
        }
    }

}