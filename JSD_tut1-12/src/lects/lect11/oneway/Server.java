package lects.lect11.oneway;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(4444);
        Socket client = ss.accept();
        System.out.println("Got a client!");
        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
        Scanner sc = new Scanner(System.in);
        String line;
        do {
            System.out.print("Say something: ");
            line = sc.nextLine();
            out.println(line);
        } while (!line.equals("bye"));
        sc.close();
        out.close();
        client.close();
        ss.close();
    }
}
