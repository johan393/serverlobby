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
    public static Socket[] sockets;
    public static PrintWriter[] outs;
    public static BufferedReader[] ins;
    public static String[] addresses;
    
    public static void main(String[] args) {
        freeplayers = 0;//freeplayers are players that connected to the server, but are not yet in a game
    
        try { 
            int constant = 2;
            ServerSocket serverSocket = new ServerSocket(2150);
            outs = new PrintWriter[constant];
            ins = new BufferedReader[constant];
            sockets = new Socket[constant];
            addresses = new String[constant]; 
            
            
            while(true){
                sockets[freeplayers] = serverSocket.accept();
                
                outs[freeplayers] = new PrintWriter(sockets[freeplayers].getOutputStream());
                ins[freeplayers] = new BufferedReader(new InputStreamReader(sockets[freeplayers].getInputStream()));
                

                outs[freeplayers].println("connected");
                System.out.println("connected");
                outs[freeplayers].flush();
                
                
                addresses[freeplayers] = sockets[freeplayers].getInetAddress().toString();
                
                System.out.println(addresses[freeplayers]);
                if(freeplayers == constant-1){
                    for(int i = 0;i<constant;i++){
                        if(i==0){
                            outs[i].println("H");
                        }
                        else{
                            outs[i].println("C");
                        }
                        outs[i].flush();
                        for(int j = 0; j<constant; j++){
                            outs[i].println(addresses[j]);//each person in a lobby of constant players will get all other addresses in the lobby
                            outs[i].flush(); 
                        }
                        outs[i].close();//once the address data is sent, there is nothing else we need to communicate
                        ins[i].close();
                        sockets[i].close();
                        
                    }
                }
                freeplayers = (freeplayers + 1) % constant;    
            }
        }
        catch(Exception E){
            System.out.println("There was an error connecting to a client");
            E.printStackTrace();
        }
    }
    
}