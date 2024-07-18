package tutes.oop2b;

public class HamAndCheesePizza extends Pizza {

    // TODO: 1i
    // TODO: 2fin
    public HamAndCheesePizza(Size size, Topping hamToppings, Topping cheeseToppings) {
        // TODO: 1vi
        super(size, cheeseToppings, null, hamToppings);
    }

    // TODO: 1iv
    @Override
    public String getDescription() {
        return "A " + this.getSize().toString().toLowerCase() + " " + this.getName() + " with " +
                this.getCheeseToppings().getQuantity() + (this.getCheeseToppings().getQuantity() > 1 ? " cheeses" : " cheese") +
                " and " + this.getHamToppings().getQuantity() + (this.getHamToppings().getQuantity() > 1 ? " hams" : " ham") +
                " should cost a total of $" + this.calcCost() + ".";
    }
}
