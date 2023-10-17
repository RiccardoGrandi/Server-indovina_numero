package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class App 
{
    public static void main( String[] args )
    {
        try {
            ServerSocket server = new ServerSocket(3000);

            int randomNum = (int)(Math.random() * 100+1); 
            
            Socket s = server.accept();
            
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
             
            int tentativi = 0;
            int numRicevuto = 0;

            do {
                String stringaRicevuta = in.readLine();
                numRicevuto = Integer.parseInt(stringaRicevuta);
                System.out.println(numRicevuto);
                if (numRicevuto < randomNum) {
                    out.writeBytes(1 + "\n");
                } else if (numRicevuto > randomNum) {
                    out.writeBytes(2 + "\n"); 
                }
                tentativi++;
            } while (numRicevuto != randomNum);

           
            out.writeBytes(3 + "\n");
            out.writeBytes(tentativi + "\n");    
            
            server.close();
            s.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza");
            System.exit(1);
        }
    }
}
