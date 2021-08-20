package com.rajeev.halodocapp.data.network

import com.rajeev.halodocapp.model.MyDataResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface MyAPI {

    @GET("search?query=sports&page=1")
    fun fetchData() : Call<MyDataResponse>

    @GET("search?query=sports")
    fun fetchPaginationData(@Query("page") pageNumber: Int): Call<MyDataResponse>

    /*
    //Below is POST query
    @POST("search?query=sports")
    @FormUrlEncoded
    fun fetchPaginationData(@Field("page") pageNumber: Int): Call<MyDataResponse>
    */

    companion object {

        var retrofitService: MyAPI? = null

        fun getInstance() : MyAPI {

            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("http://hn.algolia.com/api/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(MyAPI::class.java)
            }
            return retrofitService!!
        }
    }
}