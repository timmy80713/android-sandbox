package com.timmy.codelab.sandbox.arch

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class ViewBindingActivity<T : ViewBinding> : AppCompatActivity() {

    private var _viewBinding: T? = null

    protected val viewBinding: T
        get() = _viewBinding
            ?: throw ViewBindingUnavailableException("Cannot use ViewBinding before onCreate().")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _viewBinding = createViewBinding(layoutInflater).apply { setContentView(root) }
    }

    abstract fun createViewBinding(inflater: LayoutInflater): T
}