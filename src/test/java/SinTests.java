import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class SinTests {

    private final double DEL = 1e-5;
    private final double EPS = 1e-6;

    @Test
    public void testZero(){
        assertEquals(0.0, SinCalc.sin(0, EPS));
    }

    @Test
    public void testHalfPi(){
        assertEquals(1.0, SinCalc.sin(Math.PI / 2, EPS), DEL);
    }

    @Test
    public void testPi(){
        assertEquals(0.0, SinCalc.sin(Math.PI, EPS), DEL);
    }

    @Test
    public void testTwoPi(){
        assertEquals(0.0, SinCalc.sin(Math.PI * 2, EPS), DEL);
    }


    @Test
    public void testMinusHalfPi(){
        assertEquals(-1.0, SinCalc.sin(0 - Math.PI / 2, EPS), DEL);
    }

    @Test
    public void testMinusPi(){
        assertEquals(0.0, SinCalc.sin(0 - Math.PI, EPS), DEL);
    }


    @Test
    public void testMinusTwoPi(){
        assertEquals(0.0, SinCalc.sin(0 - Math.PI * 2, EPS), DEL);
    }

    @Test
    public void testQuarterPI(){
        assertEquals(Math.sqrt(2.0) / 2, SinCalc.sin(Math.PI / 4, EPS ), DEL);
    }

    @Test
    public void testMinusQuarterPI(){
        assertEquals(0 - Math.sqrt(2.0) / 2, SinCalc.sin(0 - Math.PI / 4, EPS ), DEL);
    }

    @Test
    public void testMinusThreeQuartersPI(){
        assertEquals(0 - Math.sqrt(2.0) / 2, SinCalc.sin(0 - 3 * Math.PI / 4, EPS ), DEL);
    }

    @Test
    public void testThreeQuartersPI(){
        assertEquals(Math.sqrt(2.0) / 2, SinCalc.sin(3 * Math.PI / 4, EPS ), DEL);
    }

    @Test
    public void testOdd() {
        Random rnd = new Random();
        double x = rnd.nextDouble() * 1000 - 500;
        assertEquals(-SinCalc.sin(x, EPS), SinCalc.sin(-x, EPS), DEL);
    }


    @Test
    public void testBig(){
        assertEquals(0, SinCalc.sin(1000 * Math.PI, EPS), DEL );
    }


    @Test
    public void testMinusBig(){
        assertEquals(0, SinCalc.sin(- 1000 * Math.PI, EPS), DEL );
    }

    @Test
    public void testSmall(){
        assertEquals(1e-10, SinCalc.sin(1e-10, EPS), DEL);
    }

    @Test
    public void TestPeriod(){
        Random rnd = new Random();
        double x = rnd.nextDouble() * 1000 - 500;
        assertEquals(SinCalc.sin(x, EPS), SinCalc.sin(x + Math.PI * 2, EPS), DEL);
    }


}
