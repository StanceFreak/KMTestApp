package com.example.kmtestsuitmedia.network

import com.example.kmtestsuitmedia.model.UserList

class ApiHelper(private val apiService: ApiService) {

    suspend fun getListUser(page: Int) : UserList {
        return apiService.getUserList(page)
    }

}