package com.example.effectivemobile

import android.util.Log
import com.example.data.ApiFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

suspend fun main(){
    val api = ApiFactory.apiService
    val scope = CoroutineScope(Dispatchers.Default)
    val kek = scope.async {
        api.getOffers()
    }
    Log.d("asda", kek.await().toString())
}