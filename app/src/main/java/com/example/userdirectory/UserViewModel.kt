package com.example.userdirectory

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel: ViewModel() {

    var users: MutableLiveData<MutableList<User>>? = MutableLiveData(mutableListOf())
    val currentUser: MutableLiveData<MutableList<User>> by lazy { MutableLiveData<MutableList<User>>() }
    fun addUser(user: User) {
        users?.value?.add(user)
        users?.value = users?.value
    }
    fun removeUser(user: User) {users?.value?.remove(user)}

}