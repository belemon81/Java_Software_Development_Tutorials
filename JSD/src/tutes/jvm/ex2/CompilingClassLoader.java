package tutes.jvm.ex2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/*
    A CompilingClassLoader compiles your Java source on-the-fly. It
    checks for nonexistent .class files, or .class files that are older
    than their corresponding source code.
*/
public class CompilingClassLoader extends ClassLoader {
    // Given a filename, read the entirety of that file from disk
    // and return it as a byte array.
    private byte[] getBytes(String filename) throws IOException {
        File file = new File(filename);
        long len = file.length();

        // Create an array that's just the right size for the file's contents
        byte raw[] = new byte[(int) len];

        // Open the file
        FileInputStream fin = new FileInputStream(file);
        int r = fin.read(raw);

        if (r != len)
            throw new IOException("Can't read all, " + r + " != " + len);
        fin.close();
        return raw;
    }

    // Spawn a process to compile the java source code file
    // specified in the 'javaFile' parameter. Return a true if
    // the compilation worked, false otherwise.
    private boolean compile(String javaFile) throws IOException {
        // Let the user know what's going on
        System.out.println("CCL: Compiling " + javaFile + "...");

        // Start up the compiler
        Process p = Runtime.getRuntime().exec("javac " + javaFile);

        // Wait for it to finish running

        try {
            p.waitFor();
        } catch (InterruptedException ie) {
            System.out.println(ie);
        }

        // Check the return code, in case of a compilation error
        int ret = p.exitValue();

        // Tell whether the compilation worked
        return ret == 0;
    }

    // The heart of the ClassLoader -- automatically compile
    // source as necessary when looking for class files
    public Class loadClass(String name, boolean resolve)
            throws ClassNotFoundException {
        Class clas = null;
        clas = findLoadedClass(name);

        // System.out.println("findLoadedClass: " + clas);
        // Create a pathname from the class name
        // E.g. java.lang.Object => java/lang/Object
        String fileStub = name.replace('.', '/');

        // Build objects pointing to the source code (.java) and object code (.class)
        String javaFilename = fileStub + ".java";
        String classFilename = fileStub + ".class";
        File javaFile = new File(javaFilename);
        File classFile = new File(classFilename);

        // System.out.println("j " + javaFile.lastModified() + " c " + classFile.lastModified());
        if (javaFile.exists() && (!classFile.exists() || javaFile.lastModified() > classFile.lastModified())) {
            try {
                if (!compile(javaFilename) || !classFile.exists()) {
                    throw new ClassNotFoundException("Compile failed: " + javaFilename);
                }
            } catch (IOException ie) {
                throw new ClassNotFoundException(ie.toString());
            }
        }

        try {
            byte raw[] = getBytes(classFilename);
            // raw -> .class
            clas = defineClass(name, raw, 0, raw.length);
        } catch (IOException ie) {
            // We are dealing with a class in a library,
            // such as java.lang.Object
        }

        // System.out.println("defineClass: " + clas);
        // Maybe the class is in a library?
        if (clas == null) {
            clas = findSystemClass(name);
        }

        // System.out.println("findSystemClass: " + clas);
        // Resolve the class, if the "resolve" flag is set to true
        if (resolve && clas != null)
            resolveClass(clas);

        if (clas == null)
            throw new ClassNotFoundException(name);

        return clas;
    }
}