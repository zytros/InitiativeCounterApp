package com.inCounter.userEnd.core;

import com.inCounter.userEnd.Configuration;
import com.inCounter.userEnd.Message;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;

public class MessageSender extends Thread{
    Configuration configuration;
    Message message;

    public MessageSender(Configuration configuration, Message  message){
        this.configuration = configuration;
        this.message = message;
    }

    /**
     * Sending message, ignoring response
     */

    @Override
    public void run() {
        System.out.println("sending message");
        String call = configuration.getUserEnd() + " " + message;
        String response = null;

        try {
            Socket client = new Socket(configuration.getHost(), configuration.getPort());
            DataOutputStream outputStream = new DataOutputStream(client.getOutputStream());
            outputStream.writeUTF(call);

            DataInputStream inputStream = new DataInputStream(client.getInputStream());
            response = inputStream.readUTF();


            client.close();

        } catch (ConnectException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("sent message " + call);
    }
}
