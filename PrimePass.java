package com.mycompany.primepass;

public class PrimePass {

    public static void main(String[] args) {
   
        Server s = new Server();
        Client c = new Client();
        
        Thread serverThread = new Thread(s);
        Thread clientThread = new Thread(c);

        serverThread.start();
        clientThread.start();

        try {
           
            serverThread.join();
            clientThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Both Server and Client have finished their tasks.");
    }
}
