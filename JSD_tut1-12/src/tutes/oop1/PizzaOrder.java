package tutes.oop1;

public class PizzaOrder {
    Pizza[] pizzas = new Pizza[3];
    int numPizzas;

    public static void main(String[] args) {
        // Code to create a large pizza, 1 cheese, 1 ham
        Pizza pizza1 = new Pizza(Size.LARGE, 1, 0, 1);
        // Code to create a medium pizza, 2 cheese, 2 pepperoni
        Pizza pizza2 = new Pizza(Size.MEDIUM, 2, 2, 0);
        // Code to create an order
        PizzaOrder order = new PizzaOrder();
        order.setNumPizzas(2); // 2 pizzas in the order
        order.setPizza1(pizza1);// Set first pizza
        order.setPizza2(pizza2);// Set second pizza
        double total = order.calcTotal();
        // Should be 18+20 = 38
        System.out.println("Total: $" + total);
    }

    public void setNumPizzas(int numPizzas) {
        this.numPizzas = numPizzas;
    }

    public void setPizza1(Pizza pizza1) {
        this.pizzas[0] = pizza1;
    }

    public void setPizza2(Pizza pizza2) {
        this.pizzas[1] = pizza2;
    }

    public void setPizza3(Pizza pizza3) {
        this.pizzas[2] = pizza3;
    }

    public double calcTotal() {
        double cost = 0;
        for (int i = 0; i < numPizzas; i++) {
            cost += pizzas[i].calcCost();
        }
        return cost;
    }
}
