package tutes.meta;

public class PizzaOrder {
    private Pizza[] pizzas;

    // initialise this to be a new order with the specified number of
    // pizzas
    public PizzaOrder(int num) {
        pizzas = new Pizza[num];
    }

    // initialise this to be a new order with the specified number of Pizzas,
    // copying any existing pizzas to the new order
    public void setNumPizzas(int num) {
        Pizza[] old = pizzas;
        pizzas = new Pizza[num];
        System.arraycopy(old, 0, pizzas, 0, Math.min(old.length, num));
    }

    // add a Pizza to this order
    // throws IllegalArgumentException if p is null
    public void addPizza(Pizza p) throws IllegalArgumentException {

        if (p == null)
            throw new IllegalArgumentException("PizzaOrder.addPizza: arg is null");

        Pizza p1;
        for (int i = 0; i < pizzas.length; i++) {
            p1 = pizzas[i];
            if (p1 == null) {
                pizzas[i] = p;
                break;
            }
        }
    }

    // return a pizza in this order
    public Pizza getPizza(int i) {
        // check i
        return pizzas[i];
    }

    // calculate total price of this order
    public double calcTotal() {
        double t = 0;
        Pizza p;
        for (int i = 0; i < pizzas.length; i++) {
            p = pizzas[i];
            if (p != null) {
                t += p.calcCost();
            }
        }
        return t;
    }

    // reduce the rep
    // remove all null items from the order
    private void reduceRep() {
        int numItems = 0;
        Pizza p;
        for (int i = 0; i < pizzas.length; i++) {
            p = pizzas[i];
            if (p != null) {
                numItems++;
            } else
                break;
        }

        if (numItems > 0) {
            Pizza[] newPizzas = new Pizza[numItems];
            System.arraycopy(pizzas, 0, newPizzas, 0, numItems);
            pizzas = newPizzas;
        }
    }

    // the order details
    public String getDescription() {
        StringBuffer desc = new StringBuffer();
        Pizza p;
        for (int i = 0; i < pizzas.length; i++) {
            p = pizzas[i];
            desc.append((p != null) ? p.getDescription() + "\n" : "");
        }

        desc.append("\nTotal cost: $" + calcTotal());
        return desc.toString();
    }

    // sort the order by pizza cost
    public void sort() {
        reduceRep();
        Arrays.sort(pizzas);
    }
}
