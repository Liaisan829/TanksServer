package ru.kpfu.itis.akhmetova.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class ClientThread implements Runnable {

    private final BufferedReader input;

    private final BufferedWriter output;

    private final ChatClient client;

    public ClientThread(BufferedReader input, BufferedWriter output, ChatClient client) {
        this.input = input;
        this.output = output;
        this.client = client;
    }

    public BufferedReader getInput() {
        return input;
    }

    public BufferedWriter getOutput() {
        return output;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String message = input.readLine();
                client.getApplication().appendMessageToChat(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}