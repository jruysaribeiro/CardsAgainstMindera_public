package Commands;

import java.awt.desktop.QuitHandler;

import static java.time.chrono.JapaneseEra.values;

public enum Command {

    //Before game starts:

    START("/start", new StartHandler()), //(number of players)
    LIST_PLAYERS("/list_players", new ListPlayersHandler()),
    CHANGE_NAME("/change_name", new ChangeNameHandler()),
    CHAT_GENERAL("/chat_general", new ChatGeneralHandler()),
    WHISPER("/whisper", new WhisperHandler()),
    NOT_FOUND("Command not found", new CommandNotFoundHandler()),//(lists of commands)

    //After game starts:

    PLAY_CARD("/play_card", new PlayCardHandler()),//(Number of cards)
    NOT_FOUND("Command not found", new CommandNotFoundHandler()),//(lists of commands)

    //After game ends:
    NEW_GAME("/new_game", new NewGameHandler()),//(same clients)
    QUIT("/quit", new QuitHandler()),


    private String description;
    private CommandHandler handler;

    public void Command(String description, CommandHandler handler) {
        this.description = description;
        this.handler = handler;
    }

    public static Command getCommandFromDescription(String description) {
        for (Command command : values()) {
            if (description.equals(command.description)) {
                return command;
            }
        }
        return NOT_FOUND;
    }

    public CommandHandler getHandler() {
        return handler;
    }

    public String getDescription() {
        return description;
    }

    public void main() {
    }
    }