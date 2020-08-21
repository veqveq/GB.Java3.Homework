import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        demoExercise1();
        demoExercise2();
        demoExercise3();
    }

    private static void demoExercise1() {
        String[] array = {"1", "2", "3", "4", "5"};
        try {
            replaceElementsOfArray(array, 0, 4);
            System.out.println(Arrays.toString(array));
        } catch (RuntimeException e) {
            System.out.println("Индексы вне границ массива");
            e.printStackTrace();
        }
    }

    private static <T>void replaceElementsOfArray(T[] array, Integer index1, Integer index2) throws RuntimeException {
        T val = array[index1];
        array[index1] = array[index2];
        array[index2] = val;
    }

    private static void demoExercise2() {
        Integer[] array = {1, 2, 3, 4, 5};
        ArrayList <?> arrayList = convertToArrayList(array);
        System.out.println(arrayList.toString());
        System.out.println(arrayList.get(1).getClass());
        System.out.println(arrayList.toString());
    }

    private static <T> ArrayList convertToArrayList(T[] array) {
        ArrayList<T> arrayList = new ArrayList();
        for (T obj : array) {
            arrayList.add(obj);
        }
        return arrayList;
    }

    private static void demoExercise3() {
        Box<Apple> appleBox1 = new Box<>();
        Box<Apple> appleBox2 = new Box<>();
        Box<Orange> orangeBox1 = new Box<>();
        Box<Orange> orangeBox2 = new Box<>();
        fillAppleBox(appleBox1);
        fillAppleBox(appleBox2);
        fillOrangeBox(orangeBox1);
        fillOrangeBox(orangeBox2);
        printBoxInfo(appleBox1, "appleBox1");
        printBoxInfo(appleBox2, "appleBox2");
        printBoxInfo(orangeBox1, "orangeBox1");
        printBoxInfo(orangeBox2, "orangeBox2");
        System.out.println(appleBox1.compare(orangeBox2));
        orangeBox1.replaceFruitsFromOtherBox(orangeBox2);
        printBoxInfo(orangeBox1, "orangeBox1");
        printBoxInfo(orangeBox2, "orangeBox2");
    }

    private static void fillAppleBox(Box<Apple> box) {
        Random rnd = new Random();
        int fruitsVal = rnd.nextInt(10) + 3;
        for (int i = 0; i < fruitsVal; i++) {
            box.addFruit(new Apple(rnd.nextInt(2) + 0.5f));
        }
    }

    private static void fillOrangeBox(Box<Orange> box) {
        Random rnd = new Random();
        int fruitsVal = rnd.nextInt(10) + 3;
        for (int i = 0; i < fruitsVal; i++) {
            box.addFruit(new Orange(rnd.nextInt(2) + 0.5f));
        }
    }

    private static void printBoxInfo(Box box, String name) {
        System.out.println(String.format("Коробка %s, количество: %d, масса: %.1f", name, box.getFruits().size(), box.getWeight()));
    }
}

