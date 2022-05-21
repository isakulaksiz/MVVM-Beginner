package com.isilon.mvvm_beginner.data.api

import com.isilon.mvvm_beginner.data.model.User
import io.reactivex.Single

interface ApiService {
    fun getUsers(): Single<List<User>>
}