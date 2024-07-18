package tutes.oop1;

public class PizzaDemo {
    public static void main(String[] args) {
        Pizza pizza1 = new Pizza(Size.LARGE, 1, 2, 1);
        Pizza pizza2 = new Pizza(Size.MEDIUM, 2, 1, 2);
        Pizza pizza3 = new Pizza(Size.SMALL, 4, 4, 4);
        Pizza pizza4 = new Pizza(Size.LARGE, 5, 6, 3);
        Pizza pizza5 = new Pizza(Size.SMALL, 3, 1, 2);
        System.out.println(pizza1.getDescription());
        System.out.println(pizza2.getDescription());
        System.out.println(pizza3.getDescription());
        System.out.println(pizza4.getDescription());
        System.out.println(pizza5.getDescription());
    }
}
