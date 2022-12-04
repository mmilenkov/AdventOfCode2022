package day3

import readLines

fun main() {
    fun part1() {
        var score = 0
        readLines("day3").forEach { line ->
            val pivot = line.length / 2
            val compartment1 = line.substring(0, pivot)
            val compartment2 = line.substring(pivot, line.length)
            run comparison@ {
                compartment1.forEach { char ->
                    if (compartment2.contains(char)) {
                        score += char.toScore()
                        return@comparison
                    }
                }
            }
        }
        println(score)
    }

    fun part2() {
        var data = readLines("day3")
        var sum = 0
        while (data.isNotEmpty()) {
            val elves = data.take(3)
            data = data.drop(3)
            run comparison@{
                elves[0].forEach { char ->
                    if (elves[1].contains(char) && elves[2].contains(char)) {
                        sum += char.toScore()
                        return@comparison
                    }
                }
            }
        }

        println(sum)
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