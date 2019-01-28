package com.dmytro.mvvmtemplate.common.data

interface TokenStorage {
    fun saveToken(token: String)
    fun getToken(): String
}