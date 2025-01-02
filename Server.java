
import java.io.*;
import java.net.*;
import java.util.*;

public class Server implements Runnable {

    private int startNum;
    private int endNum;

    @Override
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(2589);
            System.out.println("Server is waiting for a client connection...");
            Socket s = ss.accept();

            DataInputStream dis = new DataInputStream(s.getInputStream());

            startNum = dis.readInt();
            endNum = dis.readInt();

            System.out.println("Server received numbers: Start = " + startNum + ", End = " + endNum);

            int sumOfPrime=0;
            for (int i = startNum; i <= endNum; i++) {
                int ch=1;
                
                
                for (int j = 2; j <= Math.sqrt(i); j++) {
                    if(i%j==0) 
                    {
                       ch=0;
                       break;
                    }
                }
                if(ch==1) {
                    sumOfPrime++;
                    System.out.println("Prime number : "+sumOfPrime + " is : "+i);

                }
            }

            System.out.println("Total count is : "+sumOfPrime);
            dis.close();
            s.close();
            ss.close();
        } catch (Exception e) {
            System.out.println("Server error: " + e);
        }
    }
}
