package ChessScala.util
import sttp.client3._

object ConnectionHandler {
  val backend: SttpBackend[Identity, Any] = HttpURLConnectionBackend()
  val ip: String = "localhost"
  val port: String = "9000"
  val pathForNewGame: String = "/clientRequest/createNewGameID"
  val pathForMoveRequest: String = "/clientRequest/move"

  def createNewGameID(): String = {
    val requestString = "http://" + ip + ":" + port + pathForNewGame
    val request = basicRequest.get(uri"$requestString")
    request.send(backend).body.getOrElse("")
  }

  def sendMoveRequest(id: String, move: String) : String = {
    val requestString = "http://" + ip + ":" + port + pathForMoveRequest
    val request = basicRequest.post(uri"$requestString").body(Map("id" -> id, "move" -> move))
    request.send(backend).body.getOrElse("")
  }
}
