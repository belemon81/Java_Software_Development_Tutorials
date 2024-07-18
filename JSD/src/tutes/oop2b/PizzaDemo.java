package tutes.oop2b;

// TODO: 6
public class PizzaDemo {
    public static void main(String[] args) {
        System.out.println("--------------- FULL DEMO ORDER ---------------\n");
        Pizza pizza1 = new PepperoniPizza(Size.MEDIUM, new Pizza.Topping(ToppingType.CHEESE, 2, 2), new Pizza.Topping(ToppingType.PEPPERONI, 8, 2));
        Pizza pizza2 = new TropicalPizza(Size.SMALL, new Pizza.Topping(ToppingType.CHEESE, 3, 2), new Pizza.Topping(ToppingType.PEPPERONI, 6, 2), new Pizza.Topping(ToppingType.HAM, 1, 2));
        Pizza pizza3 = new HamAndCheesePizza(Size.LARGE, new Pizza.Topping(ToppingType.HAM, 1, 2), new Pizza.Topping(ToppingType.CHEESE, 4, 2));
        PizzaOrder order = new PizzaOrder();
        order.setNumPizzas(3);
        order.addPizza(pizza1);
        order.addPizza(pizza2);
        order.addPizza(pizza3);
        double total = order.calcTotal();
        // ORDERED BY NAME
        System.out.println("You have ordered these pizza (sort by name):");
        order.sort(new PizzaSorting(Criteria.NAME));
        for (Pizza pizza : order.getPizzas()) {
            System.out.println("    + " + pizza.getDescription());
        }

        System.out.println("Bill:");
        order.sort(new PizzaSorting(Criteria.COST));
        int count = 0;
        for (Pizza pizza : order.getPizzas()) {
            System.out.printf("    %d. %-18s  ----------------  $%5.1f%n", ++count, pizza.getName(), pizza.calcCost());
        }
        order.sort();

        System.out.println("    ** TOTAL:              ================  $ " + total);
    }
}
