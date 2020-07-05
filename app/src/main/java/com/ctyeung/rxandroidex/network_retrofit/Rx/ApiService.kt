package com.ctyeung.rxandroidex.network_retrofit.Rx

import io.reactivex.Observable
import retrofit2.http.GET
import com.ctyeung.rxandroidex.network_retrofit.User

interface ApiService {

//Describe the request type and the relative URL//

    @GET("users")
    fun getUsers() : Observable<List<User>>
}