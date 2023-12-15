package Server;

import Client.ClientConnectionHandler;
import Game.Game;
import Messages.Messages;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Server {
    public static final String ANSI_RED = "\u001B[31m";

    public static final String RED_BOLD = "\033[1;31m";

    public static final String YELLOW_UNDERLINED = "\033[4;33m";

    public static List<ClientConnectionHandler> clientHandlerList;





    void start(int port) throws IOException {
        clientHandlerList = new LinkedList<>();
        ServerSocket serverSocket = new ServerSocket(port);
        ExecutorService executorService = Executors.newCachedThreadPool();
        while (true) {
            System.out.println("Listening to connections");
            Socket socket = serverSocket.accept();
            ClientConnectionHandler clientHandler = new ClientConnectionHandler(socket);
            clientHandlerList.add(clientHandler);
            executorService.submit(clientHandler);
        }

    }

    public void broadcast(String name, String message){
        clientHandlerList.stream()
                .filter(handler -> handler.getName().equals(name))
                .forEach(handler -> handler.send(name + ": " + message));
    }


    public Optional<ClientConnectionHandler> getClientByName(String name) {
        return clientHandlerList.stream()
                .filter(clientConnectionHandler -> clientConnectionHandler.getName().equalsIgnoreCase(name))
                .findFirst();
    }
    public void removeClient(ClientConnectionHandler clientConnectionHandler){
        clientHandlerList.remove(clientConnectionHandler);
    }

    public static void sendClientsMessage(ClientConnectionHandler sender, String message) {
        clientHandlerList.stream().filter(clientHandler -> !clientHandler.equals(sender)).forEach(
                clientHandler -> {
                    try {
                        clientHandler.writeMessage(message);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }

    public static void sendWhisper(String sender, String receiver, String message){
        clientHandlerList.stream().filter(clientHandler -> clientHandler.getName()
                .equals(receiver)).forEach(clientHandler -> {
                    try {
                        clientHandler.writeMessage(sender + " " + Messages.WHISPER + ": " + message);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }

}