package budget;

import java.util.ArrayList;

public class SortCertainType extends Sort {
    Purchase[] purchasesOfType;
    String typeName;
    float total = 0;

    SortCertainType(ArrayList<Purchase> purchases, Type type) {
        this.typeName = type.getName();
        ArrayList<Purchase> filtered = Utils.filter(purchases, type);
        int size = filtered.size();
        purchasesOfType = new Purchase[size];
        for (int i = 0; i < size; i++) {
            purchasesOfType[i] = filtered.get(i);
            total += filtered.get(i).getPrice();
        }
    }

    @Override
    public void run() {
        if (purchasesOfType.length == 0) {
            System.out.println("\nPurchase list is empty!");
            return;
        }

        Utils.sort(purchasesOfType);

        System.out.println("\n" + typeName + "");
        for (Purchase purchase : purchasesOfType) {
            System.out.printf("%s $%.2f%n", purchase.getName(), purchase.getPrice());
        }
        System.out.printf("Total: $%.2f%n", total);
    }
}
