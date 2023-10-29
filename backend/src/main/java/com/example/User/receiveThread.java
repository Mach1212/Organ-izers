package com.example.User;

import com.google.gson.Gson;
import com.surrealdb.connection.SurrealWebSocketConnection;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;

public class receiveThread {
    public receiveThread(SurrealWebSocketConnection conn){
        try {
            Socket socket = conn.getSocket();

            DataInputStream dm = new DataInputStream(conn.getSocket().getInputStream());
            Gson gson = new Gson();
            Hospital hospital = gson.fromJson(dm.readUTF(), Hospital.class);




        } catch (Exception e){
            System.err.println(Arrays.toString(e.getStackTrace()));
        }
    }
}
