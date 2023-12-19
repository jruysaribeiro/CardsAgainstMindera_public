package Commands.Handlers;

import Client.ClientConnectionHandler;
import Commands.CommandHandler;
import Messages.Messages;
import Server.Server;

public class QuitHandler implements CommandHandler {

    @Override
    public void execute(Server server, ClientConnectionHandler clientConnectionHandler) {
        server.removeClient(clientConnectionHandler);
        Server.broadcast(clientConnectionHandler.getName(),
                clientConnectionHandler.getName() + Messages.CLIENT_DISCONNECTED);
    }
}