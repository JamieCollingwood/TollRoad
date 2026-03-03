import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TollRoadMain {

    public TollRoad initialiseTollRoadFromFile() throws FileNotFoundException, CustomerNotFoundException {
        TollRoad newTollRoad = new TollRoad();
        Scanner newCustomer = new Scanner(new File("customerData.txt"));
        newCustomer.useDelimiter("#");
        String vehicleType, regNum, firstName, lastName, manufacturer, discountType;
        int numberOfSeats, payload, numberOfTrailers, startingBalance;
        while (newCustomer.hasNext()) {
            Scanner attributes = new Scanner(newCustomer.next());
            attributes.useDelimiter(",");
            vehicleType = attributes.next();
            if (vehicleType.equals("Car")) {
                regNum = attributes.next();
                firstName = attributes.next();
                lastName = attributes.next();
                manufacturer = attributes.next();
                numberOfSeats = Integer.parseInt(attributes.next());
                startingBalance = Integer.parseInt(attributes.next());
                discountType = attributes.next();
                newTollRoad.addCustomer(new CustomerAccount(firstName, lastName, startingBalance,
                        new Car(regNum, manufacturer, numberOfSeats)));
            } else if (vehicleType.equals("Van")) {
                regNum = attributes.next();
                firstName = attributes.next();
                lastName = attributes.next();
                manufacturer = attributes.next();
                payload = Integer.parseInt(attributes.next());
                startingBalance = Integer.parseInt(attributes.next());
                discountType = attributes.next();
                newTollRoad.addCustomer(new CustomerAccount(firstName, lastName, startingBalance,
                        new Van(regNum, manufacturer, payload)));
            } else {
                regNum = attributes.next();
                firstName = attributes.next();
                lastName = attributes.next();
                manufacturer = attributes.next();
                numberOfTrailers = Integer.parseInt(attributes.next());
                startingBalance = Integer.parseInt(attributes.next());
                discountType = attributes.next();
                newTollRoad.addCustomer(new CustomerAccount(firstName, lastName, startingBalance,
                        new Truck(regNum, manufacturer, numberOfTrailers)));
            }

            if (discountType.equals("STAFF")) {
                newTollRoad.findCustomer(regNum).activateStaffDiscount();
            } else if (discountType.equals("FRIENDS_AND_FAMILY")) {
                newTollRoad.findCustomer(regNum).activateFriendsAndFamilyDiscount();
            }
        }
//        System.out.println(newTollRoad.toString());
        return newTollRoad;
    }

    public int simulateFromFile(TollRoad road) throws FileNotFoundException, CustomerNotFoundException, InsufficientAccountBalanceException {
        Scanner transactions = new Scanner(new File("transactions.txt"));
        transactions.useDelimiter("\\$");
        String instruction, registrationNumber;
        int balance;
        while (transactions.hasNext()){
            Scanner commaDelimiter = new Scanner(transactions.next());
            commaDelimiter.useDelimiter(",");
            instruction = commaDelimiter.next();
            if (instruction.equals("addFunds")){
                registrationNumber = commaDelimiter.next();
                balance = Integer.parseInt(commaDelimiter.next());
                try {
//                    System.out.println("balance before " + registrationNumber + ": " +   //testing addFunds added correctly
//                            road.findCustomer(registrationNumber).getAccountBalance());  //to accountbalance
                    road.findCustomer(registrationNumber).addFunds(balance);
                    System.out.println(registrationNumber + ": " + balance + " added successfully");
//                    System.out.println("balance after " + registrationNumber + ": " +
//                            road.findCustomer(registrationNumber).getAccountBalance());
                }
                catch(CustomerNotFoundException customer){
                    System.out.println(registrationNumber + ": addFunds failed. CustomerAccount does not exist");
                }
            }
            else if(instruction.equals("makeTrip")){
                registrationNumber = commaDelimiter.next();
                try{
//                    System.out.println("balance before " + registrationNumber + ": " +    // testing whether maketrip
//                        road.findCustomer(registrationNumber).getAccountBalance());       // deducts from accountbalance
                    road.chargeCustomer(registrationNumber);
//                    System.out.println("balance after " + registrationNumber + ": " +
//                            road.findCustomer(registrationNumber).getAccountBalance());
                }

                catch(CustomerNotFoundException customer){
                    System.out.println(registrationNumber + ": makeTrip failed. CustomerAccount does not exist");
                }
                catch(InsufficientAccountBalanceException add){
                    System.out.println(registrationNumber + ": makeTrip failed. Insufficient funds");
                }
            }
        }
        return road.getMoneyMade();
    }

    public static void main(String[] args) throws FileNotFoundException, CustomerNotFoundException, InsufficientAccountBalanceException {
        TollRoadMain toll = new TollRoadMain();
        toll.initialiseTollRoadFromFile();
        System.out.println(toll.initialiseTollRoadFromFile().toString());
        System.out.println("This toll road has made: " + toll.simulateFromFile(toll.initialiseTollRoadFromFile()));

    }
}
