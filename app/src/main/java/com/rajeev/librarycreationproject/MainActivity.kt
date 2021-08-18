package com.rajeev.librarycreationproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rajeev.mylibrary.MyCache
import com.rajeev.mylibrary.MyCacheSingleton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myCache = MyCacheSingleton.getInstance(1)
        myCache.put(1, 10)
        myCache.put(2, 20)
        myCache.put(3, 30)
        println("myCache.get(1): ${myCache.get(1)}")
        println("myCache.get(3): ${myCache.get(3)}")
    }
}