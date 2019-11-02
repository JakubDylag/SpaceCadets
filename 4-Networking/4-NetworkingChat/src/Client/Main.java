package Client;
import java.net.*;
import java.io.*;

public class Main {
    private static Integer port;

    public static void main(String[] args) {
        if(args.length > 0) {
            port = Integer.getInteger(args[0]);
            System.out.println("Starting Client, port: "+args[0]);
        } else {
            port = 5000;
            System.out.println("Starting Client, port: 5000");
        }

        Client client = new Client("127.0.0.1", 5000);

    }
}
