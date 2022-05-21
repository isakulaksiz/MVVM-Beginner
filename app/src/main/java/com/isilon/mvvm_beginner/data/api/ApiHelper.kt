package com.isilon.mvvm_beginner.data.api

// dependency injection
// - - constructor injection
class ApiHelper(private val apiService: ApiService) {
    fun getUsers() = apiService.getUsers()
}