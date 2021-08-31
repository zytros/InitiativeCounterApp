package com.inCounter.userEnd.core;

import com.inCounter.userEnd.Configuration;
import com.inCounter.userEnd.IntegerWrapper;
import com.inCounter.userEnd.Message;


public class Communicator extends Thread{

    private Configuration configuration;
    private final int id;

    public Communicator(Configuration configuration){
        this.configuration = configuration;

        IntegerWrapper integerWrapper = new IntegerWrapper();

        InitMessageSender initMessageSender = new InitMessageSender(configuration, integerWrapper);
        Thread t = new Thread(initMessageSender);
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.id = integerWrapper.getInteger();
    }



    public void sendMessage(String method, String value){
        MessageSender messageSender = new MessageSender(configuration, new Message(id, method, value));
        Thread t = new Thread(messageSender);
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
