package ChessScala.model.moveChain

import ChessScala.model.figureStrategies.Team
import ChessScala.model.gameState.{GameState, ProgrammState}

import scala.math.Ordering
import scala.util.Try

trait GameChain {

  val next: GameChain

  def handle(state: GameState): Option[ProgrammState]
  
}
