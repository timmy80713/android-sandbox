package com.timmy.codelab.sandbox.screen.graphemecluster

import android.os.Bundle
import android.view.LayoutInflater
import com.timmy.codelab.sandbox.arch.ViewBindingActivity
import com.timmy.codelab.sandbox.databinding.ActivityGraphemeClusterBinding
import com.timmy.codelab.sandbox.utils.GraphemeClusterCountInputFilter

class GraphemeClusterActivity : ViewBindingActivity<ActivityGraphemeClusterBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViews()
    }

    override fun createViewBinding(inflater: LayoutInflater) =
        ActivityGraphemeClusterBinding.inflate(inflater)

    private fun setupViews() {
        viewBinding.editText.filters = arrayOf(GraphemeClusterCountInputFilter(5))
    }
}