package com.dd.githubapp.ui.ui.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AboutViewModel : ViewModel() {

    private val _about = MutableLiveData<String>().apply {
        value = ""
    }

    val about: LiveData<String> = _about
}