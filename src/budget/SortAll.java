package budget;

import java.util.ArrayList;

public class SortAll extends Sort {
    Purchase[] purchases;
    float total = 0;

    SortAll(ArrayList<Purchase> purchasesList) {
        int size = purchasesList.size();
        purchases = new Purchase[size];
        for (int i = 0; i < size; i++) {
            this.purchases[i] = purchasesList.get(i);
            total += purchasesList.get(i).getPrice();
        }
    }

    @Override
    public void run() {
        if (purchases.length == 0) {
            System.out.println("\nPurchase list is empty!");
            return;
        }

        Utils.sort(purchases);

        System.out.println("\nAll:");
        for (Purchase purchase : purchases) {
            System.out.printf("%s $%.2f%n", purchase.getName(), purchase.getPrice());
        }
        System.out.printf("Total: $%.2f%n", total);
    }
}
