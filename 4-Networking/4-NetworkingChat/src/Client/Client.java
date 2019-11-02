package Client;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client
{
    // initialize socket and input output streams
    private Socket socket = null;
    private DataInputStream input = null;
    private DataOutputStream out = null;
    private Scanner scn = null;

    // constructor to put ip address and port
    public Client(String address, int port)
    {
        // establish a connection
        try
        {
            socket = new Socket(address, port);
            System.out.println("Connected");

            // takes IO from socket
            input = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            out = new DataOutputStream(socket.getOutputStream());

            //reads terminal
            scn = new Scanner(System.in);

            Thread t = new Handler(input , this);
            //t.start();
        }
        catch(UnknownHostException u)
        {
            System.out.println(u);
        }
        catch(IOException i)
        {
            System.out.println(i);
        }



        // string to read message from input
        String line = "";

        // keep reading until "Exit" is input
        while (!line.equals("Exit"))
        {
            try
            {
                line = scn.nextLine();
                out.writeUTF(line);

                //String received = input.readUTF();
                //System.out.println(received);
            }
            catch(IOException i)
            {
                System.out.println(i);
            }
        }



        // close the connection
        try
        {
            scn.close();
            input.close();
            out.close();
            socket.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }
}

class Handler extends Thread {
    private DataInputStream in;
    private Client C;

    public Handler(DataInputStream in, Client C) {
        this.in = in;
        this.C = C;

    }


    //method that is run, on by thread
    @Override
    public void run() {
        System.out.println("running handler");

        /*try {
            Socket s = new Socket("127.0.0.1", 5000);
            in = new DataInputStream(new BufferedInputStream(s.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        String received;
        while (true){
            try {
                received = in.readUTF();
                System.out.println("got: " + received);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}