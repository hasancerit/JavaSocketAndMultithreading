/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multithreadchat;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.*;

class clientThread implements Runnable{
    DataInputStream input;
    PrintStream output;
    
    Socket ist;
    clientThread t[];
    
    
    public clientThread(Socket ist,clientThread t[]) {
        this.ist = ist;
        this.t = t;
    }
    
    @Override
    public void run() {
        String isim=null;
        String line=null;
        
        try {
            input = new DataInputStream(ist.getInputStream());
            output = new PrintStream(ist.getOutputStream());        
            output.println("İsminiz:");
            isim = input.readLine();    
        for(int i = 0;i <= 9; i++ ){
            if(t[i]!=null && t[i] != this){
                t[i].output.println(ist.getLocalPort()+"İsimli kullanıcı katıldı.");
            }
        }
        
        while(true){
            line = input.readLine();
                if(line.startsWith("bye")) break;
            for(int i = 0;i<9 ; i++){
                if(t[i]!=null) t[i].output.println("<"+isim+">"+line);
            }
        }
        
        for(int y = 0;y<= 9; y++ ){
            if(t[y]!=null && t[y] != this){
                t[y].output.println(isim+"İsimli kullanıcı ayrıldı.");
            }
        }
        
        for(int i = 0;i<9;i++){
            if(this == t[i]) t[i]=null;
        }
        input.close();
        output.close();
        ist.close();
    }catch(IOException e){
        
    }
}
    
}
