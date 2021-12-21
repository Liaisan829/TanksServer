package ru.kpfu.itis.akhmetova.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.SocketException;

public class GameServerThread implements Runnable {

    private final BufferedReader input;

    private final BufferedWriter output;

    private final GameServer gameServer;

    public GameServerThread(BufferedReader input, BufferedWriter output, GameServer server) {
        this.input = input;
        this.output = output;
        this.gameServer = server;
    }

    public BufferedReader getInput() {
        return input;
    }

    public BufferedWriter getOutput() {
        return output;
    }

    public GameServer getGameServer() {
        return gameServer;
    }


    @Override
    public void run() {
        try {
            while (true) {
                String message = input.readLine();
                gameServer.sendMessage(message, this);
            }
        } catch (SocketException socketException) {
            gameServer.removeClient(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}