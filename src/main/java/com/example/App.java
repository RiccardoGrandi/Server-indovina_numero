package com.example;

import java.net.ServerSocket;
import java.net.Socket;

public class App 
{
    public static void main( String[] args )
    {
        try {
            System.out.println("Server avviato");
            ServerSocket server = new ServerSocket(3000);
            
            while (true) {
                Socket s = server.accept();

                System.out.println("Il client si è connesso");

                int randomNum = (int)(Math.random() * 100+1); 
                
                NuovoThread t1 = new NuovoThread(s, randomNum);
                t1.start();
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza");
            System.exit(1);
        }
    }
}
