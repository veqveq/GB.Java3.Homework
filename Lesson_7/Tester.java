import java.lang.reflect.Method;
import java.util.ArrayList;

public class Tester {

    private void start(Class testedClass) throws Exception {
        Method afterSuite = null;
        Method beforeSuite = null;

        Object testedObject = testedClass.getDeclaredConstructor().newInstance(null);
        Method[] methods = testedClass.getDeclaredMethods();
        ArrayList<Method> methodsList = new ArrayList<>();

        for (Method m : methods) {
            if (m.isAnnotationPresent(BeforeSuite.class)) {
                if (beforeSuite == null) {
                    beforeSuite = m;
                } else {
                    throw new RuntimeException("Класс содержит больше одного метода @BeforeSuite");
                }
            } else if (m.isAnnotationPresent(AfterSuite.class)) {
                if (afterSuite == null) {
                    afterSuite = m;
                } else {
                    throw new RuntimeException("Класс содержит больше одного метода @AfterSuite");
                }
            } else if (m.isAnnotationPresent(Test.class)) {
                if (m.getAnnotation(Test.class).priority() > 10 || m.getAnnotation(Test.class).priority() < 0) {
                    throw new RuntimeException(
                            String.format("Класс [%s]; Метод [%s]; Приоритет [%d]. " +
                                            "Приоритет для @Test указывается в пределах [0:10]",
                                    testedClass.getName(), m.getName(),m.getAnnotation(Test.class).priority()));
                }
                methodsList.add(m);
            }
        }

        methodsList.sort((o1, o2) -> o1.getAnnotation(Test.class).priority() - o2.getAnnotation(Test.class).priority());

        System.out.printf("***%s*** \n", testedClass.getName());
        System.out.println("***Test started***");
        if (beforeSuite != null) beforeSuite.invoke(testedObject, null);
        for (Method m : methodsList) m.invoke(testedObject, null);
        if (afterSuite != null) afterSuite.invoke(testedObject, null);
        System.out.println("***Test finished***");
    }

    public void start(Object object) throws Exception {
        this.start(object.getClass());
    }

    public void start(String className) throws Exception {
        Class testedClass = Class.forName(className);
        this.start(testedClass);
    }
}
