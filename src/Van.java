public class Van extends Vehicle{
    private int payload;

    public Van(String registration, String manufacturer, int payload){
        this.registration = registration;
        this.manufacturer = manufacturer;
        this.payload = payload;
    }

    @Override
    public int calculateBasicTripCost(){
        if (this.payload <= 600){
            return 500;
        } else if (this.payload <= 800) {
            return 750;
        }
        else{
            return 1000;
        }
    }

    public String toString(){
        return "This is a van with registration " + this.registration + ", manufacturer " + this.manufacturer +
                " and a payload of " + this.payload + "KG";
    }

    public static void main(String[] args){
        Van lowPayload = new Van("EK12DLE", "Citroen", 600);
        Van mediumPayload = new Van("AJ09BLT", "Volkswagen", 800);
        Van highPayload = new Van("VN14UPU", "Ford", 900);

        System.out.println("A van with a low payload will cost " + lowPayload.calculateBasicTripCost() + " pence to pass toll." +
                "\nA van with a medium payload will cost " + mediumPayload.calculateBasicTripCost() + " pence to pass toll." +
                "\nA van with a high payload will cost " + highPayload.calculateBasicTripCost() + " pence to pass toll.");

        System.out.println(highPayload.toString());
    }
}
