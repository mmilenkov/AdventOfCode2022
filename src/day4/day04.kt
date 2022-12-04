package day4

import readLines

data class Elf(val start: Int, val end: Int) {
    fun isCovered(elf: Elf) = this.start <= elf.start && this.end >= elf.end
    fun hasOverlap(elf: Elf) = maxOf(this.start, elf.start) <= minOf(this.end, elf.end)

    companion object {
        fun fromString(string: String): Elf {
            return Elf(string.substringBefore("-").toInt(), string.substringAfter("-").toInt())
        }
    }

}

fun main() {
    fun part1() {
        val sum = readLines("day4").filter { pair ->
            val elf1 = Elf.fromString(pair.substringBefore(","))
            val elf2 = Elf.fromString(pair.substringAfter(","))
            elf1.isCovered(elf2) || elf2.isCovered(elf1)
        }.size

        println(sum)
    }

    fun part2() {
        val sum = readLines("day4").filter { pair ->
            val elf1 = Elf.fromString(pair.substringBefore(","))
            val elf2 = Elf.fromString(pair.substringAfter(","))
            elf1.hasOverlap(elf2)
        }.size
        println(sum)
    }

    part1()
    part2()
}