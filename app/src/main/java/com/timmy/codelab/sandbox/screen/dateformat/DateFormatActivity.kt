package com.timmy.codelab.sandbox.screen.dateformat

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class DateFormatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dateString1 = "2022-03-30T03:05:52.839299Z"
        val dateString2 = "2022-03-30T03:05:52.839Z"

        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault()).apply {
//            timeZone = TimeZone.getTimeZone("UTC")
        }
        Log.i("TAG", "TimmmmmmY 1: ${format.parse(dateString1)}")
        Log.i("TAG", "TimmmmmmY 2: ${format.parse(dateString2)}")

        val dateString3 = "2022-03-30T03:05:52Z"
        val format2 = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault()).apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }
        Log.i("TAG", "TimmmmmmY 3: ${format2.parse(dateString3)}")
    }
}
