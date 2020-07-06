package budget;

import java.util.ArrayList;

public class Budget {
    private final ArrayList<Purchase> purchases;
    private float income = 0;

    public Budget() {
        this.purchases = new ArrayList<>();
        this.income = 0;
    };

    public Budget(float income, ArrayList<Purchase> purchases) {
        this.income = income;
        this.purchases = purchases;
    }

    public ArrayList<Purchase> getPurchases() {
        return purchases;
    }

    public void addPurchases(Purchase purchase) {
        this.purchases.add(purchase);
    }

    public float getIncome() {
        return income;
    }

    public void addIncome(float newIncome) {
        this.income += newIncome;
    }
}