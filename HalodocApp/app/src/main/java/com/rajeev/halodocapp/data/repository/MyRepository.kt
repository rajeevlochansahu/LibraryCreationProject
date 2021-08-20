package com.rajeev.halodocapp.data.repository

import com.rajeev.halodocapp.data.network.MyAPI

class MyRepository constructor(private val retrofitService: MyAPI) {

    fun fetchData() = retrofitService.fetchData()
    fun fetchPaginationData(pageNumber: Int) = retrofitService.fetchPaginationData(pageNumber)
}