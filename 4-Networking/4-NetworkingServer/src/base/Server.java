package base;

import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class Server
{
    //initialize socket and input stream
    private ServerSocket server = null;
    private DataInputStream in =  null;
    private DataOutputStream out =  null;
    private ArrayList<Socket> allSockets = null;

    // constructor with port
    public Server(int port)
    {

        // starts server and waits for a connection
        try
        {
            server = new ServerSocket(port);
            allSockets = new ArrayList<>();
            System.out.println("Server started");
            System.out.println("Waiting for a client ...");

            while (true) {
                Socket socket = null;
                socket = server.accept();
                allSockets.add(socket);
                System.out.println("new Client: "+ socket);

                // takes input from the client socket
                in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));

                Thread t = new Handler(socket, in, out, this );
                t.start();
            }
            //System.out.println("Closing connection");
            //socket.close();
            //in.close();

        }
        catch(IOException i)
        {
            System.out.println(i);
            // close connection

        }
    }

    public void sendAll(String message) throws IOException {
        for (Socket s: allSockets){
            DataOutputStream outNew = new DataOutputStream(new BufferedOutputStream(s.getOutputStream()));
            outNew.writeUTF(message);
            //outNew.close();
        }
    }
}

class Handler extends Thread {
    private DataOutputStream out;
    private DataInputStream in;
    private Socket socket;
    private String name;
    private Server S;

    public Handler(Socket socket, DataInputStream in, DataOutputStream out, Server S) {
        this.socket = socket;
        this.in = in;
        this.out = out;
        this.name = name;
        this.S = S;
    }

    //method that is run, on by thread
    @Override
    public void run() {
        String received;

        //Main loop
        try {

            this.name = String.valueOf(socket.getPort());

            //S.sendAll("new Client Connected");
            while (true) {
                received = in.readUTF();
                //out.writeUTF("welcome");
                if (received.equals("Exit")) {
                    break;
                }
                System.out.println(this.name+": " + received);

                //out.writeUTF(received);
                //send to all others
                //S.sendAll(received);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        //close
        try {
            this.in.close();
            this.out.close();
        } catch (IOException d) {
            d.printStackTrace();
        }
    }

}