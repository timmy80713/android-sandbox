package com.timmy.codelab.sandbox.utils

import android.icu.text.BreakIterator
import android.os.Build

class GraphemeClusterHelper {
    companion object {
        private const val ZERO_WIDTH_JOINER = '\u200D'
        private val REGIONAL_INDICATOR_RANGE = 0x1F1E6..0x1F1FF
        private val EMOJI_MODIFIER_RANGE = 0x1F3FB..0x1F3FF
        private val TAG_RANGE = 0xE0020..0xE007F

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
        ): List<Int> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                // Use ICU implementation if available.
                val breakIterator = BreakIterator.getCharacterInstance().apply { setText(text) }
                val breaks = mutableListOf<Int>()
                while (true) {
                    val index = breakIterator.next()
                    if (index == BreakIterator.DONE) break
                    breaks += index
                }
                return breaks
            }

            /** Fallback to patched implementation based on [java.text.BreakIterator]. */
            val breakIterator = java.text.BreakIterator.getCharacterInstance().apply {
                setText(text)
            }
            val breaks = mutableListOf<Int>()
            var regionalIndicatorPairEndIndex = 0
            while (true) {
                val index = breakIterator.next()
                if (index == java.text.BreakIterator.DONE) break
                if (index == text.length) {
                    breaks += index
                    continue
                }

                // Check ZWJ.
                if (text[index - 1] == ZERO_WIDTH_JOINER || text.getOrNull(index) == ZERO_WIDTH_JOINER) continue

                // Check emoji modifier.
                if (EMOJI_MODIFIER_RANGE.contains(Character.codePointAt(text, index))) continue

                // Check regional indicator symbols (flags).
                val codePointBeforeIndex = Character.codePointBefore(text, index)
                val codePointAtIndex = Character.codePointAt(text, index)
                if (
                    REGIONAL_INDICATOR_RANGE.contains(codePointBeforeIndex) &&
                    REGIONAL_INDICATOR_RANGE.contains(codePointAtIndex)
                ) {
                    if (regionalIndicatorPairEndIndex < index) {
                        regionalIndicatorPairEndIndex =
                            index + Character.toChars(codePointAtIndex).size
                        continue
                    }
                }

                // Check tags.
                if (TAG_RANGE.contains(Character.codePointAt(text, index))) continue

                breaks += index
            }
            return breaks
        }
    }
}