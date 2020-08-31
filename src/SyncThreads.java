public class SyncThreads {
    private final Object mon = new Object();
    private char currentChar = 'A';

    public static void main(String[] args) {
        SyncThreads app = new SyncThreads();
        Thread threadA = new Thread(() -> app.printA());
        Thread threadB = new Thread(() -> app.printB());
        Thread threadC = new Thread(() -> app.printC());
        threadA.start();
        threadB.start();
        threadC.start();
    }

    private void printA(){
        synchronized (mon){
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentChar !='A') {
                        mon.wait();
                    }
                    System.out.print('A');
                    currentChar = 'B';
                    mon.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void printB(){
        synchronized (mon){
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentChar !='B') {
                        mon.wait();
                    }
                    System.out.print('B');
                    currentChar = 'C';
                    mon.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void printC(){
        synchronized (mon){
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentChar !='C') {
                        mon.wait();
                    }
                    System.out.print('C');
                    currentChar = 'A';
                    mon.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
