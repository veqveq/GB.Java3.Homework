public class Car extends Vehicle {
    public Car(String name, FuelStation fuelStation){
        super(fuelStation);
        this.name = "Автомобиль " + name;
        sizeFuelTank = 20.0F;
        rateFuel = 2.5F;
    }
}
