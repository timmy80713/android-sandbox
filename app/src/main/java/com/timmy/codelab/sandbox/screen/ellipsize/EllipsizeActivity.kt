package com.timmy.codelab.sandbox.screen.ellipsize

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import androidx.core.view.doOnPreDraw
import com.timmy.codelab.sandbox.R
import com.timmy.codelab.sandbox.arch.ViewBindingActivity
import com.timmy.codelab.sandbox.databinding.ActivityEllipsizeBinding

class EllipsizeActivity : ViewBindingActivity<ActivityEllipsizeBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViews()
    }

    override fun createViewBinding(inflater: LayoutInflater) =
        ActivityEllipsizeBinding.inflate(inflater)


    private fun setupViews() {
        viewBinding.textView.apply {
            text = getString(R.string.lorem)
            doOnPreDraw {
                val width = measuredWidth.toFloat()
                val truncateAt = TextUtils.TruncateAt.MIDDLE
                text = TextUtils.ellipsize(text, paint, width, truncateAt)
            }
        }
    }
}