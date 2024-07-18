package tutes.oop2b;

public class PizzaOrder {
    // TODO: 3
    Pizza[] pizzas = new Pizza[3];
    private int numPizzas = 0;
    private int pos = 0;


    // TODO: 6

    public static void main(String[] args) {
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
    }

    public void setNumPizzas(int numPizzas) {
        this.numPizzas = numPizzas;
    }

    // TODO: 3
    public boolean addPizza(Pizza pizza) {
        if (pos > 2) {
            return false;
        } else {
            pizzas[pos] = pizza;
            pos++;
            return true;
        }
    }

    public double calcTotal() {
        double cost = 0;
        for (int i = 0; i < numPizzas; i++) {
            cost += pizzas[i].calcCost();
        }
        return cost;
    }

    // TODO: 5a
    public void sort() {
        pizzas = Pizza.reduceOrder(pizzas);
        Arrays.sort(pizzas);
    }

    // TODO: ExtraE
    public void sort(SortingP sortingP) {
        sortingP.sort(pizzas);
    }

    public Pizza[] getPizzas() {
        return pizzas;
    }
}
