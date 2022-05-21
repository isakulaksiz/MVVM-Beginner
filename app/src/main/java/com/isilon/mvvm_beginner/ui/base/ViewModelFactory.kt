package com.isilon.mvvm_beginner.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.isilon.mvvm_beginner.data.api.ApiHelper
import com.isilon.mvvm_beginner.data.repository.MainRepository
import com.isilon.mvvm_beginner.ui.main.viewmodel.MainViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val apiHelper: ApiHelper): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java))
            return MainViewModel(MainRepository(apiHelper)) as T
        throw IllegalArgumentException("Bilinmeyen Class ismi")
    }

}