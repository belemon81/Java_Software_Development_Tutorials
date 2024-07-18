package tutes.oop2;

// TODO: 6
public class PizzaDemo {
    public static void main(String[] args) {
        System.out.println("--------------- Old Order ---------------");
        // Code to create a large pizza, 1 cheese, 1 ham
        Pizza p1 = new HamAndCheesePizza(Size.LARGE, new Pizza.Topping(ToppingType.HAM, 1, 2), new Pizza.Topping(ToppingType.CHEESE, 1, 2));
        // Code to create a medium pizza, 2 cheese, 2 pepperoni
        Pizza p2 = new PepperoniPizza(Size.MEDIUM, new Pizza.Topping(ToppingType.CHEESE, 2, 2), new Pizza.Topping(ToppingType.PEPPERONI, 2, 2));
        // Code to create an order
        PizzaOrder order = new PizzaOrder();
        order.setNumPizzas(2); // 2 pizzas in the order
        order.addPizza(p1);// Set first pizza
        order.addPizza(p2);// Set second pizza
        double total = order.calcTotal();
        // Should be 18+20 = 38

        System.out.println("Bill:");
        order.sort();
        for (Pizza pizza : order.getPizzas()) {
            System.out.println("+ " + pizza.getDescription());
        }
        System.out.println("Total: $" + total);

        System.out.println("--------------- New Order ---------------");
        Pizza pizza1 = new HamAndCheesePizza(Size.LARGE, new Pizza.Topping(ToppingType.HAM, 1, 2), new Pizza.Topping(ToppingType.CHEESE, 4, 2));
        Pizza pizza2 = new PepperoniPizza(Size.MEDIUM, new Pizza.Topping(ToppingType.CHEESE, 2, 2), new Pizza.Topping(ToppingType.PEPPERONI, 8, 2));
        Pizza pizza3 = new TropicalPizza(Size.SMALL, new Pizza.Topping(ToppingType.CHEESE, 3, 2), new Pizza.Topping(ToppingType.PEPPERONI, 6, 2), new Pizza.Topping(ToppingType.HAM, 1, 2));
        order = new PizzaOrder();
        order.setNumPizzas(3);
        order.addPizza(pizza1);
        order.addPizza(pizza2);
        order.addPizza(pizza3);
        total = order.calcTotal();

        System.out.println("Bill:");
        order.sort();
        for (Pizza pizza : order.getPizzas()) {
            System.out.println("+ " + pizza.getDescription());
        }
        System.out.println("Total: $" + total);
    }
}
