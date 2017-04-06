/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agunga.websocket;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author agunga
 */
@ServerEndpoint("/wsmessage")
public class WebSocketTrial {

    static Set<Session> chatUsers = Collections.synchronizedSet(new HashSet<>());

    @OnOpen
    public void openConnection(Session session) {
        chatUsers.add(session);
        System.out.println("openConnection Session ID: " + session.getId());
        try {
            session.getBasicRemote().sendText("Conection opened as: " + session.getId());
        } catch (IOException ex) {
            System.err.println("My Error : " + ex.getMessage() + " caused by : ");
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        System.out.println("ID: " + session.getId() + " onMessage \" " + message + " \" ");
        String username = (String) session.getUserProperties().get("username");
        if (username == null || "".equals(username)) {
            session.getUserProperties().put("username", message);
            session.getBasicRemote().sendText("New login : " + message);
        } else {
            for (Session chatUser : chatUsers) {
                chatUser.getBasicRemote().sendText(username + " : " + message);
            }
        }
    }

    @OnError
    public void onError(Session session, Throwable t) {
        System.out.println(session.getId() + " clossed ");
    }

    @OnClose
    public void onClose(Session session) {
        chatUsers.remove(session);
        System.out.println(session.getId() + " clossed ");
    }
}
