public class Main {
    public static void main(String[] args) {
        FuelStation gasStation = new FuelStation();
        new Thread(new Car("Mercedes",gasStation)).start();
        new Thread(new Car("BMW",gasStation)).start();
        new Thread(new Bus("Man",gasStation)).start();
        new Thread(new Truck("Scania",gasStation)).start();
        new Thread(new Car("Volvo",gasStation)).start();
    }
}
