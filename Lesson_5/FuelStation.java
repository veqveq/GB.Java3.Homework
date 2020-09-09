import java.util.concurrent.Semaphore;

public class FuelStation {
    private final float sizeFuelTank;
    private float remainsOfFuel;
    Semaphore semaphore = new Semaphore(3);

    public FuelStation() {
        this.sizeFuelTank = 100;
        this.remainsOfFuel = sizeFuelTank;
    }


    public void refuel(Vehicle vehicle) throws InterruptedException {
        System.out.println(vehicle.getName() + " заехал на заправку...");
        semaphore.acquire();
        takeFuel(vehicle);
        System.out.println(vehicle.getName() + " заправляется...");
        Thread.sleep(5000);
        System.out.println(String.format("%s заправился. На заправке осталось %.2f топлива", vehicle.getName(), remainsOfFuel));
        System.out.println(vehicle.getName() + " уехал с заправки");
        vehicle.refueling();
        semaphore.release();
    }

    private synchronized void takeFuel(Vehicle vehicle) throws InterruptedException {
        if (vehicle.getSizeFuelTank() > remainsOfFuel) {
            System.out.println(String.format("%s не заправлен! Мало топлива на заправке: [%.2f]", vehicle.getName(), remainsOfFuel));
            semaphore.release();
            throw new InterruptedException();
        }
        remainsOfFuel -= vehicle.getSizeFuelTank();
    }
}
