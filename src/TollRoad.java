import java.util.LinkedList;

public class TollRoad {
    private LinkedList<CustomerAccount> customerCollection;   //chosen a LinkedList here as adding elements is much
    // quicker than an ArrayList as it only has to add an extra pointer to add elements, whereas ArrayLists have to
    // create an entirely new array once full, larger than the previous and copy all elements over in the background.
    // For insertion and deletion of elements, LinkedList provides Big O(1) whereas ArrayList takes Big O(n).
    private int moneyMade;

    public TollRoad(){
        this.customerCollection = new LinkedList<>();
        this.moneyMade = 0;
    }

    public void addCustomer(CustomerAccount acc){
        customerCollection.add(acc);
    }

    public CustomerAccount findCustomer(String regNum) throws CustomerNotFoundException {

        try {
            for (int i=0; i< customerCollection.size(); i++){
                CustomerAccount element = customerCollection.get(i);
//                System.out.println(element.toString());
                if (element.getCustomerVehicle().getRegistration().equals(regNum)){
//                    System.out.println(element.getCustomerVehicle().getRegistration());
                    return element;
                }
            }
            throw new CustomerNotFoundException();
        }catch(CustomerNotFoundException customer){
//            System.out.println("The customer could not be found.");
            throw new CustomerNotFoundException();
        }
    }

    public void chargeCustomer(String registrationNumber) throws CustomerNotFoundException, InsufficientAccountBalanceException {
        CustomerAccount charge = this.findCustomer(registrationNumber);
        charge.makeTrip();
        int chargeTripCost = charge.getCustomerVehicle().calculateBasicTripCost();
        this.moneyMade += chargeTripCost;
//        System.out.println(this.moneyMade);                                     // testing moneyMade tally
    }

    public LinkedList<CustomerAccount> getCustomerCollection(){
        return this.customerCollection;
    }

    public int getMoneyMade(){
        return this.moneyMade;
    }

    public String toString(){
        String list = "";
        for(int i=0; i<this.customerCollection.size(); i++){
            list += "\n" + this.customerCollection.get(i);
        }
        return list;
    }



    public static void main (String[] args) throws CustomerNotFoundException, InsufficientAccountBalanceException{
        TollRoad m1 = new TollRoad();
//        m1.addCustomer("George", "Smith", 1500, new Car("AJO5PYL", "Volkswagen", 7));
        CustomerAccount customer1 = new CustomerAccount("Johnny", "Taylor", 20, new Van("EK12DLE", "Renault", 600));
        m1.addCustomer(customer1);
        m1.findCustomer("EK12DLE");
        m1.chargeCustomer("EK12DLE");
        System.out.println(m1.getMoneyMade());
        m1.addCustomer(new CustomerAccount("George", "Smith", 1500, new Car("AJO5PYL", "Volkswagen", 7)));

        System.out.println(m1.toString());


//        m1.addCustomer("John", "Smith")
    }
}
