/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cwfserver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author BeerSmokinGenius
 */
public class CWFServer {
    
    public static int freeplayers;
    public static void main(String[] args) {
        freeplayers = 0;//freeplayers are players that connected to the server, but are not yet in a game
    
        try { 
            ServerSocket serverSocket = new ServerSocket(2149);
            
            while(true){
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out.println("connected");
                out.flush();
            }
        }
        catch(Exception E){
            System.out.println("There was an error connecting to a client");
        }
    }
    
}