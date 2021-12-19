package ru.kpfu.itis.akhmetova.server;

import ru.kpfu.itis.akhmetova.server.ChatServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.SocketException;

public class ClientThread implements Runnable {

    private final BufferedReader input;

    private final BufferedWriter output;

    private final ChatServer server;

    public ClientThread(BufferedReader input, BufferedWriter output, ChatServer server) {
        this.input = input;
        this.output = output;
        this.server = server;
    }

    public BufferedReader getInput() {
        return input;
    }

    public BufferedWriter getOutput() {
        return output;
    }

    public ChatServer getServer() {
        return server;
    }


    @Override
    public void run() {
        try {
            while (true) {
                String message = input.readLine();
                server.sendMessage(message, this);
            }
        } catch (SocketException socketException) {
            server.removeClient(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}