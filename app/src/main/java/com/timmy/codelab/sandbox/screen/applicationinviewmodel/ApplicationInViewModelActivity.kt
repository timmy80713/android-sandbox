package com.timmy.codelab.sandbox.screen.applicationinviewmodel

import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.timmy.codelab.sandbox.arch.ViewBindingActivity
import com.timmy.codelab.sandbox.databinding.ActivityApplicationInViewModelBinding

class ApplicationInViewModelActivity :
    ViewBindingActivity<ActivityApplicationInViewModelBinding>() {

    val viewModel: ApplicationInViewModel by lazy {
        ViewModelProvider(this).get(ApplicationInViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModel()
        viewModel.fetchPage()
    }

    override fun createViewBinding(inflater: LayoutInflater) =
        ActivityApplicationInViewModelBinding.inflate(inflater)

    private fun setupViewModel() {
        viewModel.text.observe(this) {
            viewBinding.textView.text = it
        }
    }
}