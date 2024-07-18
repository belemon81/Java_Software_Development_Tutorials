package tutes.oop1;

public class Pizza {
    private Size size;
    private int cheeseToppings;
    private int pepperoniToppings;
    private int hamToppings;

    public Pizza(Size size, int cheeseToppings, int pepperoniToppings, int hamToppings) {
        this.size = size;
        this.cheeseToppings = cheeseToppings;
        this.pepperoniToppings = pepperoniToppings;
        this.hamToppings = hamToppings;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public int getCheeseToppings() {
        return cheeseToppings;
    }

    public void setCheeseToppings(int cheeseToppings) {
        this.cheeseToppings = cheeseToppings;
    }

    public int getPepperoniToppings() {
        return pepperoniToppings;
    }

    public void setPepperoniToppings(int pepperoniToppings) {
        this.pepperoniToppings = pepperoniToppings;
    }

    public int getHamToppings() {
        return hamToppings;
    }

    public void setHamToppings(int hamToppings) {
        this.hamToppings = hamToppings;
    }

    public double calcCost() {
        switch (size) {
            case SMALL:
                return 10 + 2 * (cheeseToppings + pepperoniToppings + hamToppings);
            case MEDIUM:
                return 12 + 2 * (cheeseToppings + pepperoniToppings + hamToppings);
            case LARGE:
                return 14 + 2 * (cheeseToppings + pepperoniToppings + hamToppings);
            default:
                return 0;
        }
    }

    protected String getDescription() {
        return "A " + this.getSize().toString().toLowerCase() + " pizza with " +
                this.getCheeseToppings() + (this.getCheeseToppings() > 1 ? " cheeses" : " cheese") +
                " and " + this.getHamToppings() + (this.getHamToppings() > 1 ? " hams" : " ham") +
                " should cost a total of $" + this.calcCost() + ".";
    }
}
