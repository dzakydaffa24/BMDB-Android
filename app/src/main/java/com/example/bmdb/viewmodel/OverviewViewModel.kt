package com.example.bmdb.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bmdb.network.Movie
import com.example.bmdb.network.MovieAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class OverviewViewModel : ViewModel() {
    private val _items = MutableLiveData<List<Movie>>()

    val items: LiveData<List<Movie>> get() = _items

    private val _response = MutableLiveData<String>()

    val response: LiveData<String> get() = _response

    private var vmJob = Job()
    private val crScope = CoroutineScope(vmJob + Dispatchers.Main)

    init {
        initData()
    }

    private fun initData() {
        _response.value = "Loading..."
        crScope.launch {
            try {
                val result = MovieAPI.retrofitService.showList()

                if (result.isNotEmpty()) {
                    _items.value = result
                    _response.value = "OK"
                }
            } catch (e: Exception) {
                _response.value = "Please check your connection"
                Log.d("Movie", e.message)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        vmJob.cancel()
    }
}