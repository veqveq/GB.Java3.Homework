public class Truck extends Vehicle {
    public Truck(String name, FuelStation fuelStation){
        super(fuelStation);
        this.name = "Грузовик " + name;
        sizeFuelTank = 60.0F;
        rateFuel = 15.0F;
    }
}
