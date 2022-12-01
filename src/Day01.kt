import java.io.File

fun main() {
    fun calculateMax(input: String): Int  = input.split("\n\n")
            .map { elf ->
                elf.lines()
                    .sumOf {
                        it.toInt()
                    }
            }.max()

    println(calculateMax(input = readText("day1")))
}
