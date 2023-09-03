class TicTacToeGame {
    private val board = Array(3) { CharArray(3) { ' ' } }
    public var currentPlayer = 'X'

    fun play(row: Int, col: Int): Boolean {
        if (row < 0 || row >= 3 || col < 0 || col >= 3 || board[row][col] != ' ') {
            return false
        }

        board[row][col] = currentPlayer
        currentPlayer = if (currentPlayer == 'X') 'O' else 'X'
        return true
    }

    fun isGameOver(): Boolean {
        return checkWin('X') || checkWin('O') || isBoardFull()
    }

    public fun checkWin(player: Char): Boolean {
        // Verifique linhas, colunas e diagonais
        for (i in 0 until 3) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                (board[0][i] == player && board[1][i] == player && board[2][i] == player)
            ) {
                return true
            }
        }
        return (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
                (board[0][2] == player && board[1][1] == player && board[2][0] == player)
    }

    private fun isBoardFull(): Boolean {
        for (row in board) {
            for (cell in row) {
                if (cell == ' ') {
                    return false
                }
            }
        }
        return true
    }

    fun printBoard() {
        for (row in board) {
            println(row.joinToString(" | "))
            println("---------")
        }
    }
}

fun main() {
    val game = TicTacToeGame()
    var gameOver = false

    while (!gameOver) {
        println("Jogador atual: ${game.currentPlayer}")
        game.printBoard()

        print("Informe a linha (0, 1 ou 2): ")
        val row = readLine()?.toIntOrNull() ?: continue

        print("Informe a coluna (0, 1 ou 2): ")
        val col = readLine()?.toIntOrNull() ?: continue

        if (game.play(row, col)) {
            gameOver = game.isGameOver()
        } else {
            println("Jogada inválida. Tente novamente.")
        }
    }

    game.printBoard()
    if (game.checkWin('X')) {
        println("Jogador X ganhou!")
    } else if (game.checkWin('O')) {
        println("Jogador O ganhou!")
    } else {
        println("Empate!")
    }
}