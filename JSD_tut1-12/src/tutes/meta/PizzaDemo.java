package tutes.meta;

public class PizzaDemo {
    public static Pizza.Topping getTopping(String name, double cost, int quantity) {
        return (Pizza.Topping) DataManager.newInstance(Pizza.Topping.class, new Object[]{name, cost, quantity});
    }

    public static TropicalPizza getTropicalPizza(String size, Pizza.Topping cheese, Pizza.Topping ham, Pizza.Topping pep) {
        return (TropicalPizza) DataManager.newInstance(TropicalPizza.class, new Object[]{size, cheese, ham, pep});
    }

    public static HamAndCheesePizza getHamAndCheesePizza(String size, Pizza.Topping cheese, Pizza.Topping ham) {
        return (HamAndCheesePizza) DataManager.newInstance(HamAndCheesePizza.class, new Object[]{size, cheese, ham});
    }

    public static PepperoniPizza getPepperoniPizza(String size, Pizza.Topping cheese, Pizza.Topping pep) {
        return (PepperoniPizza) DataManager.newInstance(PepperoniPizza.class, new Object[]{size, cheese, pep});
    }

    public static void main(String[] args) {
        // validate in newInstance()
        Pizza p1 = getTropicalPizza("small",
                getTopping("cheese", 2, 1),
                getTopping("pepperoni", 2, 2),
                getTopping("ham", 2, 3));
        Pizza p2 = getHamAndCheesePizza("small",
                getTopping("cheese", 2, 1),
                getTopping("ham", 2, 4));
        Pizza p3 = getPepperoniPizza("large",
                getTopping("cheese", 2, 5),
                getTopping("pepperoni", 2, 7));
        PizzaOrder po = new PizzaOrder(3);
        po.addPizza(p1);
        po.addPizza(p2);
        po.addPizza(p3);
        final String ban = "---------------------------------------------";
        System.out.println(ban);
        System.out.println("Pizza order (improved):\n" + po.getDescription());
        System.out.println(ban);

        // reduce order size
        po.setNumPizzas(2);
        po.sort();
        System.out.println(ban);
        System.out.println("Pizza order (reduced, sorted):\n" + po.getDescription());
        System.out.println(ban);

        po = new PizzaOrder(3);
        po.addPizza(p1);
        po.addPizza(p3);
        System.out.println(ban);
        System.out.println("Pizza order (2):\n" + po.getDescription());
        System.out.println(ban);

    }
}
