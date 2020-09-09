public abstract class Vehicle implements Runnable {
    protected float sizeFuelTank;
    protected float rateFuel;
    protected String name;
    private float remainsOfFuel;
    private FuelStation fuelStation;

    public Vehicle(FuelStation fuelStation) {
        this.fuelStation = fuelStation;
    }

    @Override
    public void run() {
        remainsOfFuel = sizeFuelTank;
        while (true) {
            System.out.println(name + " начал движение");
            while (remainsOfFuel > rateFuel) {
                remainsOfFuel -= rateFuel;
                System.out.println(String.format("[%s]: %.2f",name,remainsOfFuel));
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                fuelStation.refuel(this);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    public String getName() {
        return name;
    }

    public float getSizeFuelTank() {
        return sizeFuelTank-remainsOfFuel;
    }

    public void refueling(){
        remainsOfFuel = sizeFuelTank;
    }
}
