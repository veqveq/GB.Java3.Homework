public class TestedClass {
    @BeforeSuite
    public void shouldBeforeSuite(){
        System.out.println("Run test before suite");
    }

    @AfterSuite
    public void shouldAfterSuite(){
        System.out.println("Run test after suite");
    }

    @Test(priority = 1)
    public void shouldTest1(){
        System.out.println("Run test priority 1");
    }

    @Test(priority = 2)
    public void shouldTest2(){
        System.out.println("Run test priority 2");
    }
    @Test(priority = 3)
    public void shouldTest3(){
        System.out.println("Run test priority 3");
    }
    @Test(priority = 4)
    public void shouldTest4(){
        System.out.println("Run test priority 4");
    }
    @Test(priority = 5)
    public void shouldTest5(){
        System.out.println("Run test priority 5");
    }
    @Test(priority = 6)
    public void shouldTest6(){
        System.out.println("Run test priority 6");
    }

    @Test(priority = 7)
    public void shouldTest7(){
        System.out.println("Run test priority 7");
    }

    @Test(priority = 8)
    public void shouldTest8(){
        System.out.println("Run test priority 8");
    }

    @Test(priority = 9)
    public void shouldTest9(){
        System.out.println("Run test priority 9");
    }

    @Test(priority = 10)
    public void shouldTest10(){
        System.out.println("Run test priority 10");
    }

    @Test
    public void shouldTest11(){
        System.out.println("Run test default priority");
    }

    @Test(priority = 7)
    public void shouldTest12(){
        System.out.println("Run test priority 7");
    }

    @Test(priority = 10)
    public void shouldTest13(){
        System.out.println("Run test priority 10");
    }

    public void shouldTest14(){
        System.out.println("Test for this method never running");
    }
}
