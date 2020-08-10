# <4Gewinnt> (PiS, SoSe 2020) 

Autor: Ammar Ahmed, 5226465

Ich habe die Zulassung für PiS im <WiSe/SoSe 2019> bei Herrn Franzen erhalten.

<C:\Users\ammar\IdeaProjects\Gradlee>

## Einleitung
Das Spiel 4Gewinnt lässt sich einfach spielen. Das Spiel braucht etwas nachdenken aber macht trotzdem spaß.

### Spielregeln

Das Spiel wird auf einem senkrecht stehenden hohlen Spielbrett gespielt, in das die Spieler abwechselnd ihre Spielsteine fallen lassen. Das Spielbrett besteht aus sieben Spalten (senkrecht) und sechs Reihen (waagerecht). Jeder Spieler besitzt 21 gleichfarbige Spielsteine. Wenn ein Spieler einen Spielstein in eine Spalte fallen lässt, besetzt dieser den untersten freien Platz der Spalte. Gewinner ist der Spieler, der es als erster schafft, vier oder mehr seiner Spielsteine waagerecht, senkrecht oder diagonal in eine Linie zu bringen. Das Spiel endet unentschieden, wenn das Spielbrett komplett gefüllt ist, ohne dass ein Spieler eine Viererlinie gebildet hat.

### Bedienungsanleitung

![Screenshot](GUI.png)
#####New Game   :    wenn man drauf klickt wird ein leeres Board geladen, um ein neues Spiel zu beginnen
#####Undo       :    wenn man drauf klickt werden die letzten zwei züge zurückgenommen. Man kann immer weiter drauf klicken bis das Game-Board leer ist
#####Play for me:    wenn man drauf klickt spielt die Engine für den User. dies kann man wieder mit undo zurücknehmen sowie die nomralen züge
#####Computer start: wenn man drauf klickt dann startet die Engine mit dem ersten Zug. Danach ist der User dran
#####szenario 1,2,3,4,5: sind für die Test. Das Board und die Spielstellung werden auf der Console nach jedem zug bzw aufruf des Algorithms ausgegeben.


### Dateiübersicht

    C:\Users\ammar\IdeaProjects\Gradlee\build.gradle
    C:\Users\ammar\IdeaProjects\Gradlee\README.md
    C:\Users\ammar\IdeaProjects\Gradlee\GUI.png    
    C:\Users\ammar\IdeaProjects\Gradlee\src\main\kotlin\AI.kt
    C:\Users\ammar\IdeaProjects\Gradlee\src\main\kotlin\App.kt
    C:\Users\ammar\IdeaProjects\Gradlee\src\main\kotlin\Check.kt
    C:\Users\ammar\IdeaProjects\Gradlee\src\main\kotlin\CheckWinner.kt
    C:\Users\ammar\IdeaProjects\Gradlee\src\main\kotlin\Controller.kt
    C:\Users\ammar\IdeaProjects\Gradlee\src\main\kotlin\Engine.kt
    C:\Users\ammar\IdeaProjects\Gradlee\src\main\resources\public\index.html
    C:\Users\ammar\IdeaProjects\Gradlee\src\main\resources\public\script.js
![Screenshot](cloc.png)    

## Spiel-Engine (ENG)

Feature    | AB  | I | -  | -   | -| Summe
-----------|-----|-----|----|-----|-----|----
Umsetzung  | 120 | 33.3 | -  |   - | -   |
Gewichtung | 0.4 | 0.3 | -  | -   |  -  | 
Ergebnis   |  48 |  10 |  - |   - |   - | **58%**

Die Spiel-Engine befindet sich in C:\Users\ammar\IdeaProjects\Gradlee\src\main\kotlin\Engine.kt

In der Engine habe ich den Algorithmus Alpha-beta verwendet. Durch die funktion bestmove werden die Zustände nach gültigkeit geprüft und an die Algorithmus-Funktion geschickt. Der Algorithmus macht die Berechnung durch die Suchtiefe für sich und für den gegner. Am Ende liefert die Funktion "minimax"  einen Wert für jeden Zustand zurück. Diese wird verglichen und das beste score wird ausgewählt. Danach entscheidet der computer sich für die spielstellung mit dem Bestscore.

## Tests (TST)

Szenario |  1  |  2  |  3  |  4  |  5  | Summe
---------|-----|-----|-----|-----|-----|-------
ok       |  X  |  X  |  X  |  X  |  X | 1.0

