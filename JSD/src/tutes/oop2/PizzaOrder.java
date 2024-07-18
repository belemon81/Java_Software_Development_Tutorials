package tutes.oop2;

public class PizzaOrder {
    // TODO: 3
    Pizza[] pizzas = new Pizza[3];
    private int numPizzas = 0;
    private int pos = 0;

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

    public Pizza[] getPizzas() {
        return pizzas;
    }
}
