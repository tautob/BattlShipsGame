import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner; // you must import Scanner to use it

public class BattleShipsGame {
    public static String[][] seaArray = intializeSeaArray(10, 10);
    public static int ownShipCount = 2;
    public static int computerShipCount = 2;

    public static void main(String[] args) {

        System.out.println("*** Welcome to BATTLESHIPS! ***");
        System.out.println("The sea is currently empty\n");

        printSeaToScreen();

        System.out.println();
        enterUserShipCoords(ownShipCount);

        System.out.println("\n");
        populateComputerShips(computerShipCount);

        System.out.println("\n");
        printSeaToScreen();

        playGame();
    }

    public static void playGame() {
        Scanner input = new Scanner(System.in);
        Random rand = new Random();
        int x = 0;
        int y = 0;

        while((ownShipCount > 0) & (computerShipCount > 0)) {
            System.out.print("Enter X coordinate for your attack: ");
            x = getInputFromUser();
            System.out.print("Enter Y coordinate for your attack: ");
            y = getInputFromUser();

            boolean goodCoords = false;

            while(!goodCoords) {
                if (x > seaArray.length - 1 | y > seaArray[0].length - 1) {
                    System.out.println("Coordinates out of bounds, please try again.\n");
                    System.out.print("Enter X coordinate for your attack: ");
                    x = getInputFromUser();
                    System.out.print("Enter Y coordinate for your attack: ");
                    y = getInputFromUser();
                    goodCoords = false;
                }
                else if (seaArray[y][x].equals("X") | seaArray[y][x].equals("!") | seaArray[y][x].equals("-")) {
                    System.out.println("Invalid coordinates, please try again.\n");
                    System.out.print("Enter X coordinate for your attack: ");
                    x = getInputFromUser();
                    System.out.print("Enter Y coordinate for your attack: ");
                    y = getInputFromUser();
                    goodCoords = false;
                }
                else{
                    goodCoords = true;
                }
            }

            if (seaArray[y][x].equals("1")) {
                seaArray[y][x] = "X";
                System.out.println("Oh no! You sunk your own ship!");
                ownShipCount--;
            } else if (seaArray[y][x].equals("2")) {
                seaArray[y][x] = "!";
                System.out.println("Direct hit! You sunk an enemy ship!");
                computerShipCount--;
            } else if (seaArray[y][x].equals(" ") | seaArray[y][x].equals("+")) {
                seaArray[y][x] = "-";
                System.out.println("You missed!");
            }

            System.out.print("Computer is attacking ...");
            x = rand.nextInt(seaArray.length - 1);
            y = rand.nextInt(seaArray[0].length - 1);

            while(seaArray[y][x].equals("X") | seaArray[y][x].equals("!") | seaArray[y][x].equals("-")) {
                System.out.println("Invalid coordinates, already struck this zone, please try again.");
                System.out.print("Computer is attacking ...");
                x = rand.nextInt(seaArray.length - 1);
                y = rand.nextInt(seaArray[0].length - 1);
            }
            if (seaArray[y][x].equals("1")) {
                seaArray[y][x] = "X";
                System.out.println("Direct hit! The computer struck one of your ships!");
                ownShipCount--;
            } else if (seaArray[y][x].equals("2")) {
                seaArray[y][x] = "!";
                System.out.println("Oh no! The computer has sunk it's own ship!");
                computerShipCount--;
            } else if (seaArray[y][x].equals(" ")) {
                seaArray[y][x] = "+";
                System.out.println("Computer missed!");
            }

            printSeaToScreen();
            System.out.println("Your Ships: " + ownShipCount + " Computer Ships: " + computerShipCount);
        }

        System.out.println();
        if(ownShipCount == 0){
            System.out.println("Your Ships: " + ownShipCount + " Computer Ships: " + computerShipCount);
            System.out.println("Aww, you lost :-(");
        }
        else {
            System.out.println("Your Ships: " + ownShipCount + " Computer Ships: " + computerShipCount);
            System.out.println("Hooray! You won!");
        }
    }

    public static void enterUserShipCoords(int numShips) {
        int x = 0;
        int y = 0;
        int j = 1;

        for(int i=0; i<numShips; i++) {

            System.out.print("Enter X coordinate for your # " + j + " ship: ");
            x = getInputFromUser();
            System.out.print("Enter Y coordinate for your # " + j + " ship: ");
            y = getInputFromUser();

            if(x > seaArray.length-1 | y > seaArray[0].length-1){
                System.out.println("Invalid coordinates, try again.");
                i--;
                j--;
            }
            else if(!seaArray[y][x].equals(" ")){
                System.out.println("Invalid coordinates, try again.");
                i--;
                j--;
            }
            else{
                seaArray[y][x] = "1";
            }

            j++;
        }
    }

    public static void printSeaToScreen(){

    System.out.println("   0 1 2 3 4 5 6 7 8 9  ");
        for(int row = 0; row <seaArray.length; row++){
            System.out.print(row + "| ");

            for (int col = 0; col < seaArray[row].length; col++) {
                if(seaArray[row][col].equals("1")){
                    System.out.print("@ ");
                }
                if(seaArray[row][col].equals("X")){
                    System.out.print("X ");
                }
                if(seaArray[row][col].equals("!")){
                    System.out.print("! ");
                }
                if(seaArray[row][col].equals("-")){
                    System.out.print("- ");
                }
                else if(seaArray[row][col].equals("2") | seaArray[row][col].equals(" ") | seaArray[row][col].equals("+")){
                    System.out.print("  ");
                }
            }
            System.out.print("|" + row);
            System.out.println();
        }
        System.out.println("   0 1 2 3 4 5 6 7 8 9  ");
    }

    public static String[][] intializeSeaArray(int row, int col) {
        String[][] array = new String[row][col];

        for(int r = 0; r <array.length; r++){
            for (int c = 0; c < array[r].length; c++) {
                array[r][c] = " ";
            }
        }

        return array;
    }

    public static void populateComputerShips(int numShips) {
        Random rand = new Random();
        int x = 0;
        int y = 0;
        int j = 1;

        System.out.println("Computer is deploying ships.");

        for(int i=0; i<numShips; i++) {
            x = rand.nextInt(seaArray.length-1);
            y = rand.nextInt(seaArray[0].length-1);

            if(!seaArray[y][x].equals(" ")){
                i--;
                j--;
            }
            else{
                System.out.println(j + ". ship DEPLOYED.");
                seaArray[y][x] = "2";
            }

            j++;
        }
    }

    public static int getInputFromUser(){
        Scanner input = new Scanner(System.in);
        int z = 0;

        boolean goodCoords = false;

        while(!goodCoords) {
            if (input.hasNextInt()) {
                z = input.nextInt();
//                z = dataGenerator();  //For testing
                goodCoords = true;
            } else {
                System.out.println("Invalid coordinates, try again.\n");
                goodCoords = false;
                input.next();
            }
        }

        return z;
    }


    public static Integer dataGenerator()
    {
        List<Integer> rawList = new ArrayList<Integer>();
        rawList.add(1);
        rawList.add(2);
        rawList.add(3);
        rawList.add(4);
        rawList.add(5);
        rawList.add(6);
        rawList.add(7);
        rawList.add(8);
        rawList.add(9);
        rawList.add(0);

        Random rand = new Random();
        return rawList.get(rand.nextInt(rawList.size()));

    }
}