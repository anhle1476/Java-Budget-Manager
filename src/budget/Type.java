package budget;

public enum Type {
    FOOD(1, "Food"),
    CLOTHES(2, "Clothes"),
    ENTERTAINMENT(3, "Entertainment"),
    OTHER(4, "Other");

    int index;
    String name;
    Type(int index, String name) {
        this.index = index;
        this.name = name;
    }

    public int getIndex() { return index;}

    public String getName() {
        return name;
    }

    public static Type getTypeByIndex(int i) {
        for (Type type : values()) {
            if (i == type.index) return type;
        }
        return null;
    }
}
