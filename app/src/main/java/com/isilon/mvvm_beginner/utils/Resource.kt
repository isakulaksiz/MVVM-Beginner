package com.isilon.mvvm_beginner.utils

data class Resource<out T>(val status: Status, val data: T?, val message: String?){

    // ağ çağrısının mevcut durumunu UI katmanına iletmekten sorumlu olan katman
    companion object{
        fun <T> success(data: T?): Resource<T>{
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T>{
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T>{
            return Resource(Status.LOADING, data, null)
        }
    }
}