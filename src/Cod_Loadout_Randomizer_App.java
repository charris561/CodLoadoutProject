/*
Author: Caleb Harris

Program Description:
This program takes an array of data and prints out a random loadout
for the user to use in Call of Duty Modern Warfare 2019
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.io.*;
import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.util.List;

public class Cod_Loadout_Randomizer_App extends JFrame implements ActionListener{

    /*
    GUI setup
    */
    static JButton createLoadoutButton = new JButton("Create Loadout");
    static JFrame frame = new JFrame("Modern Warfare Loadout Randomizer");
    static JLabel label;

    public static void main(String[] args) throws IOException{

        new Cod_Loadout_Randomizer_App();

        //store file names as constants
        final String PRIMARIES_INPUT_FILE = "PrimaryWeapons.csv";
        final String SECONDARIES_INPUT_FILE = "SecondaryWeapons.csv";
        final String PERKS_INPUT_FILE = "Perks.csv";
        final String LETHALS_INPUT_FILE = "Lethals.csv";
        final String TACTICALS_INPUT_FILE = "Tacticals.csv";

        //boolean to allow user to create multiple classes
        boolean done = false;

        //create scanner object
        Scanner input = new Scanner(System.in);

        //checks to see if the user would like to continue
        while (!done) {

            //create objects and array for class objects
            //primary
            ArrayList<Primary> primaryGuns = new ArrayList<>();
            createPrimaries(primaryGuns, PRIMARIES_INPUT_FILE);

            //secondary
            ArrayList<Secondary> secondaryGuns = new ArrayList<>();
            createSecondaries(secondaryGuns, SECONDARIES_INPUT_FILE);

            //secondary
            ArrayList<Perk> perks = new ArrayList<>();
            createPerks(perks, PERKS_INPUT_FILE);

            //lethals
            ArrayList<Lethal> lethals = new ArrayList<>();
            createLethals(lethals, LETHALS_INPUT_FILE);

            //tacticals
            ArrayList<Tactical> tacticals = new ArrayList<>();
            createTacticals(tacticals, TACTICALS_INPUT_FILE);

            //prints class
            printLoadout(primaryGuns, secondaryGuns, perks, lethals, tacticals);

            //prompt user to see if they want another class
            boolean continueComputing = continueComputing(input);

            //if they don't want to continue
            if (!continueComputing) {
                done = true;
                System.out.println("\nHave fun my dood\n");
            }

            //if they do want to continue
            else {
                System.out.println("****************************************\n");
            }

        }//end while

    }//end main

    //finds how many rows are in file passed in
    public static int findRows(String inputFileName) throws IOException{

        int numRows = 0; //stores number of rows

        //create file and scanner objects
        File file = new File(inputFileName);
        Scanner inputFile = new Scanner(file);

        //while has next line, go to next line and iterate count
        while (inputFile.hasNextLine()){
            numRows++;
            inputFile.nextLine();
        }

        //close input file
        inputFile.close();

        return numRows;

    }//findRows

    //sees if the user would like to create another class or not
    public static boolean continueComputing(Scanner input) {
        //variable used to track if they want to continue or not
        boolean userInput = false;

        System.out.println("****************************************");
        System.out.println("Would you like to create another class (y/n)");
        char userChoice = input.next().charAt(0); //used to read in user choice from input

        //checks for valid user input
        while (userChoice != 'y' && userChoice != 'Y' && userChoice != 'n' && userChoice != 'N') {
            System.out.println("Please enter a valid input (y/n)");
            userChoice = input.next().charAt(0);
        }

        //if yes
        if (userChoice == 'y' || userChoice == 'Y') {
            userInput = true;
        }

        return userInput;

    } // end continueComputing

    //prints loadout details
    public static void printLoadout(ArrayList<Primary> primary, ArrayList<Secondary> secondary, ArrayList<Perk> perks, ArrayList<Lethal> lethals, ArrayList<Tactical> tacticals) {

        JLabel header = new JLabel("Here is your loadout my bois");
        JPanel loadoutPanel = new JPanel();
        loadoutPanel.add(header);
        frame.add(loadoutPanel);

        //set instance variables then assign object when applicable
        boolean overkill = false;
        Primary primaryGun = null;
        Secondary secondaryGun = null;
        Primary overkillGun = null;
        Perk perk1 = null;
        Perk perk2 = null;
        Perk perk3 = null;
        Lethal lethal1 = null;
        Tactical tactical1 = null;

        //assign object based on random number generated for array index then stores in instance variables
        primaryGun = primary.get(randomNum(0, 46));
        perk1 = perks.get(randomNum(0, 5));
        perk2 = perks.get(randomNum(6, 11));
        perk3 = perks.get(randomNum(12, 17));
        lethal1 = lethals.get(randomNum(0, 7));
        tactical1 = tacticals.get(randomNum(0, 7));

        //		perk2 = perks[10]; //sets perk2 to overkill for testing (two primaries)

        //if overkill perk is picked, the class needs 2 primary weapons
        if (perk2.getPerk().equals("Overkill")) {

            overkill = true;

            //assigns another primary to overkillGun rather than using a secondary
            overkillGun = primary.get(randomNum(0, 46));

            //if the overkill gun equals the primary, assign new primary to overkill gun to avoid duplicate
            while (overkillGun.getName().equals(primaryGun.getName())) {
                overkillGun = primary.get(randomNum(0, 46));
            }

            //print class details
            System.out.printf("Primary:\n%s %d: %s\n\n", primaryGun.getType(), primaryGun.getNumber(), primaryGun.getName());
            System.out.printf("Secondary:\n%s %d: %s\n\n", overkillGun.getType(), overkillGun.getNumber(), overkillGun.getName());
            System.out.printf("Perk 1: %s\nPerk 2: %s\nPerk 3: %s\n\n", perk1.getPerk(), perk2.getPerk(), perk3.getPerk());
            System.out.printf("Lethal: %s\nTactical: %s\n\n", lethal1.getName(), tactical1.getName());
        }

        //print class without overkill. i.e. a primary and secondary
        else {
            secondaryGun = secondary.get(randomNum(0, 12));

            //print class details
            System.out.printf("Primary:\n%s %d: %s\n\n", primaryGun.getType(), primaryGun.getNumber(), primaryGun.getName());
            System.out.printf("Secondary:\n%s %d: %s\n\n", secondaryGun.getType(), secondaryGun.getNumber(), secondaryGun.getName());
            System.out.printf("Perk 1: %s\nPerk 2: %s\nPerk 3: %s\n\n", perk1.getPerk(), perk2.getPerk(), perk3.getPerk());
            System.out.printf("Lethal: %s\nTactical: %s\n\n", lethal1.getName(), tactical1.getName());
        }

    }//end printClass

    //returns a random number between min and max
    public static int randomNum(int min, int max) {
        // define the range
        int range = max - min + 1;
        int rand = (int) (Math.random() * range) + min;

//        debugging statement used to test if rand is creating all values -> store testing value in rangeTest variable
//                int rangeTest = 0;

//                while (rand != rangeTest) {
//                	System.out.println(rand);
//                	rand = (int)(Math.random() * range) + min;
//                }
//
//                System.out.println("rand: " + rand);

        return rand;
    }// end randomNum

    //creates primary objects and stores them in array list
    public static void createPrimaries(ArrayList<Primary> guns, String inputFileName) throws IOException{

        //create scanner and file object
        File file = new File(inputFileName);
        Scanner inputFile = new Scanner(file);

        int numPrimaries = findRows(inputFileName); //stores the number of primary guns

        //make scanner use comma as delimiter
        inputFile.useDelimiter(",");

        //create ARs and put them in arraylist
        for (int i = 0; i < numPrimaries; i++){
            String name = inputFile.next();
            int number = inputFile.nextInt();
            String type = inputFile.next();
            inputFile.nextLine();

            Primary primary = new Primary(name, number, type);
            guns.add(primary);
        }

        //close file
        inputFile.close();

    }//end createPrimaryArray

    //create secondary objects and stores them in array list
    public static void createSecondaries(ArrayList<Secondary> guns, String inputFileName) throws IOException{

        //create scanner and file object
        File file = new File(inputFileName);
        Scanner inputFile = new Scanner(file);

        int numSecondaries = findRows(inputFileName); //stores the number of primary guns

        //make scanner use comma as delimiter
        inputFile.useDelimiter(",");

        //create ARs and put them in arraylist
        for (int i = 0; i < numSecondaries; i++){

            String name = inputFile.next();
            int number = inputFile.nextInt();
            String type = inputFile.next();
            inputFile.nextLine();

            Secondary secondary = new Secondary(name, number, type);
            guns.add(secondary);
        }

        //close file
        inputFile.close();

    }//end createSecondaryArray

    //Create perks and stores them in array list
    public static void createPerks(ArrayList<Perk> perks, String inputFileName) throws IOException {

        //create scanner and file object
        File file = new File(inputFileName);
        Scanner inputFile = new Scanner(file);

        int numPerks = findRows(inputFileName); //stores the number of primary guns

        //make scanner use comma as delimiter
        inputFile.useDelimiter(",");

        //create ARs and put them in arraylist
        for (int i = 0; i < numPerks; i++){
            Perk perk = new Perk(inputFile.next(), inputFile.nextInt());
            perks.add(perk);
        }

        //close file
        inputFile.close();

    }//end createPerkArray

    //create lethals and store them in array list
    public static void createLethals(ArrayList<Lethal> lethals, String inputFileName) throws IOException {

        //create scanner and file object
        File file = new File(inputFileName);
        Scanner inputFile = new Scanner(file);

        int numLethals = findRows(inputFileName); //stores the number of primary guns

        //create ARs and put them in arraylist
        for (int i = 0; i < numLethals; i++){
            Lethal lethal = new Lethal(inputFile.nextLine());
            lethals.add(lethal);
        }

        //close file
        inputFile.close();

    }//end createLethalArray

    //create tacticals and store them in array list
    public static void createTacticals(ArrayList<Tactical> tacticals, String inputFileName) throws IOException {

        //create scanner and file object
        File file = new File(inputFileName);
        Scanner inputFile = new Scanner(file);

        int numTacticals = findRows(inputFileName); //stores the number of primary guns

        //create ARs and put them in arraylist
        for (int i = 0; i < numTacticals; i++){
            Tactical tactical = new Tactical(inputFile.nextLine());
            tacticals.add(tactical);
        }

        //close file
        inputFile.close();

    }//end createTacticalArray


    /*
    More GUI stuff
     */
    public Cod_Loadout_Randomizer_App(){

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        createLoadoutButton.addActionListener(this);
        frame.setLayout(new FlowLayout());
        frame.add(createLoadoutButton);
        frame.setVisible(true);

    }//Cod loadout randomizer gui

    @Override
    public void actionPerformed(ActionEvent e) {

        label = new JLabel();
        label.setText("Here is your loadout my bois");

        JPanel panel = new JPanel();

        panel.add(label);

        frame.add(panel);

    }//actionPerformed

}//end Loadout_Randomizer

