public class CustomerAccount  implements Comparable<CustomerAccount>{
    private String firstName, lastName, discountLevel;
    private int accountBalance;
    private Vehicle customerVehicle;

    public CustomerAccount(String firstName, String lastName, int accountBalance, Vehicle customerVehicle){
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountBalance = accountBalance;
        this.customerVehicle = customerVehicle;
        this.discountLevel = "";
    }

    public void activateStaffDiscount(){
        this.discountLevel = "Staff";
    }

    public void activateFriendsAndFamilyDiscount(){
        this.discountLevel = "FriendsAndFamily";
    }

    public void deactivateDiscount(){
        this.discountLevel = "";
    }

    public void addFunds(int amount){
        this.accountBalance = this.accountBalance + amount;
    }

    public void makeTrip() throws InsufficientAccountBalanceException{
        double tripCost = this.customerVehicle.calculateBasicTripCost();
        if (this.discountLevel.equals("Staff")){
            tripCost = Math.floor(tripCost * 0.5);
        }
        else if (this.discountLevel.equals("FriendsAndFamily")){
            tripCost = Math.floor(tripCost * 0.9);
        }

        try{
            if (this.accountBalance >= tripCost){
                this.accountBalance = (this.accountBalance - (int)tripCost);
                System.out.println(this.customerVehicle.registration + ": Trip completed successfully");
            }
            else{
                throw new InsufficientAccountBalanceException();}
        }catch(InsufficientAccountBalanceException e){
            System.out.println(this.customerVehicle.registration + ": makeTrip failed. Insufficient funds");
        }
    }

    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public String getDiscountLevel(){
        return this.discountLevel;
    }

    public int getAccountBalance(){
        return this.accountBalance;
    }

    public Vehicle getCustomerVehicle(){
        return this.customerVehicle;
    }

    @Override
    public int compareTo(CustomerAccount o){
        if (this.customerVehicle.registration.compareTo(o.customerVehicle.registration) > 1){
            return 1;
        }else if(this.customerVehicle.registration.compareTo(o.customerVehicle.registration) == 0){
            return 0;
        }
        return -1;
    }

    public String toString(){
        return "First name: " + this.firstName + ", last name: " + this.lastName + ", account balance: " +
                this.accountBalance + ", vehicle details: " + this.customerVehicle.toString() +
                ", discount type: " + this.discountLevel;
    }

    public static void main(String[] args) throws InsufficientAccountBalanceException {
        CustomerAccount customer = new CustomerAccount("Johnny", "Taylor", 20, new Van("EK12DLE", "Renault", 600));
//        customer.makeTrip();

        customer.addFunds(500);
        customer.activateFriendsAndFamilyDiscount();
        System.out.println(customer.getDiscountLevel());
        customer.deactivateDiscount();
        System.out.println(customer.getDiscountLevel());                        //testing methods inc. makeTrip

        customer.makeTrip();
        System.out.println(customer.getAccountBalance()); //520-(0.9*500) = 70

        CustomerAccount customerCompare = new CustomerAccount("George", "Smith", 1500, new Car("AJO5PYL", "Volkswagen", 7));
        System.out.println(customer.compareTo(customerCompare));
        System.out.println(customerCompare.compareTo(customer));
        System.out.println(customer.compareTo(customer));
    }


}
