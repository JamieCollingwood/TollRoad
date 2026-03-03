import java.sql.SQLOutput;

public class Truck extends Vehicle {
    private int numTrailers;

    public Truck(String registration, String manufacturer, int numTrailers){
        this.registration = registration;
        this.manufacturer = manufacturer;
        this.numTrailers = numTrailers;
    }

    @Override
    public int calculateBasicTripCost(){
        if (this.numTrailers <= 1){
            return 1250;
        }
        else{
            return 1500;
        }
    }

    public String toString(){
        return "This is a truck with registration " + this.registration + ", manufacturer " + this.manufacturer +
                " and has " + this.numTrailers + " trailers.";
    }

    public static void main(String[] args){
        Truck oneTrailer = new Truck("PZ65PWO", "Volvo", 1);
        Truck multipleTrailers = new Truck("BD51SMR", "Ford", 3);

        System.out.println("A truck with one trailer will cost " + oneTrailer.calculateBasicTripCost() +
                ", whereas a truck with multiple trailers will cost " + multipleTrailers.calculateBasicTripCost() +
                " pence at toll.");

        System.out.println(multipleTrailers.toString());
    }
}
