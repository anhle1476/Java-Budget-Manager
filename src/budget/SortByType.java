package budget;

import java.util.ArrayList;

public class SortByType extends Sort {
    // save Types of purchases by index of Type.index - 1
    Purchase[] typesOfPurchases = new Purchase[4];
    float total = 0;

    SortByType(ArrayList<Purchase> purchases) {

        typesOfPurchases[0] = new Purchase("Food", 0, Type.FOOD);
        typesOfPurchases[1] = new Purchase("Clothes", 0, Type.CLOTHES);
        typesOfPurchases[2] = new Purchase("Entertainment", 0, Type.ENTERTAINMENT);
        typesOfPurchases[3] = new Purchase("Other", 0, Type.OTHER);

        for (Purchase purchase : purchases) {
            int thisIndex = purchase.getType().getIndex() - 1;
            float thisPrice = purchase.getPrice();
            typesOfPurchases[thisIndex].addPrice(thisPrice);
            total += thisPrice;
        }
    }

    @Override
    public void run() {
        Utils.sort(typesOfPurchases);

        System.out.println("\nTypes:");
        for (Purchase type : typesOfPurchases) {
            System.out.printf("%s - $%.2f%n", type.getName(), type.getPrice());
        }
        System.out.printf("Total sum: $%.2f%n", total);
    }
}
