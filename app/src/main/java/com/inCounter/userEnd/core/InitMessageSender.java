package com.inCounter.userEnd.core;

import com.inCounter.userEnd.Configuration;
import com.inCounter.userEnd.IntegerWrapper;
import com.inCounter.userEnd.Message;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;

public class InitMessageSender extends Thread{
    Configuration configuration;
    IntegerWrapper integerWrapper;

    public InitMessageSender(Configuration configuration, IntegerWrapper integerWrapper){
        this.configuration = configuration;
        this.integerWrapper = integerWrapper;
    }

    @Override
    public void run() {
        Message message = new Message(0, configuration.getGetId(), "0");
        String call = configuration.getUserEnd() + " " + message;
        String response = null;

        try {
            Socket client = new Socket(configuration.getHost(), configuration.getPort());
            DataOutputStream outputStream = new DataOutputStream(client.getOutputStream());
            outputStream.writeUTF(call);

            DataInputStream inputStream = new DataInputStream(client.getInputStream());
            response = inputStream.readUTF();

            integerWrapper.setInteger(Integer.parseInt(response));

            client.close();

        } catch (ConnectException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
