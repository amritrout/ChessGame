package server;
import game.*;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collection;

public class GameWebSocketServer extends WebSocketServer {
    private GameController gameController;

    public GameWebSocketServer(InetSocketAddress address) {
        super(address);
        this.gameController = new GameController();
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        System.out.println("Connected: " + conn.getRemoteSocketAddress());
        conn.send("Welcome to the server!");
        sendBoardStateToAll();
    }

    //resets the board and variables to 0;
    private void resetBoard() {
        GameBoard board = gameController.getGameBoard();
        board.clearBoard();
        board.moves = new ArrayList<>();

        hero1CountA=0;
        hero2CountA=0;
        hero3CountA=0;
        pawnA=0;
        hero1CountB=0;
        hero2CountB=0;
        hero3CountB=0;
        pawnB=0;

    }

    int hero1CountA=0;
    int hero2CountA=0;
    int hero3CountA=0;
    int pawnA=0;
    int hero1CountB=0;
    int hero2CountB=0;
    int hero3CountB=0;
    int pawnB=0;
    @Override
    public void onMessage(WebSocket conn, String message) {
        System.out.println("Received message: " + message);

        //if message InitHero it means we are tying to place a character
        if (message.startsWith("InitHero:")) {
            String[] parts = message.substring(9).split(":");
            String heroName = parts[0];
            int x = Integer.parseInt(parts[2]);
            int y = Integer.parseInt(parts[1]);
            String player="";
            if(y==0){
                player="A-";
            }else if(y==4){
                player="B-";
            }else{
                return;
            }

            if(heroName.equals("Hero1")){
                gameController.getGameBoard().placeCharacter(new Hero1(player+"H1"+((player.equals("A-"))?hero1CountA++:hero1CountB++), x, y), x, y);
            }else if(heroName.equals("Hero2")){
                gameController.getGameBoard().placeCharacter(new Hero2(player+"H2"+((player.equals("A-"))?hero2CountA++:hero2CountB++), x, y), x, y);
            }else if(heroName.equals("Hero3")) {
                gameController.getGameBoard().placeCharacter(new Hero3(player + "H3" + ((player.equals("A-")) ? hero3CountA++ : hero3CountB++), x, y), x, y);
            }else{
                gameController.getGameBoard().placeCharacter(new Pawn(player+"P"+((player.equals("A-"))?pawnA++:pawnB++), x, y), x, y);
            }
        } else if(message.equals("reset")){
            resetBoard();
        }
        else {
            gameController.processMove(message);
        }

        sendBoardStateToAll();
    }

    //send the state of the board to fronten/client
    private void sendBoardStateToAll() {
        String boardState = gameController.getGameBoard().getBoardState();
        String moveHistory = String.join("<br>", gameController.getGameBoard().getMove());
        String message = boardState + "<br><br>" + moveHistory;

        Collection<WebSocket> clients = this.getConnections();
        for (WebSocket client : clients) {
            client.send(message);
        }
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        System.out.println("Disconnected: " + conn.getRemoteSocketAddress() + " with reason: " + reason);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        System.out.println("Error: " + ex.getMessage());
        ex.printStackTrace();
    }
    @Override
    public void onStart() {
        System.out.println("WebSocket server started successfully");
    }

    public static void main(String[] args) {
        int port = 8080;
        GameWebSocketServer server = new GameWebSocketServer(new InetSocketAddress(port));
        server.start();

        System.out.println("WebSocket server started on port " + port);

        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            System.out.println("Server interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        } finally {
            try {
                server.stop(1000);
                System.out.println("WebSocket server stopped");
            } catch (InterruptedException e) {
                System.out.println("Error stopping server: " + e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }
}
