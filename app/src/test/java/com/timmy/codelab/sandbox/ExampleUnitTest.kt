package com.timmy.codelab.sandbox

import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun testRemainingTimeInMillis() {
        val remainTimeInMillis = try {
            val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ROOT).apply {
                timeZone = TimeZone.getTimeZone("UTC")
            }
            val expiredAt = "2021-09-018T15:00:00Z"
            val expiredTimeInMillis = format.parse(expiredAt)?.time ?: 0
            val remainingTimeInMills = expiredTimeInMillis - System.currentTimeMillis()
            if (remainingTimeInMills > 0) remainingTimeInMills else null
        } catch (e: Exception) {
            null
        }

        if (remainTimeInMillis == null) {
            println("到期")
            return
        }

        val days = TimeUnit.MILLISECONDS.toDays(remainTimeInMillis)

        val hours = TimeUnit.MILLISECONDS.toHours(remainTimeInMillis) -
                TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(remainTimeInMillis))

        val minutes = TimeUnit.MILLISECONDS.toMinutes(remainTimeInMillis) -
                TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(remainTimeInMillis))

        val seconds = TimeUnit.MILLISECONDS.toSeconds(remainTimeInMillis) -
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(remainTimeInMillis))

        println("剩餘：$days 日 $hours 時 $minutes 分 $seconds 秒")
    }


    @Test
    fun testDateStringFormat() {
        val dateString = "2021-09-02T12:00:00Z"
        val format1 = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())

        val format2 = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault()).apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }
        val date1 = format1.parse(dateString)

        val format3 = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX", Locale.ROOT)

        val date2 = format2.parse(dateString)
        val date3 = format3.parse(dateString)
        println("1: ${date1},${date1.time}")
        println("2: ${date2},${date2.time}")
        println("3: ${date3},${date3.time}")
    }

    @Test
    fun testExpiryValue() {
        val createdAt = "2021-09-02T12:00:00Z"
        val expiredAt = "2021-09-03T12:00:00Z"
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ROOT).apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }
        val createdTimeInMillis = format.parse(createdAt)?.time ?: 0
        val expiredTimeInMillis = format.parse(expiredAt)?.time ?: 0
        val createdDays = TimeUnit.MILLISECONDS.toDays(createdTimeInMillis).toInt()
        val expiredDays = TimeUnit.MILLISECONDS.toDays(expiredTimeInMillis).toInt()
        println(createdDays)
        println(expiredDays)
        println(expiredDays - createdDays)
    }
}