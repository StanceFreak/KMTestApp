package com.example.kmtestsuitmedia.network

import com.example.kmtestsuitmedia.model.UserList
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/api/users?")
    suspend fun getUserList(
        @Query ("page") page: Int
    ): UserList
}