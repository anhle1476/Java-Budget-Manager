package budget;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    File file = new File("purchases.txt");

    public void save(Budget budget) {
        float income = budget.getIncome();
        ArrayList<Purchase> purchases = budget.getPurchases();

        try (PrintWriter printWriter = new PrintWriter(file)) {
            printWriter.println(income);
            for (Purchase purchase : purchases) {
                printWriter.println(purchase.getName() + "__::__" + purchase.getPrice() + "__::__" + purchase.getType().getIndex());
            }

        } catch (FileNotFoundException exception) {
            System.out.println("Error: File Not Found.");
        } catch (NullPointerException exception) {
            System.out.println("Error: Item Not Found.");
        } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }

    public Budget load() {
        ArrayList<Purchase> purchases = new ArrayList<>();

        try (Scanner scanner = new Scanner(file)) {
            // read income from first line & purchases from remain lines
            float income = scanner.nextFloat();
            scanner.nextLine();

            while (scanner.hasNextLine()) {
                String[] itemInfo = scanner.nextLine().split("__::__");
                Type type = Type.getTypeByIndex(Integer.parseInt(itemInfo[2]));
                purchases.add(new Purchase(itemInfo[0] , Float.parseFloat(itemInfo[1]), type));
            }

            return new Budget(income, purchases);
        } catch (FileNotFoundException exception) {
            System.out.println("Error: File Not Found.");
            return null;
        }
    }
}
