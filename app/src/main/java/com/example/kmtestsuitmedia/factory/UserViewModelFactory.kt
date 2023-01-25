package com.example.kmtestsuitmedia.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kmtestsuitmedia.network.ApiHelper
import com.example.kmtestsuitmedia.repository.UserRepository
import com.example.kmtestsuitmedia.viewmodel.UserViewModel
import java.lang.IllegalArgumentException

class UserViewModelFactory(private val apiHelper: ApiHelper): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(UserRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}