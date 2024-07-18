package tutes.jvm.ex2;

import java.lang.reflect.Method;

/*
    CCLRun executes a Java program by loading it through a
    CompilingClassLoader.
*/
public class CCLRun {
    static public void main(String args[]) throws Exception {
        // The first argument is the Java program (class) the user wants to run
        String progClass = args[0];

        // And the arguments to that program are just arguments 1..n, so separate those out into their own array
        String progArgs[] = new String[args.length - 1];
        System.arraycopy(args, 1, progArgs, 0, progArgs.length);

        CompilingClassLoader ccl = new CompilingClassLoader();
        Class clas = ccl.loadClass(progClass);

        // Use reflection to call its main() method, and to ppass the arguments in.
        // Get a class representing the type of the main method's argument
        Class mainArgType[] = {(new String[0]).getClass()};

        // Find the standard main method in the class
        Method main = clas.getMethod("main", mainArgType);

        // Create a list containing the arguments -- in this case, an array of strings
        Object argsArray[] = {progArgs};

        main.invoke(null, argsArray);
    }
}