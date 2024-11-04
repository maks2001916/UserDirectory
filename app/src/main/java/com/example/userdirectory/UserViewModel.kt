package com.example.userdirectory

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel: ViewModel() {

    var users: MutableLiveData<User>? = null
    val currentUser: MutableLiveData<User> by lazy { MutableLiveData<User>() }

}