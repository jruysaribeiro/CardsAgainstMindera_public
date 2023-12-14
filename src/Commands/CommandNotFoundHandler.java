package Commands;

import Messages.Messages;
import Server.Server;

public class CommandNotFoundHandler implements CommandHandler {

    @Override
    public void execute(Server server, Server.ClientConnectionHandler clientConnectionHandler) {
        clientConnectionHandler.send(Messages.NO_SUCH_COMMAND);
    }
}
