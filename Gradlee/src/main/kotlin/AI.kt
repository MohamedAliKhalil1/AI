import java.awt.Point

interface AI {
    fun get_valid_col_locations(game: Array<Array<Int>>): ArrayList<Int>
    fun get_open_row(game: Array<Array<Int>>, j: Int): Int
    fun drop(game: Array<Array<Int>>, r: Int, c: Int, piece: Int)
    fun bestMove(game: Array<Array<Int>>, x: Int, y: Int, p: Int) : Point
    fun minimax(game: Array<Array<Int>>, depth: Int, isMaximizing: Boolean, x: Int, y: Int, alpha: Int, beta: Int, p : Int): Int
}