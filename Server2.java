import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server2 implements Runnable {

    private int startNum;
    private int endNum;
    Scanner scanner = new Scanner(System.in);

    public static int findGCD(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static int findLCM(int a, int b) {
        return Math.abs(a * b) / findGCD(a, b);
    }

    @Override
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(8586);
            System.out.println("Server is waiting for a client connection...");
            Socket s = ss.accept();

            DataInputStream dis = new DataInputStream(s.getInputStream());

            startNum = dis.readInt();
            endNum = dis.readInt();

            System.out.println("Server received numbers: Start = " + startNum + ", End = " + endNum);



            int gcd = findGCD(startNum, endNum);
            int lcm = findLCM(startNum,endNum);
            System.out.println("The gcd of " + startNum + " and " + endNum + " is: " + gcd);
            System.out.println("The LCM of " + startNum + " and " + endNum + " is: " + lcm);


            scanner.close();

            dis.close();
            s.close();
            ss.close();
        } catch (Exception e) {
            System.out.println("Server error: " + e);
        }
    }
}
