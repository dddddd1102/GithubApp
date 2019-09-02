package com.dd.githubapp.ui.ui.star

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StarViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Star Fragment"
    }
    val text: LiveData<String> = _text
}