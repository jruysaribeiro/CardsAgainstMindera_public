package Commands;

import Client.ClientConnectionHandler;
import Messages.Messages;
import Server.Server;

public class CommandNotFoundHandler implements CommandHandler {

    @Override
    public void execute(Server server, ClientConnectionHandler clientConnectionHandler) {
        clientConnectionHandler.send(Messages.NO_SUCH_COMMAND);
    }
}
