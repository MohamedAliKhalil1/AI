/*<script type="text/javascript">*/
    /* Hier werden HTTP-Request (GET-Methode) versendet
       und als Antwort stets der innere Teil der
       Tabelle mit dem 4Gewinn-Spielbrett erwartet
    */
    var http = new XMLHttpRequest();

    function sendRequestGET(path = '', query = '') {

        http.open('GET', path + '?' + query);
        http.send();
    }

    http.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            if(this.responseText === "winner1"){
                alert("player 1 has won ")
            }
            else if(this.responseText === "winner2"){
                alert("Computer has won ")
            }
            else if(this.responseText === "draw"){
                alert("Draw")
            }
            else if(this.responseText === "nowinner"){
            }
            else if(this.responseText === "computerbegin"){
                document.getElementById('gameboard').innerHTML = this.responseText;
            }
            else if (this.responseText ==="playforme"){
                document.getElementById('gameboard').innerHTML = this.responseText;
            }
            else if (this.responseText ==="undo" || this.responseText ==="szenario4" || this.responseText ==="szenario1" || this.responseText ==="szenario2" || this.responseText ==="szenario3"){
                document.getElementById('gameboard').innerHTML = this.responseText;
            }
            else{
                document.getElementById('gameboard').innerHTML = this.responseText;
                sendRequestGET("checkwinner")
            }



        }
    }

    /* Diese Funktionen sind Beispiele, wie der
       Aufruf an `sendRequestGET` delegiert wird.
    */
    function sendMove(field) {
        sendRequestGET('move', 'pos=' + field);
    }

    function loadInnerTable() {
        sendRequestGET('rows');
    }
/*</script>*/
