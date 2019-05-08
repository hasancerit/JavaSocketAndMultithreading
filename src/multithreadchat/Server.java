/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multithreadchat;

import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hasan
 */
public class Server {
    static Socket istSocket;
    static ServerSocket serSocket;
    static clientThread t[] = new clientThread[10];
    
    public static void main(String [] args){
        try {
            serSocket = new ServerSocket(1956);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        while(true){
            try {
                istSocket = serSocket.accept();
                System.out.println("Bağlantı Olustu");    
                for(int i = 0 ; i < 9 ; i++){
                    if(t[i] == null){
                        t[i] = new clientThread(istSocket,t);
                        new Thread(t[i]).start();
                        break;
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
    }
    
}
