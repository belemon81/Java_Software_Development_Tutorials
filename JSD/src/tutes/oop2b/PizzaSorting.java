package tutes.oop2b;

// TODO: ExtraD
public class PizzaSorting implements SortingP {

    private Criteria criteria;

    public PizzaSorting(Criteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public void sort(Comparable[] pizzas) {
        if (criteria == Criteria.NAME) {
            sortByName((Pizza[]) pizzas);
        } else if (criteria == Criteria.COST) {
            sortByPrice((Pizza[]) pizzas);
        }
    }

    @Override
    public void sortByName(Pizza[] pizzas) {
        PizzaName[] pizzaNames = new PizzaName[pizzas.length];
        for (int i = 0; i < pizzas.length; i++) {
            pizzaNames[i] = new PizzaName(pizzas[i], pizzas[i].getName());
        }
        Arrays.sort(pizzaNames);
        for (int i = 0; i < pizzas.length; i++) {
            pizzas[i] = pizzaNames[i].getPizza();
        }
    }

    @Override
    public void sortByPrice(Pizza[] pizzas) {
        Arrays.sort(pizzas);
    }

    private class PizzaName implements Comparable<PizzaName> {
        String name;
        Pizza pizza;

        public PizzaName(Pizza pizza, String name) {
            this.pizza = pizza;
            this.name = name;
        }

        public Pizza getPizza() {
            return pizza;
        }

        @Override
        public int compareTo(PizzaName other) {
            return name.compareTo(other.name);
        }

    }
}
