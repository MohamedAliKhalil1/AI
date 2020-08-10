

import java.awt.Point
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.max
import kotlin.math.min

class Engine : AI{
    var r = 0
    var c = 0
    var is_terminal_node = false
    var lastplayed: Point = Point()
    var checker : CheckWinner
    constructor(){
        checker = CheckWinner()
    }

    override fun get_valid_col_locations(game: Array<Array<Int>>): ArrayList<Int> {

        var valid_Col_locations = ArrayList<Int>()

        for (j in 0..6) {

            if (game[0][j] == 0)
                valid_Col_locations.add(j)
        }
        return valid_Col_locations
    }

    override fun get_open_row(game: Array<Array<Int>>, j: Int): Int {

        for (r in 0..5) {
            if (game[r][j] == 0)
                return r
        }
        return null!!
    }

    override fun drop(game: Array<Array<Int>>, r: Int, c: Int, piece: Int) {
        game[r][c] = piece

    }

    override fun bestMove(game: Array<Array<Int>>, x: Int, y: Int, p : Int) : Point{
        // AI to make its turn
        var bestScore = -1000000
        var move: Point = Point()
        for (i in 5 downTo 0) {
            for (j in 0..6) {
                // Is the spot available?
                if ((game[i][j] == 0 && i == 5) || (i < 5 && game[i + 1][j] != 0 && game[i][j] == 0)) {
                    game[i][j] = p
                    var score = minimax(game, 0, false, i, j, -1000000, 1000000, p)
                    game[i][j] = 0
                    if (score > bestScore) {
                        bestScore = score
                        move = Point(i, j)
                    }
                }
            }
        }
        game[move.x][move.y] = p
        return move
    }

    override fun minimax(game: Array<Array<Int>>, depth: Int, isMaximizing: Boolean, x: Int, y: Int, alpha: Int, beta: Int , p : Int): Int {
        var alpa = alpha
        var bta = beta
        if (checker.checkwinnnerboolean(game, 1, x, y))
            if(p == 2)
                return -1
            else
                return 1
        if (checker.checkwinnnerboolean(game, 2, x, y))
            if(p == 2)
                return 1
            else
                return -1
        if (checker.checkdraw(game))
            return 0
        if (depth == 5)
            return 0

        if (isMaximizing) {
            var bestscore = -1000000
            for (j in 5 downTo 0) {
                for (i in 0..6) {

                    if ((game[j][i] == 0 && j == 5) || (j < 5 && game[j + 1][i] != 0 && game[j][i] == 0)) {
                        game[j][i] = p;
                        var score = minimax(game, depth + 1, false, j, i, alpa, bta, p)
                        game[j][i] = 0
                        bestscore = max(score, bestscore)
                        alpa = max(alpa, bestscore)
                        if (alpa >= bta)
                            break
                    }
                }
            }
            return bestscore
        } else {
            var bestscore = 1000000
            for (j in 5 downTo 0) {
                for (i in 0..6) {

                    if ((game[j][i] == 0 && j == 5) || (j < 5 && game[j + 1][i] != 0 && game[j][i] == 0)) {
                        if (p ==2)
                            game[j][i] = 1
                        else
                            game[j][i] = 2

                        var score = minimax(game, depth + 1, true, j, i, alpa, bta, p)
                        game[j][i] = 0
                        bestscore = min(score, bestscore)
                        bta = min(beta, bestscore)
                        if (alpa >= bta)
                            break

                    }
                }

            }
            return bestscore
        }
    }



}