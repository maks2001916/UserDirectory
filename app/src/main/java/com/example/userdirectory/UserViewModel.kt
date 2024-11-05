package com.example.userdirectory

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel: ViewModel() {

    var users: MutableLiveData<MutableList<User>>? = null
    val currentUser: MutableLiveData<MutableList<User>> by lazy { MutableLiveData<MutableList<User>>() }
    fun addUser(user: User) { users?.value?.add(user) }
    fun removeUser(user: User) {users?.value?.remove(user)}

}