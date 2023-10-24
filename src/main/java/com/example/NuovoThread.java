package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class NuovoThread extends Thread{
    private Socket s;
    private int n;

    public NuovoThread (Socket s, int n) {
        this.s = s;
        this.n = n;
    }

    public void run () {
        try {
            int tentativi = 0;
            int numRicevuto = 0;

            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            do {
                String stringaRicevuta = in.readLine();
                numRicevuto = Integer.parseInt(stringaRicevuta);
                System.out.println(Thread.currentThread().getName() + " ha digitato " + numRicevuto);
                if (numRicevuto < n) {
                    out.writeBytes(1 + "\n");
                } else if (numRicevuto > n) {
                    out.writeBytes(2 + "\n"); 
                }
                tentativi++;
            } while (numRicevuto != n);

            out.writeBytes(3 + "\n");
            out.writeBytes(tentativi + "\n"); 
            
            s.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza");
            System.exit(1);
        }
    }
}
