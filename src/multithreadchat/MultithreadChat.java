/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multithreadchat;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hasan
 */
public class MultithreadChat implements Runnable{
    static Socket istemci;
    static PrintStream output;
    static BufferedReader input;
    static Scanner scn;
    static String gidenMesaj;
    static boolean closed = false;
    
        @Override
        public void run() {
            String gelenMesaj=null;
            
            try {
                while((gelenMesaj = input.readLine()) != null ){
                    System.out.println(gelenMesaj);
                        if(gelenMesaj.indexOf("bye") != -1) 
                            break;
                }
                closed = true;
            } catch (IOException ex) {
                Logger.getLogger(MultithreadChat.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    public static void main(String[] args) {
        try {
            istemci = new Socket("localhost",1956);
            output = new PrintStream(istemci.getOutputStream());
            input = new BufferedReader(new InputStreamReader(istemci.getInputStream()));
            scn = new Scanner(System.in);
        } catch (IOException ex) {
            Logger.getLogger(MultithreadChat.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        new Thread(new MultithreadChat()).start();
        
        while(!closed){
            output.println(scn.nextLine());
        }
        
        try {
            input.close();  
            output.close();
            scn.close();
        } catch (IOException ex) {
            Logger.getLogger(MultithreadChat.class.getName()).log(Level.SEVERE, null, ex);
        }      
        
    }  

}
