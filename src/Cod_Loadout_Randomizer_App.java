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

public class Cod_Loadout_Randomizer_App{

    public static void main(String[] args) throws IOException{

        Loadout loadout = new Loadout();

        ImageIcon image = new ImageIcon("memez.jpg"); //create an ImageIcon for image
        JLabel label = new JLabel(); //create label

        //set text for label
        if (loadout.getOverKillSecondary() == null) {
            label.setText(String.format("<html>Cod Weapon Loadout Randomizer:<BR><BR>" +
                    "Primary: %s<BR>" +
                    "Secondary: %s<BR><BR>" +
                    "Perks:<BR>1: %s<BR>2: %s<BR>3: %s<BR><BR>" +
                    "Lethal: %s<BR>Tactical: %s", loadout.getPrimary().getName(),
                    loadout.getSecondary().getName(),
                    loadout.getTierOnePerk().getPerk(),
                    loadout.getTierTwoPerk().getPerk(),
                    loadout.getTierThreePerk().getPerk(),
                    loadout.getLethal().getName(),
                    loadout.getTactical().getName()));
        }
        else{
            label.setText(String.format("<html>Cod Weapon Loadout Randomizer:<BR><BR>" +
                            "Primary: %s<BR>" +
                            "Secondary: %s<BR><BR>" +
                            "Perks:<BR>1: %s<BR>2: %s<BR>3: %s<BR><BR>" +
                            "Lethal: %s<BR>Tactical: %s", loadout.getPrimary().getName(),
                    loadout.getOverKillSecondary().getName(),
                    loadout.getTierOnePerk().getPerk(),
                    loadout.getTierTwoPerk().getPerk(),
                    loadout.getTierThreePerk().getPerk(),
                    loadout.getLethal().getName(),
                    loadout.getTactical().getName()));
        }

        label.setFont(new Font("MV Boli", Font.PLAIN, 20));
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.add(label);

    }//end main

}//end Loadout_Randomizer

//defines a class
class Loadout {

    //private data fields
    private Primary primary;
    private Primary overKillSecondary;
    private Secondary secondary;
    private Perk tierOnePerk;
    private Perk tierTwoPerk;
    private Perk tierThreePerk;
    private Lethal lethal;
    private Tactical tactical;

    //constructor
    public Loadout() throws IOException {

        //creates a class
        createClass();

    }//Loadout

    //creates a class
    private void createClass() throws IOException {

        //store file names as constants
        final String PRIMARIES_INPUT_FILE = "PrimaryWeapons.csv";
        final String SECONDARIES_INPUT_FILE = "SecondaryWeapons.csv";
        final String PERKS_INPUT_FILE = "Perks.csv";
        final String LETHALS_INPUT_FILE = "Lethals.csv";
        final String TACTICALS_INPUT_FILE = "Tacticals.csv";

        //define number of tier 1, 2, and 3 perks
        int tierOneNum = findRows(PERKS_INPUT_FILE) / 3;
        int tierTwoNum = tierOneNum * 2;
        int tierThreeNum = findRows(PERKS_INPUT_FILE) - 1;

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

        //select values for instance variables
        this.primary = primaryGuns.get(randomNum(0, primaryGuns.size() - 1));
        this.tierOnePerk = perks.get(randomNum(0 , tierOneNum));
        this.tierTwoPerk = perks.get(randomNum(tierOneNum + 1 , tierTwoNum));
        this.tierThreePerk = perks.get(randomNum(tierTwoNum + 1, tierThreeNum));
        this.lethal = lethals.get(randomNum(0, lethals.size() - 1));
        this.tactical = tacticals.get(randomNum(0, tacticals.size() - 1));

        //if overkill is picked, the class needs 2 primaries
        if (tierTwoPerk.getPerk().equals("Overkill")){

            this.overKillSecondary = primaryGuns.get(randomNum(0 , primaryGuns.size() - 1));

            //if overkill gun == primary, get new overkill gun
            while (overKillSecondary.getName().equals(primary.getName())){
                overKillSecondary = primaryGuns.get(randomNum(0, primaryGuns.size() - 1));
            }

        }

        //if no overkill, get a secondary weapon
        else{
            secondary = secondaryGuns.get(randomNum(0, secondaryGuns.size() - 1));
        }

    }//createClass

    //returns a random number between min and max
    private int randomNum(int min, int max) {
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
    private void createPrimaries(ArrayList<Primary> guns, String inputFileName) throws IOException{

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
    private void createSecondaries(ArrayList<Secondary> guns, String inputFileName) throws IOException{

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
    private void createPerks(ArrayList<Perk> perks, String inputFileName) throws IOException {

        //create scanner and file object
        File file = new File(inputFileName);
        Scanner inputFile = new Scanner(file);

        int numPerks = findRows(inputFileName); //stores the number of primary guns

        //make scanner use comma as delimiter
        inputFile.useDelimiter(",");

        //create ARs and put them in arraylist
        for (int i = 0; i < numPerks; i++){
            String name = inputFile.next();
            name = name.replaceAll("(\\r|\\n)", "");
            int tier = inputFile.nextInt();
            Perk perk = new Perk(name, tier);
            perks.add(perk);
        }

        //close file
        inputFile.close();

    }//end createPerkArray

    //create lethals and store them in array list
    private void createLethals(ArrayList<Lethal> lethals, String inputFileName) throws IOException {

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
    private void createTacticals(ArrayList<Tactical> tacticals, String inputFileName) throws IOException {

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

    //finds how many rows are in file passed in
    private int findRows(String inputFileName) throws IOException{

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

    //getters
    public Primary getPrimary() {
        return primary;
    }

    public Primary getOverKillSecondary() {
        return overKillSecondary;
    }

    public Secondary getSecondary() {
        return secondary;
    }

    public Perk getTierOnePerk() {
        return tierOnePerk;
    }

    public Perk getTierTwoPerk() {
        return tierTwoPerk;
    }

    public Perk getTierThreePerk() {
        return tierThreePerk;
    }

    public Lethal getLethal() {
        return lethal;
    }

    public Tactical getTactical() {
        return tactical;
    }

    //overwrite toString
    @Override
    public String toString(){

        String loadout;

        if (overKillSecondary == null) {
            loadout = String.format("""
                            Primary: %s
                            Secondary: %s
                            
                            Perks:
                            1: %s
                            2: %s
                            3: %S
                            
                            Lethal: %s
                            Tactical: %s
                            
                            """,
                    primary.getName(), secondary.getName(), tierOnePerk.getPerk(),
                    tierTwoPerk.getPerk(), tierThreePerk.getPerk(),
                    lethal.getName(), tactical.getName());
        }
        else{
            loadout = String.format("""
                            Primary: %s
                            Secondary: %s
                            
                            Perks:
                            1: %s
                            2: %s
                            3: %S
                            
                            Lethal: %s
                            Tactical: %s
                            
                            """,
                    primary.getName(), overKillSecondary.getName(), tierOnePerk.getPerk(),
                    tierTwoPerk.getPerk(), tierThreePerk.getPerk(),
                    lethal.getName(), tactical.getName());
        }

        return loadout;
    }

}//Loadout

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