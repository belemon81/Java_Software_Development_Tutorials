package tutes.oop2b;

public class TropicalPizza extends Pizza {

    // TODO: 1i
    // TODO: 2fin
    public TropicalPizza(Size size, Topping cheeseToppings, Topping pepperoniToppings, Topping hamToppings) {
        // TODO: 1vi
        super(size, cheeseToppings, pepperoniToppings, hamToppings);
    }

    // TODO: 1iv
    public String getDescription() {
        return "A " + this.getSize().toString().toLowerCase() + " " + this.getName() + " with " +
                this.getCheeseToppings().getQuantity() + (this.getCheeseToppings().getQuantity() > 1 ? " cheeses" : " cheese") + ", " +
                this.getPepperoniToppings().getQuantity() + (this.getPepperoniToppings().getQuantity() > 1 ? " pepperonis" : " pepperoni") +
                " and " + this.getHamToppings().getQuantity() + (this.getHamToppings().getQuantity() > 1 ? " hams" : " ham") +
                " should cost a total of $" + this.calcCost() + ".";
    }
}
