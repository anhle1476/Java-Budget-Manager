package budget;

public class Purchase {
    private final String name;
    private float price;
    private final Type type;

    Purchase(String name, float price, Type type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public void addPrice(float newPrice) {
        this.price += newPrice;
    }

    public Type getType() {
        return type;
    }

    public boolean isSmallerThan(Purchase other) {
        return this.price < other.price;
    }
}
