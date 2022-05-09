package com.timmy.codelab.sandbox.screen

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.timmy.codelab.sandbox.R

class LobbyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lobby)
        findViewById<TextView>(R.id.textView).apply {

        }
    }
}