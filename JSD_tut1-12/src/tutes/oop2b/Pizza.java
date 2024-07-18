package tutes.oop2b;

import java.util.Arrays;

// TODO: 4
public abstract class Pizza implements Comparable<Pizza> {
    private Size size;

    // TODO: 2fin
    private Topping hamToppings;
    private Topping pepperoniToppings;
    private Topping cheeseToppings;

    // TODO: 1ii
    private String name;

    // TODO: 1vi
    // TODO: 2fin
    protected Pizza(Size size, Topping hamToppings, Topping pepperoniToppings, Topping cheeseToppings) {
        this.size = size;
        this.hamToppings = hamToppings;
        this.pepperoniToppings = pepperoniToppings;
        this.cheeseToppings = cheeseToppings;
        // TODO: 1ii
        this.name = this.getClass().getSimpleName();
    }

    // TODO: 5b
    public static Pizza[] reduceOrder(Pizza[] pizzas) {
        Pizza[] reducedArray = new Pizza[pizzas.length];
        int count = 0;

        for (Pizza pizza : pizzas) {
            if (pizza != null) {
                reducedArray[count++] = pizza;
            }
        }
        return Arrays.copyOf(reducedArray, count);
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    // TODO: 1v
    protected Topping getCheeseToppings() {
        return cheeseToppings;
    }

    public void setCheeseToppings(Topping cheeseToppings) {
        this.cheeseToppings = cheeseToppings;
    }

    // TODO: 1v
    protected Topping getPepperoniToppings() {
        return pepperoniToppings;
    }

    public void setPepperoniToppings(Topping pepperoniToppings) {
        this.pepperoniToppings = pepperoniToppings;
    }

    // TODO: 1v
    protected Topping getHamToppings() {
        return hamToppings;
    }

    public void setHamToppings(Topping hamToppings) {
        this.hamToppings = hamToppings;
    }

    // TODO: 1iii
    public String getName() {
        return name;
    }

    // TODO: 1iv
    protected String getDescription() {
        int cheeses = 0, pepperonis = 0, hams = 0;
        if (hamToppings != null) hams = hamToppings.getQuantity();
        if (pepperoniToppings != null) pepperonis = pepperoniToppings.getQuantity();
        if (cheeseToppings != null) cheeses = cheeseToppings.getQuantity();
        return "A " + this.size.toString().toLowerCase() + " pizza with "
                + cheeses + (cheeses > 1 ? " cheeses" : " cheese") + ", "
                + pepperonis + (pepperonis > 1 ? " pepperonis" : " pepperoni") + " and "
                + hams + (hams > 1 ? " hams" : " ham") +
                " should cost a total of $" + this.calcCost() + ".";
    }

    public double calcCost() {
        int cheeses = 0, pepperonis = 0, hams = 0;
        if (hamToppings != null) hams = hamToppings.getQuantity();
        if (pepperoniToppings != null) pepperonis = pepperoniToppings.getQuantity();
        if (cheeseToppings != null) cheeses = cheeseToppings.getQuantity();
        switch (this.size) {
            case SMALL:
                return 10 + 2 * (pepperonis + hams + cheeses);
            case MEDIUM:
                return 12 + 2 * (pepperonis + hams + cheeses);
            case LARGE:
                return 14 + 2 * (pepperonis + hams + cheeses);
            default:
                return 0;
        }
    }

    // TODO: 4
    @Override
    public int compareTo(Pizza pizza) {
        return Double.compare(this.calcCost(), pizza.calcCost());
    }

    public int compareByName(Pizza pizza) {
        return this.getName().compareTo(pizza.getName());
    }

    // TODO: 2
    public static class Topping {
        private ToppingType name;
        private int quantity;
        private double cost;

        public Topping(ToppingType name, int quantity, double cost) {
            this.name = name;
            this.quantity = quantity;
            this.cost = cost;
        }

        public String getName() {
            return name.toString();
        }

        public int getQuantity() {
            return quantity;
        }

        public double getCost() {
            return cost;
        }

        public double calcCost() {
            return cost * quantity;
        }

        @Override
        public String toString() {
            return this.quantity + "@$" + this.cost + " - " + this.name.toString();
        }
    }
}