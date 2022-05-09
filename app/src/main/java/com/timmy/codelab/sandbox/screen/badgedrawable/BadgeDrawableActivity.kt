package com.timmy.codelab.sandbox.screen.badgedrawable

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils
import com.timmy.codelab.sandbox.R
import com.timmy.codelab.sandbox.arch.ViewBindingActivity
import com.timmy.codelab.sandbox.databinding.ActivityBadgeDrawableBinding

class BadgeDrawableActivity : ViewBindingActivity<ActivityBadgeDrawableBinding>() {

    private val unreadCount = MutableLiveData<Int>()

    private val badgeDrawable by lazy {
        BadgeDrawable.create(this).apply {
            clearNumber()
            backgroundColor = Color.BLUE
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
        }
    }

    private fun setupViewModel() {
        unreadCount.observe(this, ::updateUnreadCount)
    }

    private fun updateUnreadCount(unreadCount: Int) {
        val previousUnreadCount = badgeDrawable.number
        if (previousUnreadCount == 0 && unreadCount == 0) return

        badgeDrawable.number = unreadCount

        if (previousUnreadCount == 0 && unreadCount != 0) {
            BadgeUtils.attachBadgeDrawable(
                badgeDrawable,
                viewBinding.toolbar,
                R.id.action_group_chat
            )
        }
        if (previousUnreadCount != 0 && unreadCount == 0) {
            BadgeUtils.detachBadgeDrawable(
                badgeDrawable,
                viewBinding.toolbar,
                R.id.action_group_chat
            )
        }
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