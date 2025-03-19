let N = 8;
let board = []
function initBoard() {
    console.log("sup")
    let color = 0
    let colorA = "#FFFFFF"
    let colorB = "#000000"
    for (let i = 0; i < N; i++) {
        board.push([])
        for (let j = 0; j < N; j++) {
            const newTile = document.createElement('div');
            newTile.classList.add('square')
            newTile.innerText = "";
            newTile.onclick = () => clickTile(i, j)
            newTile.id = `tile-${i}-${j}`;
            newTile.style.backgroundColor = color % 2 == 0 ? colorA : colorB
            document.getElementsByClassName("main-grid")[0].appendChild(newTile)
            color++
            if (color == 8) {
                const a = colorA
                colorA = colorB
                colorB = a
            }
            color = color % 8
            board[i].push(newTile)
        }
    }
}
function calculateAvailableMoves(piece, i, j){

}

function initPieces() {
    const piece = document.createElement("div")
    piece.classList.add('piece')
    piece.onclick(calculateAvailableMoves(piece))
    // adding pawns
    for (let i = 0; i < N; i++) {
        piece.innerHTML="P"
        board[1][i].appendChild(piece.cloneNode(true))
        board[6][i].appendChild(piece.cloneNode(true))
    }
    // rooks 
  
    piece.innerText='R'
  
    board[0][0].appendChild( piece.cloneNode(true))
    board[0][7].appendChild( piece.cloneNode(true))
    board[7][0].appendChild( piece.cloneNode(true))
    board[7][7].appendChild( piece.cloneNode(true))

    // knights
    piece.innerText="N"

    board[0][1].appendChild( piece.cloneNode(true))
    board[0][6].appendChild( piece.cloneNode(true))
    board[7][1].appendChild( piece.cloneNode(true))
    board[7][6].appendChild( piece.cloneNode(true))
    // bishops
    piece.innerText="B"

    board[0][2].appendChild( piece.cloneNode(true))
    board[0][5].appendChild( piece.cloneNode(true))
    board[7][2].appendChild( piece.cloneNode(true))
    board[7][5].appendChild( piece.cloneNode(true))
    // queens
    piece.innerText="Q"

    board[0][3].appendChild( piece.cloneNode(true))
    board[7][3].appendChild( piece.cloneNode(true))
// kings
piece.innerText="K"

board[0][4].appendChild( piece.cloneNode(true))
board[7][4].appendChild( piece.cloneNode(true))




}


window.onload = () => {
    initBoard()
    initPieces()
}
