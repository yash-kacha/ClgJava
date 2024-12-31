package com.mycompany.primepass;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client implements Runnable {

    @Override
    public void run() {
        try {
            // Connect to the server
            Socket s = new Socket("localhost", 7562);
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            Scanner sc = new Scanner(System.in);

          
            System.out.print("Enter Starting number: ");
            int x = sc.nextInt();

            System.out.print("Enter Ending number: ");
            int y = sc.nextInt();

            
            dos.writeInt(x); 
            dos.writeInt(y);  

            System.out.println("Client sent numbers: " + x + " and " + y);

          
            dos.close();
            s.close();
        } catch (Exception e) {
            System.out.println("Client error: " + e);
        }
    }
}