import io.javalin.Javalin
import io.javalin.http.Context
import java.awt.Point

class App {

    init {

        val app = Javalin.create { config ->
            config.addStaticFiles("/public")
        }.start(7000)
        var game= Control()
        var row = 0
        var col = 0
        app.get("/move") { ctx: Context ->
            if (!game.checker.checkwinnnerboolean(game.g,1,game.lastplayed.x,game.lastplayed.y) &&
                !game.checker.checkwinnnerboolean(game.g,2,game.lastplayed.x,game.lastplayed.y) && !game.checker.checkdraw(game.g)){
                game.iswinner = false
            }
            if (!game.iswinner){
                val input = ctx.queryParam("pos")!!.toInt()
                col = input
                row = game.getnextvalidrow(game.g, input)
                if(row != -1 ){
                    game.g[row][col] = 1
                    game.lastplayed.x = row
                    game.lastplayed.y = col
                    game.undoPointer ++
                    var point : Point = Point()
                    point.x = game.lastplayed.x
                    point.y = game.lastplayed.y
                    game.playedList[game.undoPointer] = point
                    game.lastplayedplayer = game.lastplayed

                    if (game.checker.checkwinnnerboolean(game.g,1,row,col)){
                        game.iswinner = true
                    }
                    else {
                        game.lastplayed = game.engine.bestMove(game.g,row,col,2)
                        game.undoPointer ++
                        point  = Point()
                        point.x = game.lastplayed.x
                        point.y = game.lastplayed.y
                        game.playedList[game.undoPointer] = point
                        /*for(i in 0..5)
                            print(game.playedList[i])*/
                        if (game.checker.checkwinnnerboolean(game.g,2,game.lastplayed.x,game.lastplayed.y)){
                            game.iswinner = true
                        }
                    }
                }

                ctx.result(game.toString(game.g))
            }
            else {
                ctx.result(game.toString(game.g))
            }
            game.printboard()
        }

        app.get("/newgame") { ctx: Context ->
            game  = Control()
            ctx.result(game.toString(game.g))
        }

        app.get("/checkwinner") { ctx: Context ->
            if ( game.checker.checkwinnnerboolean(game.g,1,game.lastplayed.x,game.lastplayed.y)){
                ctx.result("winner1")
            }else if ( game.checker.checkwinnnerboolean(game.g,2,game.lastplayed.x,game.lastplayed.y)){
                ctx.result("winner2")
            }else if (game.checker.checkdraw(game.g)){
                ctx.result("draw")
            }
            else{
                ctx.result("nowinner")
            }
        }
        app.get("/computerbegin"){ctx: Context ->
            game = Control()
            game.lastplayed = game.engine.bestMove(game.g, 0,0,2)
            game.undoPointer ++
            var point  = Point()
            point.x = game.lastplayed.x
            point.y = game.lastplayed.y
            game.playedList[game.undoPointer] = point
            ctx.result(game.toString(game.g))
        }
        app.get("/playforme"){ctx: Context ->
            game.lastplayed = game.engine.bestMove(game.g, game.lastplayedplayer.x,game.lastplayedplayer.y,1)
            game.lastplayedplayer = game.lastplayed
            game.undoPointer ++
            var point  = Point()
            point.x = game.lastplayed.x
            point.y = game.lastplayed.y
            game.playedList[game.undoPointer] = point
            game.lastplayed = game.engine.bestMove(game.g, game.lastplayed.x,game.lastplayed.y,2)
            game.undoPointer ++
            point  = Point()
            point.x = game.lastplayed.x
            point.y = game.lastplayed.y
            game.playedList[game.undoPointer] = point
            ctx.result(game.toString(game.g))
        }
        app.get("/undo"){ctx: Context ->
            game.undo()
            game.undo()
            ctx.result(game.toString(game.g))

        }
        app.get("/szenario1"){ctx: Context ->
            game = Control()
            game.g[5][0] = 1
            game.g[4][0] = 1
            game.g[3][0] = 1
            game.g[4][2] = 1
            game.g[3][2] = 1
            game.g[5][1] = 2
            game.g[5][2] = 2
            game.g[2][0] = 2
            game.g[5][3] = 2
            println("Szenario 1. Player = 1 , AI = 2 ")
            game.printboard()
            println("Computer berechnet...")
            print("Spielstellung nach der Rechnung : ")
            println(game.engine.bestMove(game.g,3,2,2 ))
            game.printboard()
            println("Computer has Won.. ")
            println()
            ctx.result(game.toString(game.g))
        }
        app.get("/szenario2"){ctx: Context ->
            game = Control()
            game.g[5][0] = 1
            game.g[4][0] = 1
            game.g[3][0] = 1
            game.g[4][2] = 1
            game.g[5][1] = 2
            game.g[5][2] = 2
            game.g[2][0] = 2
            println("Szenario 2. Player = 1 , AI = 2 ")
            game.printboard()
            println("Computer berechnet...")
            print("Spielstellung nach der Rechnung : ")
            println(game.engine.bestMove(game.g,4,2,2 ))
            game.printboard()
            println("user plays ")
            game.g[3][2] = 1
            game.printboard()
            println("Computer berechnet...")
            print("Spielstellung nach der Rechnung : ")
            println(game.engine.bestMove(game.g,3,2,2 ))
            game.printboard()
            println("Computer has Won.. ")
            println()
            ctx.result(game.toString(game.g))
        }
        app.get("/szenario3"){ctx: Context ->
            game = Control()
            game.g[5][0] = 1
            game.g[4][0] = 1
            game.g[4][1] = 1
            game.g[5][1] = 2
            game.g[3][0] = 2
            println("Szenario 3. Player = 1 , AI = 2 ")
            game.printboard()
            println("Computer berechnet...")
            print("Spielstellung nach der Rechnung : ")
            println(game.engine.bestMove(game.g,4,1,2 ))
            game.printboard()
            println("user plays")
            game.g[3][1] = 1
            game.printboard()
            println("Computer berechnet...")
            print("Spielstellung nach der Rechnung : ")
            println(game.engine.bestMove(game.g,3,1,2 ))
            game.printboard()
            println("user plays ")
            game.g[4][2] = 1
            game.printboard()
            println("Computer berechnet...")
            print("Spielstellung nach der Rechnung : ")
            println(game.engine.bestMove(game.g,4,2,2 ))
            game.printboard()
            println("Computer has Won.. ")
            println()
            ctx.result(game.toString(game.g))
        }
        app.get("/szenario4"){ctx: Context ->
            game = Control()
            game.g[5][0] = 1
            game.g[4][0] = 1
            game.g[3][0] = 1
            game.g[5][1] = 2
            game.g[5][2] = 2
            game.g[5][0] = 1
            println("Szenario 4. Player = 1 , AI = 2 ")
            game.printboard()
            println("Computer berechnet...")
            print("Spielstellung nach der Rechnung : ")
            println(game.engine.bestMove(game.g,3,0,2 ))
            game.printboard()
            println("Computer has Won.. ")
            println()
            ctx.result(game.toString(game.g))
        }
        app.get("/szenario5"){ctx: Context ->
            game = Control()
            game.g[5][0] = 2
            game.g[5][3] = 1
            game.g[5][4] = 1
            println("Szenario 5. Player = 1 , AI = 2 ")
            game.printboard()
            println("Computer berechnet...")
            print("Spielstellung nach der Rechnung : ")
            println(game.engine.bestMove(game.g,5,4,2 ))
            game.printboard()
            println("Computer has Won.. ")
            println()
            ctx.result(game.toString(game.g))
        }
    }

}

fun main() {

    App()


}       // end main function
