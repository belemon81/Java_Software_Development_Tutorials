package lects.lect08.independent;

/**
 * @author dmle
 * @overview Represents a standard desktop GUI
 */
public class MyApp {

    private Controller c;

    private Gui g;

    /**
     * @effects initialise <tt>c</tt> and invoke <tt>c.displayGUI()</tt>
     */
    public MyApp() {
        c = new Controller();
        g = new Gui(c);
        c.setGui(g);
    }

    /**
     * The run method
     *
     * @effects create an instance of <tt>MyApp</tt>
     */
    public static void main(String[] args) {
        MyApp app = new MyApp();
        app.display();
    }

    /**
     * @effects show window <tt>g</tt>
     */
    public void display() {
        g.display();
    }
}
