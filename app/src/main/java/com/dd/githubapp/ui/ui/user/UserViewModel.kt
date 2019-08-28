package com.dd.githubapp.ui.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dd.githubapp.entity.User
import com.dd.githubapp.model.AccountManager

class UserViewModel : ViewModel() {

    private val _user = MutableLiveData<User>().apply {
        value = AccountManager.currentUser
    }

    val user: LiveData<User> = _user

}