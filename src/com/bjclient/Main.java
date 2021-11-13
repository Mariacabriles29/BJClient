package com.bjclient;

import com.conection.WSClient;
import com.gui.Screen;

public class Main {

    public static void main(String[] args) {

        //Screen screen = new Screen();
        //screen.display();

        WSClient client = new WSClient(null);
        client.startClient();
    }
}
