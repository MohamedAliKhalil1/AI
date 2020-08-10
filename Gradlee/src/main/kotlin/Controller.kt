import java.awt.Point
import java.util.*
import io.javalin.Javalin
import io.javalin.http.Context
import java.util.Stack


class Control{
    var lastplayed : Point = Point()
    var checker : CheckWinner
    var engine : Engine
    var live : Boolean = true
    var g = arrayOf<Array<Int>>() // our board
    var iswinner : Boolean = false
    var playedList = arrayOfNulls<Point>(42)
    var undoPointer :Int = -1
    var lastplayedplayer : Point = Point()

    constructor(){
        checker = CheckWinner()
        engine = Engine()
        for (i in 0..5 ) {                  //create 2D Array and set its element with zero
            var array =  arrayOf<Int>()
            for (j in 0..6) {
                array += 0
            }
            g += array
        }

    }
    fun undo(){
        if(undoPointer > -1){
            var temp : Point? = playedList[undoPointer]
            if (temp != null) {
                g[temp.x][temp.y] = 0
            }
            undoPointer --
            if(undoPointer > -1) {
                for(i in 0..5)
                    print(playedList[i])
                temp = playedList[undoPointer]
                if (temp != null) {
                    lastplayed.x = temp.x
                    lastplayed.y = temp.y
                }
            }
        }
        for(i in 0..5){
            for (j in 0..6){
            print(g[i][j])
        }
        }

    }
    fun getnextvalidrow( game : Array<Array<Int>>, col : Int): Int {
        for(i in 5 downTo 0){
            if(game[i][col] == 0)
                return i;
        }
        return -1
    }

    fun getcolour(player : Int): String {
        if (player == 1 ){
            return "red"
        }
        if (player == 2 ){
            return "yellow"
        }
        return "none"
    }
    fun printboard(){
        for(i in 0..5){
            for(j in 0..6)
                print(this.g[i][j])
            println()
        }
    }

