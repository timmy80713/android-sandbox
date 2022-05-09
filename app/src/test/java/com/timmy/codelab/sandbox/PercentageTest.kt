package com.timmy.codelab.sandbox

import org.junit.Test
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.roundToInt
import kotlin.random.Random

class PercentageTest {
    @Test
    fun test() {
//        val a = listOf(0, abs(Random.nextInt()), abs(Random.nextInt()), abs(Random.nextInt()))
        val a = listOf(1, 0, 0, 0)
        val totalCount = a.map { it.toLong() }.sum()
        val options = arrayListOf<Option>().apply {
            add(Option("我是 id-1", a[0]))
            add(Option("我是 id-2", a[1]))
            add(Option("我是 id-3", a[2]))
            add(Option("我是 id-4", a[3]))
        }
        getCalculatedPercentageMap(totalCount, options)
    }


    private fun getCalculatedPercentageMap(
        totalCount: Long,
        options: List<Option>
    ) {
        if (totalCount == 0L) {
            options.associate { it.id to it.voteCount }
        } else {
            var totalPercentage = 100

            val origin = options
                .sortedBy { it.voteCount }
                .map { it.id to it.voteCount.toFloat() / totalCount * 100 }

            val adjusted = options
                .sortedBy { it.voteCount }
                .mapIndexed { index, option ->
                    val percentage = (option.voteCount.toFloat() / totalCount * 100)
                        .let { if (it < 1F) ceil(it).toInt() else it.roundToInt() }
                        .let {
                            if (index == options.lastIndex) {
                                totalPercentage
                            } else {
                                totalPercentage -= it
                                it
                            }
                        }
                    option.id to percentage
                }
            println("origin: $origin")
            println("adjust: $adjusted")
            println("total: ${adjusted.sumOf { it.second }}")
        }
    }

    private data class Option(val id: String, val voteCount: Int)
}