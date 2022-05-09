package com.timmy.codelab.sandbox.screen.applicationinviewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.timmy.codelab.sandbox.R

class ApplicationInViewModel(
    application: Application
) : AndroidViewModel(application) {

    val text = MutableLiveData<String>()

    init {
        fetchPage()
    }

    fun fetchPage() {
        text.value = getApplication<Application?>().getString(R.string.milk)
    }
}