//defines a primary weapon
class Primary {

    //private data fields
    private String name;
    private int number;
    private String type;

    /*
     * public methods
     */
    //constructor
    public Primary(String name, int number, String type) {
        this.name = name;
        this.number = number;
        this.type = type;
    }// end constructor

    //getters
    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public String getType() {
        return type;
    }

}//end Primary

//defines a secondary weapon
class Secondary {

    //private data fields
    private String name;
    private int number;
    private String type;

    //constructor
    public Secondary(String name, int number, String type) {
        this.name = name;
        this.number = number;
        this.type = type;
    }// end constructor

    //getters
    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public String getType() {
        return type;
    }

}//end secondary

//defines a perk
class Perk {

    //private data fields
    private String name;
    private int tier;

    //constructor
    public Perk(String name, int tier) {
        this.name = name;
        this.tier = tier;
    }// end constructor

    //getter
    public String getPerk() {
        return name;
    }

    public int getTier() {
        return tier;
    }

}//end Perk

//defines lethal equipment
class Lethal {

    //private data fields
    private String name;

    //constructor
    public Lethal(String name) {
        this.name = name;
    }

    //getter
    public String getName() {
        return name;
    }
}//end Lethal

//defines tactical equipment
class Tactical {

    //private data fields
    private String name;

    //constructor
    public Tactical(String name) {
        this.name = name;
    }

    //getter
    public String getName() {
        return name;
    }

}//end tactical