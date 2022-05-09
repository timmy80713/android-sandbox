package com.timmy.codelab.sandbox.utils

import android.text.InputFilter
import android.text.Spanned
import android.util.Log

/**
 * Use grapheme cluster calculate count of words.
 * This filter will cut out more than [maxCount] the word count when calculated count exceeds [maxCount].
 */
class GraphemeClusterCountInputFilter(private val maxCount: Int) : InputFilter {
    override fun filter(
        source: CharSequence,
        start: Int,
        end: Int,
        dest: Spanned,
        dstart: Int,
        dend: Int
    ): CharSequence? {
        Log.i("TAG", "TimmmmmmY source: $source, start: $start, end: $end")
        Log.i("TAG", "TimmmmmmY dest: $dest")
        Log.i("TAG", "TimmmmmmY dstart: $dstart, dend: $dend")

        val destGraphemeClusterBreaks = GraphemeClusterHelper.findBreaks(dest.toString())
        Log.i("TAG", "TimmmmmmY destGraphemeClusterBreaks: $destGraphemeClusterBreaks")

        val keep = maxCount - (destGraphemeClusterBreaks.count { it <= dstart || it > dend })

        Log.i("TAG", "TimmmmmmY keep a: $keep")
        val sourceGraphemeClusters = GraphemeClusterHelper.split(source.toString())
        return when {
            keep <= 0 -> {
                Log.i("TAG", "TimmmmmmY 超過")
                Log.i("TAG", "TimmmmmmY ==============================")
                ""
            }
            keep >= sourceGraphemeClusters.size -> {
                Log.i("TAG", "TimmmmmmY 沒超過")
                Log.i("TAG", "TimmmmmmY ==============================")
                null
            }
            else -> {
                Log.i("TAG", "TimmmmmmY keep b: $keep")
                val a = sourceGraphemeClusters.take(keep).joinToString(separator = "")
                Log.i("TAG", "TimmmmmmY result: $a")
                Log.i("TAG", "TimmmmmmY ==============================")
                a
            }
        }
    }
}