package day2

import readText

sealed class Shape(val score: Int) {
    object Rock: Shape(1)
    object Paper: Shape(2)
    object Scissors: Shape(3)

    companion object {
        fun toShape(input: String): Shape {
            return if (isPaper(input)) {
                Paper
            } else if (isRock(input)) {
                Rock
            } else {
                Scissors
            }
        }

        private fun isRock(input: String) = "A" == input || "X" == input
        private fun isPaper(input: String) = "B" == input || "Y" == input
    }
}

fun main() {
    fun part1() {
        fun isWin(player:Shape, opponent: Shape): Boolean {
            if (player == Shape.Rock && opponent == Shape.Scissors) {
                return true
            } else if (player == Shape.Paper && opponent == Shape.Rock) {
                return true
            } else if (player == Shape.Scissors && opponent == Shape.Paper) {
                return true
            }
            return false
        }

        fun calculateRoundScore(opponent: String, player: String): Int {
            val playerShape = Shape.toShape(player)
            val opponentShape = Shape.toShape(opponent)
            var score = playerShape.score
            score += if (playerShape == opponentShape) {
                3
            } else if (isWin(playerShape, opponentShape)) {
                6
            } else {
                0
            }

            return score
        }

        fun calculate(round: String)  = round.split("\n")
            .map { split ->
                split.split(" ")
            }.sumOf { game ->
                calculateRoundScore(game[0], game[1])
            }

        println(calculate(readText("day2")))
    }

    fun part2() {
        fun toWinningShape(input: Shape): Shape {
            return when (input) {
                Shape.Paper -> {
                    Shape.Scissors
                }
                Shape.Rock -> {
                    Shape.Paper
                }
                else -> {
                    Shape.Rock
                }
            }
        }

        fun toLoseShape(input: Shape): Shape {
            return when (input) {
                Shape.Rock -> {
                    Shape.Scissors
                }
                Shape.Scissors -> {
                    Shape.Paper
                }
                else -> {
                    Shape.Rock
                }
            }
        }

        fun calculateRoundScore(opponent: String, round: String): Int {
            val opponentShape = Shape.toShape(opponent)
            val score = when (round) {
                "Y" -> {
                    3 + opponentShape.score
                }
                "Z" -> {
                    6 + toWinningShape(opponentShape).score
                }
                else -> {
                    toLoseShape(opponentShape).score
                }
            }

            return score
        }

        fun calculate(round: String)  = round.split("\n")
            .map { split ->
                split.split(" ")
            }.sumOf { game ->
                calculateRoundScore(game[0], game[1])
            }

        println(calculate(readText("day2")))
    }

    part1()
    part2()
}