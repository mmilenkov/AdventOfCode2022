package day6

import readText

fun main() {
    fun firstMarkerAt(length: Int): Int {
        var counter = length
        readText("day6").windowed(length, 1).forEach { step ->
            val set = mutableSetOf<String>()
            step.forEach { c -> set.add(c.toString()) }
            if (set.size == length) {
                return counter
            } else {
                counter++
            }
        }
        return counter
    }

    fun part1() {
        println(firstMarkerAt(4))
    }

    fun part2() {
        println(firstMarkerAt(14))
    }

    part1()
    part2()
}