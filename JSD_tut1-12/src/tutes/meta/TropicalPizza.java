package tutes.meta;

public class TropicalPizza extends Pizza {
    // tropical pizzas have a combination of three toppings
    public TropicalPizza(String size, Topping cheese, Topping pepperoni, Topping ham) {
        super(size, cheese, pepperoni, ham);
    }

    // specific description for this type pizza
    public String getDescription() {
        Topping cheeseTopping = getCheeseTopping();
        Topping pepTopping = getPepperoniTopping();
        Topping hamTopping = getHamTopping();

        return "Tropical(" + getSize() + ", "
                + cheeseTopping + " " + pepTopping + " " + hamTopping + ": $" + calcCost() + ")";
    }
}
