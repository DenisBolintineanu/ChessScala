package ChessScala.model.gameState.stateImplementation

import ChessScala.model.gameState.ProgrammState
import ChessScala.model.figureStrategies.Team
import ChessScala.model.board.Board

class ServerState(team: Team, board: Board) extends GameState(team, board) {
    override def handle(input: String): (ProgrammState, String) = {
        val newBoard = super.handle(input)
        newBoard._1 match
            case _: MateState => return newBoard
            case gameState: GameState => return (new ServerState(gameState.team, gameState.board), newBoard._2)
            case _ => return (this, "Wrong move. Please try again.")
    }
}