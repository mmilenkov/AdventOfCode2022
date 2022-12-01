import java.io.File

fun main() {
    fun calculateMax(input: String): Int?  =
        input.split("\n\n")
            .maxOfOrNull { elf ->
                elf.lines()
                    .sumOf {
                        it.toInt()
                    }
    }

    fun calculateSumOfThree(input: String): Int  =
        input.split("\n\n")
            .map { elf ->
                elf.lines()
                    .sumOf {
                        it.toInt()
                    }
            }.sortedDescending()
            .subList(0,3)
            .sum()

    println(calculateMax(input = readText("day1")))
    println(calculateSumOfThree(input = readText("day1")))
}
