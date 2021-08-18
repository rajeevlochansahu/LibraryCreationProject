package com.rajeev.mylibrary

import java.util.*

class MyCache(private val CAPACITY: Int) {
    private val linkedHashMap: LinkedHashMap<Int, Int>
    fun put(key: Int, value: Int) {
        linkedHashMap[key] = value
    }

    fun get(key: Int): Int? {
        return linkedHashMap[key]
    }

    init {
        linkedHashMap = object : LinkedHashMap<Int, Int>(CAPACITY, 0.75f, true) {
            override fun removeEldestEntry(eldest: Map.Entry<Int?, Int?>): Boolean {
                return size > CAPACITY
            }
        }
    }
}