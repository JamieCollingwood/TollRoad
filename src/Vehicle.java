public class Vehicle {
    protected String registration, manufacturer;

    public Vehicle(String registration, String manufacturer){
        this.registration = registration;
        this.manufacturer = manufacturer;
    }

    public Vehicle(){
        this.registration = "EK02DEU";
        this.manufacturer = "Renault";
    }

    public int calculateBasicTripCost(){
        return -1;
    }

    public String getRegistration(){
        return registration;
    }

    public String getManufacturer(){
        return manufacturer;
    }

    public String toString(){
        return "This vehicle has a registration number of " + this.registration + " and is manufactured by " +
                this.manufacturer;
    }

    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle("EK02DEU", "Renault");

        System.out.println("A vehicle with registration: " + vehicle.getRegistration() + ", produced by manufacturer "
         + vehicle.getManufacturer() + ", with a trip cost of: " + vehicle.calculateBasicTripCost());

        System.out.println(vehicle.toString());
    }
}
