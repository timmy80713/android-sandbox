package com.timmy.codelab.sandbox

import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class DateFormatTest {
    @Test
    fun test() {
        val dateString1 = "2022-03-30T03:05:52.839299Z"
        val dateString2 = "2022-03-30T03:05:52.839Z"
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault()).apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }
        println("TimmmmmmY 1: ${format.parse(dateString1)}")
        println("TimmmmmmY 2: ${format.parse(dateString2)}")
        val dateString3 = "2022-03-30T03:05:52Z"
        val format2 = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault()).apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }
        println("TimmmmmmY 3: ${format2.parse(dateString3)}")
    }
}