package tutes.meta;


public class HamAndCheesePizza extends Pizza {
    // this type of pizza don't have pepperoni
    public HamAndCheesePizza(String size, Topping cheese, Topping ham) {
        super(size, cheese, null, ham);
    }

    // specific description for this type of pizza
    public String getDescription() {
        Topping cheeseTopping = getCheeseTopping();
        Topping hamTopping = getHamTopping();

        return "Ham&Cheese(" + getSize() + ", "
                + cheeseTopping + " " + hamTopping + ": $" + calcCost() + ")";
    }
}
