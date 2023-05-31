import org.testng.annotations.*;

public class Anotations {

    @BeforeMethod //Pokreće se jednom pre SVAKOG testa
    public void beforeMethod(){
        System.out.println("Before Method");
    }
    @BeforeClass //Pokreće se jednom pre SVIH testova
    public void beforeClass(){
        System.out.println("Before Class");
    }
    @AfterMethod //Pokreće se jednom posle SVAKOG testa
    public void afterMethod(){
        System.out.println("After Method");
    }
    @AfterClass //Pokreće se jednom posle SVIH testova
    public void afterClass(){
        System.out.println("After class");
    }
    @Test
    public void Test1(){
        System.out.println("Test1");
    }
    @Test
    public void Test2(){
        System.out.println("Test2");
    }
    @Test
    public void Test3(){
        System.out.println("Test3");
    }
}
