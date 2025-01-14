package ChessScala.model.gameState
import ChessScala.model.gameState
import ChessScala.model.gameState.stateImplementation.{GameState, MenuState}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class MenuStateSpec extends AnyWordSpec with Matchers {

  val menuState : MenuState = gameState.newState().asInstanceOf[MenuState]

  "A MenuState " should {

    "return a new game " in {

      val result = menuState.handle("1")

      result._1.isInstanceOf[GameState] should be(true)
      result._2 should be("")

    }

    "return an error " in {

      val result = menuState.handle("queen")

      result._1.isInstanceOf[GameState] should be(false)
      result._2 should be("Wrong input. Please try again.")

    }

    "load a game" in {
      menuState.handle("load")._1.isInstanceOf[GameState] should be (true)
    }

  }

}
