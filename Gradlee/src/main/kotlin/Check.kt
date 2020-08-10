interface Check {

    fun checkwinner(i: Int, j: Int, game: Array<Array<Int>>): Int
    fun checkwinnnerboolean(game: Array<Array<Int>>, player: Int, i: Int, j: Int): Boolean
    fun checkHor(i: Int, j: Int, game: Array<Array<Int>>)
    fun checkVer(i: Int, j: Int,  game: Array<Array<Int>>)
    fun checkdiagonal( game: Array<Array<Int>>)
    fun checkdraw( game: Array<Array<Int>>): Boolean
}

