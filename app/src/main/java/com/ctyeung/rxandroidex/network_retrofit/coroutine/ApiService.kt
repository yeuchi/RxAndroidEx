package com.ctyeung.networkrequestex.network_retrofit.coroutine

import com.ctyeung.rxandroidex.network_retrofit.User
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<User>

}