import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BattleShipsGameTest {

    @BeforeMethod
    public void setUp() {

    }

    @AfterMethod
    public void tearDown() {
    }

    @Test
    public void testMain() {
        BattleShipsGame.main(new String[]{"5"});
    }

    @Test
    public void testPlayGame() {
    }

    @Test
    public void testEnterUserShipCoords() {
    }

    @Test
    public void testPrintSeaToScreen() {
    }

    @Test
    public void testIntializeSeaArray() {
    }

    @Test
    public void testPopulateComputerShips() {
    }
}