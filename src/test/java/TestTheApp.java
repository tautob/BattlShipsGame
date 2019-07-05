import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestTheApp {

    private ByteArrayOutputStream byteArrayOutputStream;
    private PrintStream console;

    @BeforeMethod
    public void setup() {
        byteArrayOutputStream = new ByteArrayOutputStream();
        console = System.out;
    }

    @Test
    public void testMain() throws IOException {
        String[] args = null;

        ByteArrayOutputStream bOutput = new ByteArrayOutputStream(1000000);

        bOutput.write(listGenerator(100).getBytes());

        final InputStream original = System.in;
//        final FileInputStream fips = new FileInputStream(new File("C:\\repos\\battleship\\src\\test\\data.txt"));
        final InputStream fips = new ByteArrayInputStream(bOutput.toByteArray());

        System.setIn(fips);
        BattleShipsGame.main(args);
        System.setIn(original);
    }

    public static String listGenerator(int numPointPairs)
    {
        List<String> rawList = new ArrayList<String>();
        rawList.add("1\r\n");
        rawList.add("2\r\n");
        rawList.add("3\r\n");
        rawList.add("4\r\n");
        rawList.add("5\r\n");
        rawList.add("6\r\n");
        rawList.add("7\r\n");
        rawList.add("8\r\n");
        rawList.add("9\r\n");
        rawList.add("0\r\n");

        Random rand = new Random();
        String finalString = "";

        for(int i=0; i<numPointPairs*2; i++) {
            finalString = finalString + rawList.get(rand.nextInt(rawList.size()));
        }

        System.out.println("finalString:\n" + finalString);
        return finalString;
    }
}