public class Bus extends Vehicle{
    public Bus(String name, FuelStation fuelStation){
        super(fuelStation);
        this.name = "Автобус " + name;
        sizeFuelTank = 40.0F;
        rateFuel = 7.5F;
    }
}
