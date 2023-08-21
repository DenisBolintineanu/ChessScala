package ChessScala.model.gameState.stateImplementation

import ChessScala.model.board.Board
import ChessScala.model.gameState.ProgrammState
import ChessScala.model.interpreter.Interpreter
import ChessScala.model.interpreter.interpreterImplementations.GameInterpreter
import ChessScala.util.ConnectionHandler
import ChessScala.model.fileIO.fileIOJson.FileIO

class MultiplayerState(ip: String, port: String, id: String, val board: Board) extends ProgrammState {
  override val interpreter: Interpreter = new GameInterpreter()
  val JsonBoardBuilder = new FileIO()

  override def handle(input: String): (ProgrammState, String) = {
    val interpreterResult = interpreter.processInputLine(input)
    if (!interpreterResult._2){
      return (this, interpreterResult._1)
    }
    val result = ConnectionHandler.sendMoveRequest(id, input)
    val newBoard = JsonBoardBuilder.loadBoard(result)
    val stateString = JsonBoardBuilder.getState(result)
    if (stateString == "\"MateState\"") {
      ConnectionHandler.continuePolling = false
      return (new MateState("Checkmate", newBoard),"")
    }
    (new MultiplayerState(ip, port, id, newBoard), interpreterResult._1)
  }
}
