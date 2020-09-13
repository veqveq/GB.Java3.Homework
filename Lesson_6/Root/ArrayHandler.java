package Root;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayHandler {

    public Integer[] getValuesAfterFour(int[] values) {
        List<Integer> removedArrList = new ArrayList<>();
        for (int i = values.length - 1; i >= 0; i--) {
            if (values[i] != 4) {
                removedArrList.add(0, values[i]);
            } else {
                break;
            }
        }
        if (removedArrList.size() == values.length) throw new RuntimeException("Цифра 4 отсутствую в исходном массиве");
        return removedArrList.toArray(new Integer[0]);
    }

    public <T> boolean checkContainsFourAndOne(T[] values){
        List<T> array;
        try {
            array= Arrays.asList(values);
        }catch (RuntimeException e){
            throw new RuntimeException("Передан пустой массив");
        }
        if (!array.contains(4) && !array.contains(1)) return false;
        return true;
    }
}
