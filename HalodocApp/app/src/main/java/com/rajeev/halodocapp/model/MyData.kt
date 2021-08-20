package com.rajeev.halodocapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class MyData(
    @SerializedName("title")
    @Expose
    var title: String,
    @SerializedName("url")
    @Expose
    var url: String,
    @SerializedName("author")
    @Expose
    var author: String
)

data class MyDataResponse(
    @SerializedName("nbHits")
    @Expose
    var nbHits: Int,
    @SerializedName("page")
    var nbPages: Int,
    @SerializedName("nbPages")
    @Expose
    var page: Int,
    @SerializedName("hits")
    @Expose
    var myDataList: ArrayList<MyData>
) {
    override fun toString(): String {
        return myDataList.toString()
    }
}


