package day3

import readLines

fun main() {
    fun part1() {
        val data = readLines("day3")

        var score = 0
        data.forEach { line ->
            val pivot = line.length / 2
            val compartment1 = line.substring(0, pivot)
            val compartment2 = line.substring(pivot, line.length)
            var repeating = 'a'
            run comparison@ {
                compartment1.forEach { char ->
                    if (compartment2.contains(char)) {
                        repeating = char
                        return@comparison
                    }
                }
            }
            score += repeating.toScore()
        }
        println(score)
    }

    fun part2() {

    }

    part1()
    part2()
}

// I really need to find ways to remember this
fun Char.toScore(): Int =
    if (this.isUpperCase()) {
        this.code - 'A'.code + 27
    } else {
        this.code - 'a'.code + 1
    }