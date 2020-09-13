package Test;

import Root.ArrayHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

public class ArrayHandlerTest {

    ArrayHandler arrayHandler;

    @BeforeEach
    private void setUp() {
        arrayHandler = new ArrayHandler();
    }

    @Test
    void shouldMethodReturnValuesAfterFourException() {
        Assertions.assertThrows(RuntimeException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                arrayHandler.getValuesAfterFour(new int[]{1,2,3});
            }
        });
    }

    @ParameterizedTest
    @NullSource
    void shouldMethodReturnValuesAfterFourNullArray(int[] array) {
        Assertions.assertThrows(RuntimeException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                arrayHandler.getValuesAfterFour(array);
            }
        });
    }

    @Test
    void shouldMethodReturnValuesAfterFourValues(){
        Assertions.assertArrayEquals(arrayHandler.getValuesAfterFour(new int[]{1,2,3,4,5,6}),new Integer[]{5,6});
    }

    @Test
    void shouldMethodCheckContainsFourAndOneResultIsTrue(){
        Assertions.assertTrue(arrayHandler.checkContainsFourAndOne(new Integer[]{1,2,3,4}));
        Assertions.assertTrue(arrayHandler.checkContainsFourAndOne(new Integer[]{1,2,3}));
        Assertions.assertTrue(arrayHandler.checkContainsFourAndOne(new Integer[]{4,2,3}));
    }

    @Test
    void shouldMethodCheckContainsFourAndOneResultIsFalse(){
        Assertions.assertFalse(arrayHandler.checkContainsFourAndOne(new Integer[]{2,3,14}));
    }

    @ParameterizedTest
    @NullSource
    void shouldMethodCheckContainsFourAndOneResultException(Integer[] arr){
        Assertions.assertThrows(RuntimeException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                arrayHandler.checkContainsFourAndOne(arr);
            }
        });
    }
}
