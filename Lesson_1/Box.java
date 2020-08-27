import java.util.ArrayList;

public class Box<T extends Fruit> {
    private ArrayList<T> fruits = new ArrayList<>();

    public void addFruit(T fruit) {
        fruits.add(fruit);
    }

    public float getWeight() {
        float weight = 0;
        for (T fruit : fruits) {
            weight += fruit.getWt();
        }
        return weight;
    }

    public ArrayList<T> getFruits(){
        return fruits;
    }

    public void clearBox(){
        fruits.clear();
    }

    public boolean compare(Box box) {
        return box.getWeight() == this.getWeight();
    }

    public void replaceFruitsFromOtherBox(Box<T> box){
        for(T fruit : box.getFruits()){
            addFruit(fruit);
        }
        box.clearBox();
    }
}
