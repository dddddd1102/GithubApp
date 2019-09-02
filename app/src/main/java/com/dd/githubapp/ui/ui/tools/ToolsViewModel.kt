package com.dd.githubapp.ui.ui.tools

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ToolsViewModel : ViewModel() {

    private val _tools = MutableLiveData<String>().apply {
        value = ""
    }
    val tools: LiveData<String> = _tools
}