package com.mycompany.primepass;


public class PrimePass {

    public static void main(String[] args) {
   
        Server s = new Server();
        // Server2 s2 = new Server2();
        Client c = new Client();
        
        Thread serverThread = new Thread(s);
        // Thread serverThread2 = new Thread(s2);

        Thread clientThread = new Thread(c);

        serverThread.start();
        // serverThread2.start();
        clientThread.start();

        try {

            serverThread.join();
            // serverThread2.join();
            clientThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Both Server and Client have finished their tasks.");
    }
}
