package com.timmy.codelab.sandbox.utils

import android.icu.text.BreakIterator
import android.os.Build
import com.github.hiking93.graphemesplitterlite.GraphemeSplitter

/**
 * Processing "user-perceived characters" a.k.a "Grapheme Clusters".
 *
 * @author Hiking
 */
object GraphemeClusterHelper {

    /**
     * Split [text] into Grapheme Clusters.
     */
    fun split(text: String): List<String> {
        val breaks = findBreaks(text)
        return breaks.mapIndexed { i, breakIndex ->
            val lastBreakIndex = if (i == 0) 0 else breaks[i - 1]
            text.substring(lastBreakIndex, breakIndex)
        }
    }

    /**
     * Get Grapheme Cluster breaks of [text].
     */
    fun findBreaks(
        text: String
    ): List<Int> = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        // Use ICU implementation if available.
        val breakIterator = BreakIterator.getCharacterInstance().apply { setText(text) }
        val breaks = mutableListOf<Int>()
        while (true) {
            val index = breakIterator.next()
            if (index == BreakIterator.DONE) break
            breaks += index
        }
        breaks
    } else {
        /** Fallback to patched implementation based on [java.text.BreakIterator]. */
        GraphemeSplitter().findBreaks(text)
    }
}