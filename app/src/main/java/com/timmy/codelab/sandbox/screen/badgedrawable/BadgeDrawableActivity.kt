package com.timmy.codelab.sandbox.screen.badgedrawable

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import androidx.core.view.doOnLayout
import androidx.lifecycle.MutableLiveData
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils
import com.timmy.codelab.sandbox.arch.ViewBindingActivity
import com.timmy.codelab.sandbox.databinding.ActivityBadgeDrawableBinding

class BadgeDrawableActivity : ViewBindingActivity<ActivityBadgeDrawableBinding>() {

    private val unreadCount = MutableLiveData<Int>()

    private val badgeDrawable by lazy {
        BadgeDrawable.create(this).apply {
            backgroundColor = Color.BLUE
            badgeGravity = BadgeDrawable.TOP_END
            isVisible = true
            maxCharacterCount = 3
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViews()
        setupViewModel()
    }

    override fun createViewBinding(inflater: LayoutInflater) =
        ActivityBadgeDrawableBinding.inflate(inflater)


    private fun setupViews() {
        viewBinding.apply {
            plus.setOnClickListener(::onPlusClicked)
            minus.setOnClickListener(::onMinusClicked)
            imageButton.doOnLayout { view ->
                view as ImageButton
                badgeDrawable.apply {
                    horizontalOffset = view.measuredWidth / 2
                    verticalOffset = view.measuredHeight / 2
                }.also { badgeDrawable ->
                    BadgeUtils.attachBadgeDrawable(badgeDrawable, view)
                }
            }
        }
    }

    private fun setupViewModel() {
        unreadCount.observe(this, ::updateUnreadCount)
    }

    private fun updateUnreadCount(unreadCount: Int) {
        badgeDrawable.number = unreadCount
        badgeDrawable.isVisible = unreadCount != 0
    }

    private fun onPlusClicked(view: View) {
        var unreadCount = unreadCount.value ?: 0
        this.unreadCount.value = ++unreadCount
    }

    private fun onMinusClicked(view: View) {
        var unreadCount = unreadCount.value ?: 0
        if (unreadCount == 0) return
        this.unreadCount.value = --unreadCount
    }
}