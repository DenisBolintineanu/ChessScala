package ChessScala.model.moveChain
import ChessScala.model.board.Board
import ChessScala.model.figureStrategies.{Figure, White, Team}
import ChessScala.model.gameState.{GameState, ProgrammState}

import scala.util.{Failure, Success, Try}

class MoveHandler(move: Move) extends GameChain {
  override val next: GameChain = this

  override def handle(state: GameState): Option[ProgrammState] =
    val result = Try[Board]{mover(move, state.board).get}
    result match
      case Failure(_) => None
      case Success(_) => next.handle(new GameState(state.team, result.get))


  def mover(move: Move, board: Board): Option[Board] =
    val success = Try[Figure] {board.get(move.start).get}
    success match
      case Failure(_) => None
      case Success(value) => {
        val vector = value.getMoves(move.start, board)
        if (!vector.contains(move.target)) return None
        Some(board.insert(move.target, value).delete(move.start))
      }

}