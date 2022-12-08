package day7

import readLines

fun main() {
    val pattern = Regex("""[$] cd (.*)|(\d+).*""")

    fun directorySizes(data: List<String>) = buildMap {
        put("", 0)
        var currentDirectory = ""
        for (line in data) {
            val result = pattern.matchEntire(line) ?: continue // Nothing matches skip
            result.groups[1]?.value?.let {directory -> //These match part one of the regex. Namely this is a cd
                currentDirectory = when (directory) {
                    "/" -> "" //Root directory
                    ".." -> currentDirectory.substringBeforeLast("/","") // Clear the current directory
                    else -> if (currentDirectory.isEmpty()) directory else "$currentDirectory/$directory" // Simplifies the above case or it would be goto empty string
                }
            } ?: result.groups[2]?.value?.toIntOrNull()?.let { size -> // These match part two of the regex namely this counts file size
                var directory = currentDirectory
                while (true) {
                    put(directory, getOrElse(directory) { 0 } + size) // If we don't have it we need to make sure we have some value
                    if (directory.isEmpty()) break
                    directory = directory.substringBeforeLast("/", "")
                }
            }
        }
    }

    fun part1() {
        val data = readLines("day7")

        println(directorySizes(data).values.sumOf { size -> if (size <= 100000) size else 0 })
    }

    fun part2() {

        println(directorySizes(readLines("day7")).values
            .asSequence()
            .filter { 70000000 - (directorySizes(readLines("day7")).getValue("") - it) >= 30000000 }
            .min()
        )
    }

    part1()
    part2()

}