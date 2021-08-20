package com.rajeev.halodocapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rajeev.halodocapp.data.repository.MyRepository
import com.rajeev.halodocapp.model.MyData
import com.rajeev.halodocapp.model.MyDataResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyViewModel constructor(private val repository: MyRepository)  : ViewModel() {

    val myDataList = MutableLiveData<MyDataResponse>()
    val errorMessage = MutableLiveData<String>()

    fun fetchPaginationData(pageNumber: Int) {
        val response = repository.fetchPaginationData(pageNumber)
        response.enqueue(object : Callback<MyDataResponse> {
            override fun onResponse(call: Call<MyDataResponse>, response: Response<MyDataResponse>) {
                myDataList.postValue(response.body())
            }

            override fun onFailure(call: Call<MyDataResponse>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}