Die Tests befinden sich in der Klasse APP unter C:\Users\ammar\IdeaProjects\Gradlee\src\main\kotlin\App.kt
Die Tests werden wie folgt ausgeführt:
Die Tests sind als Requests und funktionieren mit den Buttons von der GUI. Für jeden Test-Szenario gibt es ein Button. wird ein Button gedrückt, so wird auf der Console das Game-Board sowie die Spielstellung nach jedem zug ausgegben. Das läuft je nach Szenario bis der Computer Gewinnt oder eine Drohung vereitelt.
Die Engine ist in der Gui "Gelb" und auf der Console "2". Der User is n der Gui "Rot" und auf der Console "1"

Szenario 1 : hier hat die Engine schon vor dem Spielen 3 in der Reihe und gewinnt im nächsten zug 
Szenario 2 : hier hat die Engine schon vor dem Spielen 2 in der Reihe und gewinnt im übernächsten zug.
Szenario 3 : hier hat die Engine schon vor dem Spielen 1 in der Reihe und gewinnt im überübernächsten zug.
Szenario 4 : hier hat der User  schon 3 in der ersten spalte und kann im nächsten zug gewinnen. Die Engine erkennt diese und vereitelt die Drohung.
Szenario 5 : hier hat der User  schon 2 in der Reihe und kann im übernächsten zug gewinnen. Die Engine erkennt diese und vereitelt die Drohung.


Die Testausführung protokolliert sich über die Konsole wie folgt:

    Szenario 1. Player = 1 , AI = 2 
    0000000
    0000000
    2000000
    1010000
    1010000
    1222000
    Computer berechnet...
    Spielstellung nach der Rechnung : java.awt.Point[x=5,y=4]
    0000000
    0000000
    2000000
    1010000
    1010000
    1222200
    Computer has Won.. 
    
    
    Szenario 2. Player = 1 , AI = 2 
    0000000
    0000000
    2000000
    1000000
    1010000
    1220000
    Computer berechnet...
    Spielstellung nach der Rechnung : java.awt.Point[x=5,y=3]
    0000000
    0000000
    2000000
    1000000
    1010000
    1222000
    user plays 
    0000000
    0000000
    2000000
    1010000
    1010000
    1222000
    Computer berechnet...
    Spielstellung nach der Rechnung : java.awt.Point[x=5,y=4]
    0000000
    0000000
    2000000
    1010000
    1010000
    1222200
    Computer has Won.. 
    
    Szenario 3. Player = 1 , AI = 2 
    0000000
    0000000
    0000000
    2000000
    1100000
    1200000
    Computer berechnet...
    Spielstellung nach der Rechnung : java.awt.Point[x=5,y=2]
    0000000
    0000000
    0000000
    2000000
    1100000
    1220000
    user plays
    0000000
    0000000
    0000000
    2100000
    1100000
    1220000
    Computer berechnet...
    Spielstellung nach der Rechnung : java.awt.Point[x=5,y=3]
    0000000
    0000000
    0000000
    2100000
    1100000
    1222000
    user plays 
    0000000
    0000000
    0000000
    2100000
    1110000
    1222000
    Computer berechnet...
    Spielstellung nach der Rechnung : java.awt.Point[x=5,y=4]
    0000000
    0000000
    0000000
    2100000
    1110000
    1222200
    Computer has Won.. 
    
    Szenario 4. Player = 1 , AI = 2 
    0000000
    0000000
    0000000
    1000000
    1000000
    1220000
    Computer berechnet...
    Spielstellung nach der Rechnung : java.awt.Point[x=2,y=0]
    0000000
    0000000
    2000000
    1000000
    1000000
    1220000
    Computer has Won.. 
    
    Szenario 5. Player = 1 , AI = 2 
    0000000
    0000000
    0000000
    0000000
    0000000
    2001100
    Computer berechnet...
    Spielstellung nach der Rechnung : java.awt.Point[x=5,y=2]
    0000000
    0000000
    0000000
    0000000
    0000000
    2021100
    Computer has Won.. 
    


## Quellennachweis

* https://de.wikipedia.org/wiki/Vier_gewinnt#:~:text=Jeder%20Spieler%20besitzt%2021%20gleichfarbige,in%20eine%20Linie%20zu%20bringen.
* https://de.wikipedia.org/wiki/Alpha-Beta-Suche#Informelle_Beschreibung
* https://en.wikipedia.org/wiki/Minimax
*
