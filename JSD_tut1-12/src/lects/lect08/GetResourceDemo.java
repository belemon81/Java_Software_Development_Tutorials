package lects.lect08;

import java.io.File;

public class GetResourceDemo {
    public static void main(String[] args) {
        System.out.println(new File("resources\\images").getAbsolutePath());
    }
}
