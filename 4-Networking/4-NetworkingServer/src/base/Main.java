package base;

import java.net.*;
import java.io.*;

/*
 Server program, which receives messages and prints them on it's local display
The server will need a ServerSocket (which opens a socket on your local machine).
A ServerSocket cannot do anything until a client connects. Look at .accept() for this.
Get your client sending messages, and have the server listen and print these out.

EXTRA
multithreading
server execute barebones
 */

public class Main {
    private static Integer socketNum;

    public static void main(String[] args) {
        System.out.println("SERVER");
        if (args.length > 0) {
            socketNum = Integer.getInteger(args[0]);
            System.out.println("Hosting at: " + args[0]);
        } else {
            socketNum = 5000;
        }

        Server myServer = new Server(socketNum);
    }

}
