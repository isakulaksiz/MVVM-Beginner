package com.isilon.mvvm_beginner.data.repository

import com.isilon.mvvm_beginner.data.api.ApiHelper
import com.isilon.mvvm_beginner.data.model.User
import io.reactivex.Single

// dependency injection
// - - constructor injection
class MainRepository(private val apiHelper: ApiHelper) {
    fun getUsers(): Single<List<User>>{
        return  apiHelper.getUsers()
    }
}