package ChessScala.model.gameState.stateImplementation

import ChessScala.model.board.{Board, BoardBuilder}
import ChessScala.model.gameState.ProgrammState
import ChessScala.model.interpreter.Interpreter
import ChessScala.model.interpreter.interpreterImplementations.ConnectionInterpreter
import ChessScala.util.ConnectionHandler

class ConnectionState extends ProgrammState{

  override val interpreter: Interpreter = new ConnectionInterpreter()
  override val board: Board = (new BoardBuilder(8)).createChessBoard()

  override def handle(input: String): (ProgrammState, String) = {
    val interpreterResult = interpreter.processInputLine(input)
    if (!interpreterResult._2){
      return (this, interpreterResult._1)
    }
    if (input == "new") {
      val id: String = ConnectionHandler.createNewGameID()
      ConnectionHandler.startPolling(id)
      println(id)
      return (new MultiplayerState(ConnectionHandler.ip, ConnectionHandler.port, id, board), interpreterResult._1)
    }
    val newState = new MultiplayerState(ConnectionHandler.ip, ConnectionHandler.port, input, board)
    (newState.doInput(""), interpreterResult._1)
  }

}
