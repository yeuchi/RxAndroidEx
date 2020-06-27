package com.ctyeung.rxandroidex.network_retrofit

import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {

//Describe the request type and the relative URL//

    @GET("users")
    fun getUsers() : Observable<List<User>>
}