package lects.lect03;

import java.util.Scanner;

import static java.lang.System.out;

public class IODemo {
    public static void main(String[] args) {
        String input = "1.0 fish 2 fish red fish blue fish";
        String delim = "\\s*fish\\s*"; // ignore % fish %
        Scanner s = new
                Scanner(input).useDelimiter(delim);
        out.println(s.nextDouble());
        out.println(s.nextInt());
        out.println(s.next());
        out.println(s.next());
        s.close();

        out.println();

        String i2 = "hello world\nto be or \nnot to be";
        s = new Scanner(i2);
        out.println(s.next());
        out.println(s.nextLine());
        out.println(s.next());
        out.println(s.nextLine());
        s.close();
    }
}
