package budget;

import java.util.ArrayList;

public class Utils {
    public static ArrayList<Purchase> filter(ArrayList<Purchase> purchases, Type type) {
        ArrayList<Purchase> filtered = new ArrayList<>();
        purchases.forEach(item -> {
            if (item.getType() == type) filtered.add(item);
        });
        return filtered;
    }

    public static void printItems(ArrayList<Purchase> purchases, String typeName) {
        System.out.println(typeName + ":");
        if (purchases.isEmpty()) {
            System.out.println("Purchase list is empty\n");
            return;
        }
        float sum = 0;
        for (Purchase purchase : purchases) {
            float price = purchase.getPrice();
            System.out.printf("%s $%.2f%n", purchase.getName(), price);
            sum += price;
        }
        System.out.printf("Total sum: $%.2f%n%n", sum);
    }

    public static void sort(Purchase[] purchases) {
        int size = purchases.length;
        int iteration = 1;
        boolean isSorting = true;
        while (isSorting) {
            isSorting = false;
            for (int i = 0; i < size - iteration; i++) {
                if (purchases[i].isSmallerThan(purchases[i + 1])) {
                    Purchase temp = purchases[i];
                    purchases[i] = purchases[i + 1];
                    purchases[i + 1] = temp;
                    isSorting = true;
                }
            }
            iteration++;
        }
    }
}
