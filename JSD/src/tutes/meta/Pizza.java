package tutes.meta;

import tutes.meta.DomainConstraint.Type;

public abstract class Pizza implements Comparable {
    @DomainConstraint(type = Type.String, optional = false, length = 15)
    private String size;
    @DomainConstraint(type = Type.UserDefined)
    private Topping cheeseTopping;
    @DomainConstraint(type = Type.UserDefined)
    private Topping pepTopping;
    @DomainConstraint(type = Type.UserDefined)
    private Topping hamTopping;

    @DomainConstraint(type = Type.String, optional = false, length = 15)
    private String name;

    public Pizza(String s, Topping cheese, Topping pep, Topping ham) {
        size = s;
        cheeseTopping = cheese;
        pepTopping = pep;
        hamTopping = ham;

        name = this.getClass().getSimpleName();
    }

    private double sizeToCost() {
        if (size.equals("small")) {
            return 12D;
        } else if (size.equals("medium")) {
            return 14D;
        } else {
            return 16D;
        }
    }

    public double calcCost() {
        return sizeToCost()
                + ((cheeseTopping != null) ? cheeseTopping.calcCost() : 0) +
                ((pepTopping != null) ? pepTopping.calcCost() : 0) +
                ((hamTopping != null) ? hamTopping.calcCost() : 0);
    }

    public int getCheese() {
        if (cheeseTopping != null)
            return cheeseTopping.qty;
        return 0;
    }

    public int getHam() {
        if (hamTopping != null)
            return hamTopping.qty;
        return 0;
    }

    public int getPepperoni() {
        if (pepTopping != null)
            return pepTopping.qty;
        return 0;
    }

    public String getName() {
        return name;
    }

    protected Topping getCheeseTopping() {
        return cheeseTopping;
    }

    protected Topping getPepperoniTopping() {
        return pepTopping;
    }

    protected Topping getHamTopping() {
        return hamTopping;
    }

    public String getSize() {
        return size;
    }

    // implements Comparable interface
    public int compareTo(Object obj) throws IllegalArgumentException, ClassCastException {
        int result;

        if (obj == null) {
            throw new IllegalArgumentException("Pizza.compareTo: argument is null");
        } else {
            if (!(obj instanceof Pizza)) {
                throw new ClassCastException("Pizza.compareTo: argument is not a Pizza");
            } else {
                Pizza p = (Pizza) obj;
                double diff = this.calcCost() - p.calcCost();
                if (diff < 0)
                    result = -1;
                else if (diff == 0)
                    result = 0;
                else
                    result = 1;
            }
        }

        return result;
    }

    // general description of a pizza
    protected String getDescription() {
        return "Pizza(" + size + ", "
                + ((cheeseTopping != null) ? cheeseTopping + " " : "")
                + ((pepTopping != null) ? pepTopping + " " : "")
                + ((hamTopping != null) ? hamTopping + " " : "") + " : $" + calcCost() + ")";
    }

    public static class Topping {
        @DomainConstraint(type = Type.String, optional = false, length = 15)
        String name;
        @DomainConstraint(type = Type.Double, optional = false, min = 0)
        double cost;
        @DomainConstraint(type = Type.Integer, optional = false, min = 0)
        int qty;

        public Topping(String name, double cost, int qty) {
            this.name = name;
            this.cost = cost;
            this.qty = qty;
        }

        public Topping(String name, Double cost, Integer qty) {
            this(name, cost.doubleValue(), qty.intValue());
        }

        public double calcCost() {
            return cost * qty;
        }

        public String toString() {
            return qty + "@$" + cost + "-" + name;
        }
    }
}