    fun toString(game: Array<Array<Int>>): String {
        var gridHTML = ""
        for (i in 0..5){
            gridHTML += "<tr>\n"
            for (j in 0..6){
                gridHTML += String.format("   <td id=\"c-%d-%d\" class=\"%s\" onclick=\"sendMove(%d)\"></td>\n", i, j, getcolour(game[i][j]),j);
            }
            gridHTML += "</tr>\n"
        }
        return gridHTML
                /*String.format("   <td id=\"c-1-1\" class=\"%s\" onclick=\"sendMove(0)\"></td>\n", getcolour(game[0][0])) +
                String.format("   <td id=\"c-1-2\" onclick=\"sendMove(1)\">%d</td>\n", game[0][1]) +
                String.format("   <td id=\"c-1-3\" onclick=\"sendMove(2)\">%d</td>\n", game[0][2]) +
                String.format("   <td id=\"c-1-4\" onclick=\"sendMove(3)\">%d</td>\n", game[0][3]) +
                String.format("   <td id=\"c-1-5\" onclick=\"sendMove(4)\">%d</td>\n", game[0][4]) +
                String.format("   <td id=\"c-1-6\" onclick=\"sendMove(5)\">%d</td>\n", game[0][5]) +
                String.format("   <td id=\"c-1-7\" onclick=\"sendMove(6)\">%d</td>\n", game[0][6]) +
                "</tr>\n" +
                "<tr>\n" +
                String.format("   <td id=\"c-2-1\" onclick=\"sendMove(0)\">%d</td>\n", game[1][0]) +
                String.format("   <td id=\"c-2-2\" onclick=\"sendMove(1)\">%d</td>\n", game[1][1]) +
                String.format("   <td id=\"c-2-3\" onclick=\"sendMove(2)\">%d</td>\n", game[1][2]) +
                String.format("   <td id=\"c-2-4\" onclick=\"sendMove(3)\">%d</td>\n", game[1][3]) +
                String.format("   <td id=\"c-2-5\" onclick=\"sendMove(4)\">%d</td>\n", game[1][4]) +
                String.format("   <td id=\"c-2-6\" onclick=\"sendMove(5)\">%d</td>\n", game[1][5]) +
                String.format("   <td id=\"c-2-7\" onclick=\"sendMove(6)\">%d</td>\n", game[1][6]) +
                "</tr>\n" +
                "<tr>\n" +
                String.format("   <td id=\"c-3-1\" onclick=\"sendMove(0)\">%d</td>\n", game[2][0]) +
                String.format("   <td id=\"c-3-2\" onclick=\"sendMove(1)\">%d</td>\n", game[2][1]) +
                String.format("   <td id=\"c-3-3\" onclick=\"sendMove(2)\">%d</td>\n", game[2][2]) +
                String.format("   <td id=\"c-3-4\" onclick=\"sendMove(3)\">%d</td>\n", game[2][3]) +
                String.format("   <td id=\"c-3-5\" onclick=\"sendMove(4)\">%d</td>\n", game[2][4]) +
                String.format("   <td id=\"c-3-6\" onclick=\"sendMove(5)\">%d</td>\n", game[2][5]) +
                String.format("   <td id=\"c-3-7\" onclick=\"sendMove(6)\">%d</td>\n", game[2][6]) +
                "</tr>\n"+
                "<tr>\n" +
                String.format("   <td id=\"c-4-1\" onclick=\"sendMove(0)\">%d</td>\n", game[3][0]) +
                String.format("   <td id=\"c-4-2\" onclick=\"sendMove(1)\">%d</td>\n", game[3][1]) +
                String.format("   <td id=\"c-4-3\" onclick=\"sendMove(2)\">%d</td>\n", game[3][2]) +
                String.format("   <td id=\"c-4-4\" onclick=\"sendMove(3)\">%d</td>\n", game[3][3]) +
                String.format("   <td id=\"c-4-5\" onclick=\"sendMove(4)\">%d</td>\n", game[3][4]) +
                String.format("   <td id=\"c-4-6\" onclick=\"sendMove(5)\">%d</td>\n", game[3][5]) +
                String.format("   <td id=\"c-4-7\" onclick=\"sendMove(6)\">%d</td>\n", game[3][6]) +
                "</tr>\n" +
                "<tr>\n" +
                String.format("   <td id=\"c-5-1\" onclick=\"sendMove(0)\">%d</td>\n", game[4][0]) +
                String.format("   <td id=\"c-5-2\" onclick=\"sendMove(1)\">%d</td>\n", game[4][1]) +
                String.format("   <td id=\"c-5-3\" onclick=\"sendMove(2)\">%d</td>\n", game[4][2]) +
                String.format("   <td id=\"c-5-4\" onclick=\"sendMove(3)\">%d</td>\n", game[4][3]) +
                String.format("   <td id=\"c-5-5\" onclick=\"sendMove(4)\">%d</td>\n", game[4][4]) +
                String.format("   <td id=\"c-5-6\" onclick=\"sendMove(5)\">%d</td>\n", game[4][5]) +
                String.format("   <td id=\"c-5-7\" onclick=\"sendMove(6)\">%d</td>\n", game[4][6]) +
                "</tr>\n" +
                "<tr>\n" +
                String.format("   <td id=\"c-6-1\" onclick=\"sendMove(0)\">%d</td>\n", game[5][0]) +
                String.format("   <td id=\"c-6-2\" onclick=\"sendMove(1)\">%d</td>\n", game[5][1]) +
                String.format("   <td id=\"c-6-3\" onclick=\"sendMove(2)\">%d</td>\n", game[5][2]) +
                String.format("   <td id=\"c-6-4\" onclick=\"sendMove(3)\">%d</td>\n", game[5][3]) +
                String.format("   <td id=\"c-6-5\" onclick=\"sendMove(4)\">%d</td>\n", game[5][4]) +
                String.format("   <td id=\"c-6-6\" onclick=\"sendMove(5)\">%d</td>\n", game[5][5]) +
                String.format("   <td id=\"c-6-7\" onclick=\"sendMove(6)\">%d</td>\n", game[5][6]) +
                "</tr>\n" */
    }




}


