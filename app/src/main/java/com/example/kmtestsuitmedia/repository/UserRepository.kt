package com.example.kmtestsuitmedia.repository

import com.example.kmtestsuitmedia.model.UserList
import com.example.kmtestsuitmedia.network.ApiHelper

class UserRepository(private val apiHelper: ApiHelper) {

    suspend fun getUserList(page: Int): UserList {
        return apiHelper.getListUser(page)
    }

}