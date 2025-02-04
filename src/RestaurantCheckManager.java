import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Arrays;


public class RestaurantCheckManager {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        boolean stop = false;
        double sumSaleAmount = 0;
        double sumTipAmount = 0;
        double sumTotalAmount = 0;
        int checkCount = 0;


        while (!stop) {


            try {
                System.out.print("Enter the sale amount: ");
                double saleAmount = scnr.nextDouble();


                System.out.print("Enter the tip amount (Enter 0 if none): ");
                double tipAmount = scnr.nextDouble();


                System.out.print("Enter the total amount (Enter 0 if none): ");
                double totalAmount = scnr.nextDouble();


                //No total amount written
                if (totalAmount == 0) {
                    totalAmount = saleAmount + tipAmount;
                }
                //No tip amount written
                if (tipAmount == 0 && totalAmount > saleAmount) {
                    tipAmount = totalAmount - saleAmount;
                }
                //Nothing written
                if (totalAmount == 0 && tipAmount == 0) {
                    totalAmount = saleAmount;
                }
                //Sale and tip amounts don't add up to written total amount
                if (totalAmount != saleAmount + tipAmount) {
                    //Written total is less than sale amount so tip and total adjusted
                    if (totalAmount < saleAmount) {
                        tipAmount = 0;
                        totalAmount = saleAmount;
                    }
                    //Written tip amount too low compared to written total amount
                    else {
                        tipAmount = totalAmount - saleAmount;
                    }
                }


                sumSaleAmount += saleAmount;
                sumTipAmount += tipAmount;
                sumTotalAmount += totalAmount;
                checkCount += 1;


                System.out.println("Check count: " + checkCount);
                System.out.printf("Total sale so far: %.2f\n", sumSaleAmount);
                System.out.printf("Total pooled tip so far: %.2f\n", sumTipAmount);
                System.out.printf("Total sale + tip so far: %.2f\n", sumTotalAmount); //CAN DELETE LATER JUST FOR TESTING EDGE CASES
                System.out.print("Do you want to stop (y/n): ");
                String end = scnr.next();
                scnr.nextLine();


                if (end.equals("y") || end.equals("Y")) {
                    stop = true;
                    System.out.printf("Total sale amount is: %.2f\n", sumSaleAmount);
                    System.out.printf("Total pooled tip amount is: %.2f\n", sumTipAmount);


                    //string and int array to list the hours for each staff
                    String[] staff = {"Server 1", "Server 2", "Server 3", "Chef", "Sous Chef", "Kitchen Aid", "Host", "Busser"};
                    double[] workHours = new double[staff.length];
                    double totalHours = 0;


                    //Asks for the hours for each staff
                    int inputCount = 0;
                    while (inputCount < 3) {
                        try {
                            for (int i = 0; i < 3; i++) {
                                System.out.print("Enter hours worked by " + staff[i] + ": ");
                                workHours[i] = scnr.nextDouble();
                                totalHours += workHours[i];
                                inputCount += 1;
                            }
                        } catch (InputMismatchException excpt) {
                            System.out.println("Input error, please re-enter hours.");
                            inputCount = 0;
                            Arrays.fill(workHours, 0);
                            totalHours = 0;
                            scnr.nextLine();
                        }
                    }


                    double kitchenTips = 0.4 * sumTipAmount;
                    double hostTips = 0.1 * sumTipAmount;
                    double busserTips = 0.1 * sumTipAmount;
                    double serverTips = 0.4 * sumTipAmount;


                    //kitchen tips distribution
                    double chefTips = 0.5 * kitchenTips;
                    double souschefTips = 0.3 * kitchenTips;
                    double kitchenAidTips = 0.2 * kitchenTips;

                    double[] staffTips = {serverTips, serverTips, serverTips, chefTips, souschefTips, kitchenAidTips, hostTips, busserTips};


                    //print out tips
                    System.out.println("Tip Distributions:");
                    for (int i = 0; i < staffTips.length; i++) {
                        double distribute = (workHours[i] / totalHours) * serverTips;
                        if (staffTips[i] != serverTips) {
                            System.out.printf("%s: %.2f\n", staff[i], staffTips[i]);
                        } else {
                            System.out.printf("%s: %.2f\n", staff[i], distribute);
                        }
                    }
                }
            } catch (InputMismatchException excpt) {
                System.out.println("Input error, please re-enter amounts.");
                scnr.nextLine();
            }
        }
    }
}









