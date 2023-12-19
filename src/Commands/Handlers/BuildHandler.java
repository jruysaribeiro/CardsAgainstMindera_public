package Commands.Handlers;

import Client.ClientConnectionHandler;
import Commands.CommandHandler;
import Game.Game;
import Messages.Messages;
import Server.Server;

import java.io.IOException;

public class BuildHandler implements CommandHandler {

    @Override
    public void execute(Server server, ClientConnectionHandler clientConnectionHandler) throws IOException {
        String name = clientConnectionHandler.askNameOfGame();
        int numOfPlayers = clientConnectionHandler.askNumberOfPlayers();
        Game game = new Game(clientConnectionHandler, numOfPlayers, name);
        clientConnectionHandler.setOwnedGame(game);
        clientConnectionHandler.writeMessage(Messages.GAME_BUILT + numOfPlayers + Messages.PLAYERS_CALLED + name + ".");
    }
}