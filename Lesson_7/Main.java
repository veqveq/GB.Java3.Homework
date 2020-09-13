public class Main {
    public static void main(String[] args) throws Exception {
        TestedClass testedObject = new TestedClass();
        new Tester().start(testedObject);
        System.out.println();
        new Tester().start(testedObject.getClass().getName());
    }
}
