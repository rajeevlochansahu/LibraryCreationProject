package com.rajeev.mylibrary

import java.util.*

object MyCacheSingleton {
    private var CAPACITY: Int = 0
    private val linkedHashMap: LinkedHashMap<Int, Int>
    fun put(key: Int, value: Int) {
        linkedHashMap[key] = value
    }

    fun get(key: Int): Int? {
        return linkedHashMap[key]
    }

    fun getInstance(capacity: Int): MyCacheSingleton {
        setCapacity(capacity)
        return this
    }

    init {
        linkedHashMap = object : LinkedHashMap<Int, Int>(CAPACITY, 0.75f, true) {
            override fun removeEldestEntry(eldest: Map.Entry<Int?, Int?>): Boolean {
                return size > CAPACITY
            }
        }
    }

    private fun setCapacity(capacity: Int) {
        CAPACITY = capacity
    }
}