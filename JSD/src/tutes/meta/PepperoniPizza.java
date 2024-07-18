package tutes.meta;

public class PepperoniPizza extends Pizza {
    // Pepperoni pizzas don't have ham
    public PepperoniPizza(String size, Topping cheese, Topping pepperoni) {
        super(size, cheese, pepperoni, null);
    }

    // specific description for this type pizza
    public String getDescription() {
        Topping cheeseTopping = getCheeseTopping();
        Topping pepTopping = getPepperoniTopping();

        return "Pepperoni(" + getSize() + ", "
                + cheeseTopping + " " + pepTopping + ": $" + calcCost() + ")";
    }
}
