package tutes.oop2;

public class PepperoniPizza extends Pizza {

    // TODO: 1i
    // TODO: 2fin
    public PepperoniPizza(Size size, Topping cheeseToppings, Topping pepperoniToppings) {
        // TODO: 1vi
        super(size, null, pepperoniToppings, cheeseToppings);
    }

    // TODO: 1iv
    @Override
    public String getDescription() {
        return "A " + this.getSize().toString().toLowerCase() + " " + this.getName() + " with " +
                this.getPepperoniToppings().getQuantity() + (this.getPepperoniToppings().getQuantity() > 1 ? " pepperonis" : " pepperoni")
                + " and " + this.getCheeseToppings().getQuantity() + (this.getCheeseToppings().getQuantity() > 1 ? " cheeses" : " cheese") +
                " should cost a total of $" + this.calcCost() + ".";
    }
}
