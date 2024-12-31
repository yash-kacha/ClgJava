public class Server2 implements Runnable {

    private int startNum;
    private int endNum;

    @Override
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(1234);
            System.out.println("Server is waiting for a client connection...");
            Socket s = ss.accept();

            DataInputStream dis = new DataInputStream(s.getInputStream());

            startNum = dis.readInt();
            endNum = dis.readInt();

            System.out.println("Server received numbers: Start = " + startNum + ", End = " + endNum);

            for (int i = startNum; i <= endNum; i++) {
                    if(i%j==0) 
                    {
                       System.out.println(i);
                    }
                }

            dis.close();
            s.close();
            ss.close();
        } catch (Exception e) {
            System.out.println("Server error: " + e);
        }
    }
}
