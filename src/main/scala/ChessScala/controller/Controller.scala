package ChessScala.controller
import ChessScala.model.gameState.{MenuState, ProgrammState}
import ChessScala.util.{Observable, UndoManager}
import ChessScala.model.interpreter.{Interpreter, MenuInterpreter}

class Controller() extends Observable {
  
  var output: String = "test"
  var state: ProgrammState = new MenuState
  private val undoManager = new UndoManager

  def computeInput(input: String): Unit =
    input match
      case "undo" => undo()
      case "redo" => redo()
      case _ => doStep(input)

  def printDescriptor(): Unit =
    output = state.interpreter.descriptor
    notifyObservers()

  def doStep(input: String): Unit =
    undoManager.doStep(new SetCommand(input, this))
    notifyObservers()

  def undo(): Unit =
    undoManager.undoStep

  def redo(): Unit =
    undoManager.redoStep
    notifyObservers()
}