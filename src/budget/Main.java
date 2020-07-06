package budget;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final FileHandler fileHandler = new FileHandler();
    private static boolean isOn = true;
    private static Budget budget = new Budget();

    public static void main(String[] args) {
        String mainQuestion = "Choose your action:\n" +
                "1) Add income\n" +
                "2) Add purchase\n" +
                "3) Show list of purchases\n" +
                "4) Balance\n" +
                "5) Save\n" +
                "6) Load\n" +
                "7) Analyze (Sort)\n" +
                "0) Exit";
        while (isOn) {
            System.out.println(mainQuestion);
            switch (scanner.nextInt()) {
                case 0:
                    isOn = false;
                    System.out.println("\nBye!");
                    scanner.close();
                    break;
                case 1:
                    scanner.nextLine();
                    addIncomeHandler();
                    break;
                case 2:
                    scanner.nextLine();
                    addPurchaseHandler();
                    break;
                case 3:
                    scanner.nextLine();
                    showPurchaseList();
                    break;
                case 4:
                    scanner.nextLine();
                    showBalance();
                    break;
                case 5:
                    saveHandler();
                    break;
                case 6:
                    loadHandler();
                    break;
                case 7:
                    analyzeHandler();
                    break;
                default:
                    System.out.println("Action invalid");
            }
        }
    }

    private static void addIncomeHandler() {
        System.out.println("\nEnter income:");
        float newIncome = scanner.nextFloat();
        if (newIncome > 0) {
            budget.addIncome(newIncome);
            System.out.println("Income was added!\n");
        }
    }

    private static void addPurchaseHandler() {
        String question = "\nChoose the type of purchase\n" +
                "1) Food\n" +
                "2) Clothes\n" +
                "3) Entertainment\n" +
                "4) Other\n" +
                "5) Back";

        while (true) {
            int type = ask(question, 1, 5);
            if (type == 5) return;
            Type itemType = Type.getTypeByIndex(type);

            System.out.println("\nEnter purchase name:");
            scanner.nextLine();
            String item = scanner.nextLine();

            System.out.println("Enter its price:");
            budget.addPurchases(new Purchase(item, scanner.nextFloat(), itemType));
            System.out.println("Purchase was added!\n");
        }
    }

    private static void showPurchaseList() {
        while (true) {
            String question = "\nChoose the type of purchases\n" +
                    "1) Food\n" +
                    "2) Clothes\n" +
                    "3) Entertainment\n" +
                    "4) Other\n" +
                    "5) All\n" +
                    "6) Back";
            int type = ask(question, 1, 6);
            System.out.println();
            if (type == 6) return;
            if (type == 5) {
                Utils.printItems(budget.getPurchases(), "All");
                continue;
            }
            Type purchasesType = Type.getTypeByIndex(type);
            ArrayList<Purchase> purchasesList = Utils.filter(budget.getPurchases(), purchasesType);

            if (purchasesType != null) Utils.printItems(purchasesList, purchasesType.getName());
        }
    }

    private static void showBalance() {
        float expenses = 0;
        for (Purchase purchase : budget.getPurchases()) {
            expenses += purchase.getPrice();
        }
        System.out.printf("%nBalance: $%.2f%n%n", budget.getIncome() - expenses);
    }

    private static void saveHandler() {
        fileHandler.save(budget);
        System.out.println("\nPurchases were saved!\n");
    }

    private static void analyzeHandler() {
        String typeOfSortQuestion = "\nHow do you want to sort?\n" +
                "1) Sort all purchases\n" +
                "2) Sort by type\n" +
                "3) Sort certain type\n" +
                "4) Back";
        while (true) {
            Sort analyzer;
            int typeOfSort = ask(typeOfSortQuestion, 1, 4);
            switch (typeOfSort) {
                case 1:
                    analyzer = new SortAll(budget.getPurchases());
                    break;
                case 2:
                    analyzer = new SortByType(budget.getPurchases());
                    break;
                case 3:
                    String chooseTypeQuestion = "\nChoose the type of purchase\n" +
                            "1) Food\n" +
                            "2) Clothes\n" +
                            "3) Entertainment\n" +
                            "4) Other";
                    Type type = Type.getTypeByIndex(ask(chooseTypeQuestion, 1, 4));
                    assert type != null;
                    analyzer = new SortCertainType(budget.getPurchases(), type);
                    break;
                case 4:
                    System.out.println();
                    return;
                default:
                    throw new IllegalStateException("Unexpected value: " + typeOfSort);
            }
            analyzer.run();
        }
    }

    private static void loadHandler() {
        Budget newBudget = fileHandler.load();
        if (newBudget != null) {
            budget = newBudget;
            System.out.println("\nPurchases were loaded!\n");
        }
    }

    private static int ask(String question, int min, int max) {
        int answer = min - 1;
        while (answer < min || answer > max) {
            System.out.println(question);
            answer = scanner.nextInt();
        }
        return answer;
    }
}
