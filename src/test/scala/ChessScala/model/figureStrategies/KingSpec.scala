package ChessScala.model.figureStrategies
import org.scalatest.matchers.should.Matchers
import ChessScala.model.board.{Board, BoardBuilder, Coordinate}
import ChessScala.model.figureStrategies.strategyImplementations.{BlackKing, Pawn}
import org.scalatest.wordspec.AnyWordSpec

class KingSpec extends AnyWordSpec with Matchers {

  val position: Coordinate = new Coordinate(0, 0)
  val boardBuilder: BoardBuilder = new BoardBuilder(8)
  val board: Board = boardBuilder.createEmptyBoard()

  "A king " should {

    "return all possible moves when the board is empty" in {

      val moves: Vector[Coordinate] = BlackKing.getMoves(position, board)

      moves should contain allOf(Coordinate(1, 1), Coordinate(0, 1), Coordinate(1, 0))

      moves should contain noneOf(Coordinate(0, 0), Coordinate(2, 0), Coordinate(0, 6), Coordinate(4, 5))
    }

    "return all possible moves with the board containing a friend" in {

      val friend: Pawn = new Pawn(Black)
      val boardPawn: Board = board.insert(Coordinate(1, 1), friend)

      val moves: Vector[Coordinate] = BlackKing.getMoves(position, boardPawn)

      moves should contain allOf(Coordinate(1, 0), Coordinate(0, 1))

      moves should contain noneOf(Coordinate(1, 1), Coordinate(3, 3), Coordinate(4, 4))

    }

    "return all possible attacks with the board containing an enemy" in {

      val enemy: Pawn = new Pawn(White)
      val boardEnemy: Board = board.insert(Coordinate(1, 1), enemy)

      val attacks: Vector[Coordinate] = BlackKing.getMoves(position, boardEnemy)

      attacks should contain(Coordinate(1, 1))
      
    }

  }

}
