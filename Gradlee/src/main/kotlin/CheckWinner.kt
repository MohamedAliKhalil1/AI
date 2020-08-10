open class CheckWinner  : Check {
    var counter1 = 0
    var counter1ver = 0
    var counter1Hor = 0
    var counter2 = 0
    var counter2ver = 0
    var counter2Hor = 0
    var draw = false


    override fun checkwinner(i: Int, j: Int, game: Array<Array<Int>>): Int {
        var AI = false
        var Player = false

        checkdiagonal(game)
        checkVer(i  , j , game )
        checkHor(i, j, game)

        if (counter2 ==4 || counter2ver ==4 || counter2Hor ==4)
            return 2

        if (counter1 ==4 || counter1ver ==4 || counter1Hor ==4)
            return 1

        return 0
    }

    override fun checkwinnnerboolean(game: Array<Array<Int>>, player: Int, i: Int, j: Int): Boolean {


        var p = checkwinner(i = i, j = j , game = game)
        return p == player

    }
    override fun checkHor(i: Int, j: Int, game: Array<Array<Int>>){           // check winner Horizontaly
        counter1Hor = 0
        counter2Hor = 0

        if (!( j+3>6 )) {

            if (game[i][j] == 1 && game[i][j + 1] == 1 && game[i][j + 2] == 1 && game[i][j + 3] == 1)         // check player 1
                counter1Hor = 4
            if (game[i][j] == 2 && game[i][j + 1] == 2 && game[i][j + 2] == 2 && game[i][j + 3] == 2)  {       // check player 1
                counter2Hor = 4

            }
        }

        if (!( j-3<0 )) {

            if (game[i][j] == 1 && game[i][j - 1] == 1 && game[i][j - 2] == 1 && game[i][j - 3] == 1)         // check player 1
                counter1Hor = 4
            if (game[i][j] == 2 && game[i][j - 1] == 2 && game[i][j - 2] == 2 && game[i][j - 3] == 2)  {       // check player 1
                counter2Hor = 4

            }
        }

        if (!( j-1<0 || j+2>6 )) {

            if (game[i][j] == 1 && game[i][j + 1] == 1 && game[i][j + 2] == 1 && game[i][j - 1] == 1)         // check player 1
                counter1Hor = 4
            if (game[i][j] == 2 && game[i][j + 1] == 2 && game[i][j + 2] == 2 && game[i][j - 1] == 2) {        // check player 1
                counter2Hor = 4

            }
        }

        if (!( j-2<0 || j+1>6 )) {

            if (game[i][j] == 1 && game[i][j + 1] == 1 && game[i][j - 1] == 1 && game[i][j - 2] == 1)         // check player 1
                counter1Hor = 4
            if (game[i][j] == 2 && game[i][j + 1] == 2 && game[i][j - 1] == 2 && game[i][j - 2] == 2) {         // check player 1
                counter2Hor = 4

            }
        }



    }

    override fun checkVer(i: Int, j: Int, game: Array<Array<Int>>){           // check winner Vertically
        counter1ver = 0
        counter2ver = 0
        if (!( i+3>5 )) {
            if (game[i][j] == 1 && game[i + 1][j] == 1 && game[i + 2][j] == 1 && game[i + 3][j] == 1)         // check player 1
                counter1ver = 4
            if (game[i][j] == 2 && game[i + 1][j] == 2 && game[i + 2][j] == 2 && game[i + 3][j] == 2) {       // check player 1
                counter2ver = 4

            }
        }

    }


    override fun checkdiagonal(game: Array<Array<Int>>){            // check winner diagonaly
        counter1  = 0
        counter2  = 0
        for (c in 0..5) {
            for (r in 0..6) {

                if (!(c + 3 > 5 || r + 3 > 6)) {

                    if (game[c][r] == 1 && game[c + 1][r + 1] == 1 && game[c + 2][r + 2] == 1 && game[c + 3][r + 3] == 1) {
                        counter1 = 4

                    }

                    if (game[c][r] == 2 && game[c + 1][r + 1] == 2 && game[c + 2][r + 2] == 2 && game[c + 3][r + 3] == 2) {
                        counter2 = 4

                    }

                }

                if (!(c - 3 < 0 || r - 3 < 0)) {

                    if (game[c][r] == 1 && game[c - 1][r - 1] == 1 && game[c - 2][r - 2] == 1 && game[c - 3][r - 3] == 1) {
                        counter1 = 4

                    }
                    if (game[c][r] == 2 && game[c - 1][r - 1] == 2 && game[c - 2][r - 2] == 2 && game[c - 3][r - 3] == 2) {
                        counter2 = 4

                    }

                }
                if (!(r - 3 < 0 || c + 3 > 5)) {


                    if (game[c][r] == 1 && game[c + 1][r - 1] == 1 && game[c + 2][r - 2] == 1 && game[c + 3][r - 3] == 1) {
                        counter1 = 4

                    }
                    if (game[c][r] == 2 && game[c + 1][r - 1] == 2 && game[c + 2][r - 2] == 2 && game[c + 3][r - 3] == 2) {
                        counter2 = 4

                    }
                }
                if (!(r + 3 > 5 || c - 3 < 0)) {

                    if (game[c][r] == 1 && game[c - 1][r + 1] == 1 && game[c - 2][r + 2] == 1 && game[c - 3][r + 3] == 1) {
                        counter1 = 4

                    }
                    if (game[c][r] == 2 && game[c - 1][r + 1] == 2 && game[c - 2][r + 2] == 2 && game[c - 3][r + 3] == 2) {
                        counter2 = 4

                    }
                }

            }
        }

    }
    override fun checkdraw(game: Array<Array<Int>>): Boolean {

        for (i in 0..5) {
            for (j in 0..6) {
                if (game[i][j] == 0 ){
                    draw = false
                    return draw
                }
            }
        }
        draw = true
        if (draw == true)
            println("array full ")
        return draw
    }

    fun ifValidLocation(game : Array<Array<Int>>, i : Int , j : Int) : Boolean {
      return ((game[i][j] == 0) && ((i == 5) || ((i < 5 && i > -1) && (game[i+1][j] != 0))) )
    }


}
