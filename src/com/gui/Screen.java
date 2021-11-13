package com.gui;

import com.conection.WSClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Screen implements MouseListener {

    private JFrame frame;
    private JButton buttonConnect;
    private JButton buttonDisconnect;
    private JTextField inputServerURI;
    private JTextArea chatArea;
    private JLabel error;

    private WSClient client;

    public Screen() {

    }

    public void display(){
        frame = new JFrame("BlackJack");
        frame.setBounds(200,200,400,400);
        frame.setLayout(null);
        frame.setResizable(false);

        inputServerURI = new JTextField();
        inputServerURI.setBounds(100,210,200,30);
        inputServerURI.setVisible(true);

        error = new JLabel();
        error.setBounds(0,350,250, 30);
        error.setForeground(Color.RED);
        error.setVisible(true);

        buttonConnect = new JButton("Conectar");
        buttonConnect.setBounds(100,320,200, 30);
        buttonConnect.setName("conectar");
        buttonConnect.addMouseListener(this);
        buttonConnect.setVisible(true);

        buttonDisconnect = new JButton("Desconectar");
        buttonDisconnect.setBounds(190,330,200, 30);
        buttonDisconnect.setName("desconectar");
        buttonDisconnect.addMouseListener(this);
        buttonDisconnect.setVisible(false);

        chatArea = new JTextArea();
        chatArea.setBounds(50,20,300,300);
        chatArea.setVisible(false);

        frame.getContentPane().add(inputServerURI);
        frame.getContentPane().add(buttonConnect);
        frame.getContentPane().add(buttonDisconnect);
        frame.getContentPane().add(error);
        frame.getContentPane().add(chatArea);

        client = new WSClient(this);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (e.getComponent().getName()){
            case "conectar":
                String URI = inputServerURI.getText();
                if(URI.compareTo("") != 0){

                    WSClient.serverURI = URI;
                    client.startClient();
                    buttonConnect.setVisible(false);
                    inputServerURI.setVisible(false);
                    chatArea.setVisible(true);
                    buttonDisconnect.setVisible(true);
                }
                break;
            case "desconectar":
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
