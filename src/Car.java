public class Car extends Vehicle{
    private int numberOfSeats;

    public Car(String registration, String manufacturer, int numberOfSeats){
        this.registration = registration;
        this.manufacturer = manufacturer;
        this.numberOfSeats = numberOfSeats;
    }
    @Override
    public int calculateBasicTripCost(){
        if (this.numberOfSeats<6){
            return 500;
        }
        else{
            return 600;
        }
    }

    public int getNumberOfSeats(){
        return numberOfSeats;
    }

    public String toString(){
        return "This is a car with registration " + this.registration + ", manufacturer " + this.manufacturer +
                " and has " + this.numberOfSeats + " seats.";
    }

    public static void main(String[] args){
        Car car = new Car("EK02DEU", "Renault", 5);
        System.out.println(car.calculateBasicTripCost());

        Car sevenSeater = new Car("AJ05PYL", "Volkswagen", 7);
        System.out.println(sevenSeater.calculateBasicTripCost());

        System.out.println(sevenSeater.toString());
    }

}
