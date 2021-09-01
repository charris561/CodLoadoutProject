/*
Author: Caleb Harris

Program Description:
This program takes an array of data and prints out a random loadout
for the user to use in Call of Duty Modern Warfare 2019
 */

import java.util.Scanner;

public class Cod_Loadout_Randomizer {

    public static void main(String[] args) {

        //boolean to allow user to create multiple classes
        boolean done = false;

        //create scanner object
        Scanner input = new Scanner(System.in);

        //checks to see if the user would like to continue
        while (!done) {

            //create objects and array for class objects
            //primary
            Primary[] primaryGuns = new Primary[45];
            createPrimaryArray(primaryGuns);

            //secondary
            Secondary[] secondaryGuns = new Secondary[13];
            createSecondaryArray(secondaryGuns);

            //secondary
            Perk[] perks = new Perk[18];
            createPerkArray(perks);

            //lethals
            Lethal[] lethals = new Lethal[8];
            createLethalArray(lethals);

            //tacticals
            Tactical[] tacticals = new Tactical[8];
            createTacticalArray(tacticals);

            //prints class
            printClass(primaryGuns, secondaryGuns, perks, lethals, tacticals);

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

    //continueComputing method gets user input and checks to see if the user would like to continue
    //creating classes
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

    /*
    Method printClass takes in the arrays of gun objects, assigns random array index of objects to instance variables,
    then prints out the class details
    */
    public static void printClass(Primary[] primary, Secondary[] secondary, Perk[] perks, Lethal[] lethals, Tactical[] tacticals) {

        //print class
        System.out.println("Here is your random loadout bois:");
        System.out.println("-----------------------------------");

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
        primaryGun = primary[randomNum(0, 44)];
        perk1 = perks[randomNum(0, 5)];
        perk2 = perks[randomNum(6, 11)];
        perk3 = perks[randomNum(12, 17)];
        lethal1 = lethals[randomNum(0, 7)];
        tactical1 = tacticals[randomNum(0, 7)];

        //		perk2 = perks[10]; //sets perk2 to overkill for testing (two primaries)

        //if overkill perk is picked, the class needs 2 primary weapons
        if (perk2.getPerk().equals("Overkill")) {

            overkill = true;

            //assigns another primary to overkillGun rather than using a secondary
            overkillGun = primary[randomNum(0, 44)];

            //if the overkill gun equals the primary, assign new primary to overkill gun to avoid duplicate
            while (overkillGun.getName().equals(primaryGun.getName())) {
                overkillGun = primary[randomNum(0, 44)];
            }

            //print class details
            System.out.printf("Primary:\n%s %d: %s\n\n", primaryGun.getType(), primaryGun.getNumber(), primaryGun.getName());
            System.out.printf("Secondary:\n%s %d: %s\n\n", overkillGun.getType(), overkillGun.getNumber(), overkillGun.getName());
            System.out.printf("Perk 1: %s\nPerk 2: %s\nPerk 3: %s\n\n", perk1.getPerk(), perk2.getPerk(), perk3.getPerk());
            System.out.printf("Lethal: %s\nTactical: %s\n\n", lethal1.getName(), tactical1.getName());
        }

        //print class without overkill. i.e. a primary and secondary
        else {
            secondaryGun = secondary[randomNum(0, 12)];

            //print class details
            System.out.printf("Primary:\n%s %d: %s\n\n", primaryGun.getType(), primaryGun.getNumber(), primaryGun.getName());
            System.out.printf("Secondary:\n%s %d: %s\n\n", secondaryGun.getType(), secondaryGun.getNumber(), secondaryGun.getName());
            System.out.printf("Perk 1: %s\nPerk 2: %s\nPerk 3: %s\n\n", perk1.getPerk(), perk2.getPerk(), perk3.getPerk());
            System.out.printf("Lethal: %s\nTactical: %s\n\n", lethal1.getName(), tactical1.getName());
        }

    }//end printClass

    /*
    randomNum method takes in the range of values then creates a random number and returns it
     */
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

    /*
    createPrimaryArray method stores all possible primary weapons in arrays
     */
    public static void createPrimaryArray(Primary[] guns) {

        //create gun objects for all guns
        //ARs
        Primary ar1 = new Primary("Kilo", 1, "Assault Rifle");
        Primary ar2 = new Primary("Fal", 2, "Assault Rifle");
        Primary ar3 = new Primary("M4A1", 3, "Assault Rifle");
        Primary ar4 = new Primary("FR 5.56", 4, "Assault Rifle");
        Primary ar5 = new Primary("Oden", 5, "Assault Rifle");
        Primary ar6 = new Primary("M13", 6, "Assault Rifle");
        Primary ar7 = new Primary("Scar", 7, "Assault Rifle");
        Primary ar8 = new Primary("AK-47", 8, "Assault Rifle");
        Primary ar9 = new Primary("Ram-7", 9, "Assault Rifle");
        Primary ar10 = new Primary("Grau 5.56", 10, "Assault Rifle");
        Primary ar11 = new Primary("CR-56 Amax", 11, "Assault Rifle");
        Primary ar12 = new Primary("AN-94", 12, "Assault Rifle");
        Primary ar13 = new Primary("AS VAL", 13, "Assault Rifle");

        //smgs
        Primary smg1 = new Primary("AUG", 1, "Sub Machine Gun");
        Primary smg2 = new Primary("P90", 2, "Sub Machine Gun");
        Primary smg3 = new Primary("MP5", 3, "Sub Machine Gun");
        Primary smg4 = new Primary("Uzi", 4, "Sub Machine Gun");
        Primary smg5 = new Primary("PP19 Bizon", 5, "Sub Machine Gun");
        Primary smg6 = new Primary("Striker 45", 6, "Sub Machine Gun");
        Primary smg7 = new Primary("Fennec", 7, "Sub Machine Gun");
        Primary smg8 = new Primary("ISO", 8, "Sub Machine Gun");

        //Shotguns
        Primary cqb1 = new Primary("Model 680", 1, "Shotgun");
        Primary cqb2 = new Primary("R9-0 Shotgun", 2, "Shotgun");
        Primary cqb3 = new Primary("725", 3, "Shotgun");
        Primary cqb4 = new Primary("Origin 12 Shotgun", 4, "Shotgun");
        Primary cqb5 = new Primary("VLK Rogue", 5, "Shotgun");
        Primary cqb6 = new Primary("JAK-12", 6, "Shotgun");

        //lmgs
        Primary lmg1 = new Primary("PKM", 1, "Light Machine Gun");
        Primary lmg2 = new Primary("SA87", 2, "Light Machine Gun");
        Primary lmg3 = new Primary("M91", 3, "Light Machine Gun");
        Primary lmg4 = new Primary("MG34", 4, "Light Machine Gun");
        Primary lmg5 = new Primary("Holger-26", 5, "Light Machine Gun");
        Primary lmg6 = new Primary("Bruen Mk9", 6, "Light Machine Gun");
        Primary lmg7 = new Primary("FiNN", 7, "Light Machine Gun");

        //marksman rifles
        Primary mrk1 = new Primary("EBR-14", 1, "Marksman Rifle");
        Primary mrk2 = new Primary("MK2 Carbine", 2, "Marksman Rifle");
        Primary mrk3 = new Primary("Kar98k", 3, "Marksman Rifle");
        Primary mrk4 = new Primary("Crossbow", 4, "Marksman Rifle");
        Primary mrk5 = new Primary("SKS", 5, "Marksman Rifle");
        Primary mrk6 = new Primary("SP-R 208", 6, "Marksman Rifle");

        //snipers
        Primary snip1 = new Primary("Dragunov", 1, "Sniper Rifle");
        Primary snip2 = new Primary("HDR", 2, "Sniper Rifle");
        Primary snip3 = new Primary("AX-50", 3, "Sniper Rifle");
        Primary snip4 = new Primary("Rytec AMR", 4, "Sniper Rifle");

        //melee
        Primary riot = new Primary("Riot Shield", 1, "Melee");

        //put guns into array
        guns[0] = ar1;
        guns[1] = ar2;
        guns[2] = ar3;
        guns[3] = ar4;
        guns[4] = ar5;
        guns[5] = ar6;
        guns[6] = ar7;
        guns[7] = ar8;
        guns[8] = ar9;
        guns[9] = ar10;
        guns[10] = ar11;
        guns[11] = ar12;
        guns[12] = ar13;

        guns[13] = smg1;
        guns[14] = smg2;
        guns[15] = smg3;
        guns[16] = smg4;
        guns[17] = smg5;
        guns[18] = smg6;
        guns[19] = smg7;
        guns[20] = smg8;

        guns[21] = cqb1;
        guns[22] = cqb2;
        guns[23] = cqb3;
        guns[24] = cqb4;
        guns[25] = cqb5;
        guns[26] = cqb6;

        guns[27] = lmg1;
        guns[28] = lmg2;
        guns[29] = lmg3;
        guns[30] = lmg4;
        guns[31] = lmg5;
        guns[32] = lmg6;
        guns[33] = lmg7;

        guns[34] = mrk1;
        guns[35] = mrk2;
        guns[36] = mrk3;
        guns[37] = mrk4;
        guns[38] = mrk5;
        guns[39] = mrk6;

        guns[40] = snip1;
        guns[41] = snip2;
        guns[42] = snip3;
        guns[43] = snip4;

        guns[44] = riot;

    }//end createPrimaryArray

    /*
    createSecondaryArray method stores all possible secondary weapons in arrays
    */
    public static void createSecondaryArray(Secondary[] guns) {

        //pistols
        Secondary pis1 = new Secondary("X16", 1, "Pistol");
        Secondary pis2 = new Secondary("1911", 2, "Pistol");
        Secondary pis3 = new Secondary(".357", 3, "Pistol");
        Secondary pis4 = new Secondary("M19", 4, "Pistol");
        Secondary pis5 = new Secondary(".50 GS", 5, "Pistol");
        Secondary pis6 = new Secondary("Renetti", 6, "Pistol");

        //lauchers
        Secondary laun1 = new Secondary("Pila", 1, "Launcher");
        Secondary laun2 = new Secondary("Strela-p", 2, "Launcher");
        Secondary laun3 = new Secondary("Jokr", 3, "Launcher");
        Secondary laun4 = new Secondary("RPG", 4, "Launcher");

        //melee
        Secondary mel1 = new Secondary("Combat Knife", 1, "Knife");
        Secondary mel2 = new Secondary("Kali Sticks", 2, "Knife");
        Secondary mel3 = new Secondary("Dual Kodachis", 3, "Knife");

        //put guns into array
        guns[0] = pis1;
        guns[1] = pis2;
        guns[2] = pis3;
        guns[3] = pis4;
        guns[4] = pis5;
        guns[5] = pis6;

        guns[6] = laun1;
        guns[7] = laun2;
        guns[8] = laun3;
        guns[9] = laun4;

        guns[10] = mel1;
        guns[11] = mel2;
        guns[12] = mel3;

    }//end createSecondaryArray

    /*
    createPerkArray method stores all possible perks in arrays
    */
    public static void createPerkArray(Perk[] perks) {

        //tier1
        Perk perk1 = new Perk("Double Time", 1);
        Perk perk2 = new Perk("Kill Chain", 1);
        Perk perk3 = new Perk("Scavenger", 1);
        Perk perk4 = new Perk("EOD", 1);
        Perk perk5 = new Perk("Cold-Blooded", 1);
        Perk perk6 = new Perk("Quick Fix", 1);

        //tier2
        Perk perk7 = new Perk("Restock", 2);
        Perk perk8 = new Perk("Hardline", 2);
        Perk perk9 = new Perk("High Alert", 2);
        Perk perk10 = new Perk("Ghost", 2);
        Perk perk11 = new Perk("Overkill", 2);
        Perk perk12 = new Perk("Pointman", 2);

        //tier3
        Perk perk13 = new Perk("Tune Up", 3);
        Perk perk14 = new Perk("Amped", 3);
        Perk perk15 = new Perk("Shrapnel", 3);
        Perk perk16 = new Perk("Battle Hardened", 3);
        Perk perk17 = new Perk("Spotter", 3);
        Perk perk18 = new Perk("Tracker", 3);

        //place perks into array
        //tier 1
        perks[0] = perk1;
        perks[1] = perk2;
        perks[2] = perk3;
        perks[3] = perk4;
        perks[4] = perk5;
        perks[5] = perk6;

        //tier 2
        perks[6] = perk7;
        perks[7] = perk8;
        perks[8] = perk9;
        perks[9] = perk10;
        perks[10] = perk11;
        perks[11] = perk12;

        //tier 3
        perks[12] = perk13;
        perks[13] = perk14;
        perks[14] = perk15;
        perks[15] = perk16;
        perks[16] = perk17;
        perks[17] = perk18;

    }//end createPerkArray

    /*
    createLethalArray method stores all possible lethal equipment in arrays
    */
    public static void createLethalArray(Lethal[] lethals) {

        //create objects
        Lethal lethal1 = new Lethal("Frag Grenade");
        Lethal lethal2 = new Lethal("Semtex");
        Lethal lethal3 = new Lethal("Molotov Cocktail");
        Lethal lethal4 = new Lethal("Throwing Knife");
        Lethal lethal5 = new Lethal("Claymore");
        Lethal lethal6 = new Lethal("Proximity Mine");
        Lethal lethal7 = new Lethal("C4");
        Lethal lethal8 = new Lethal("Thermite");

        //place into array
        lethals[0] = lethal1;
        lethals[1] = lethal2;
        lethals[2] = lethal3;
        lethals[3] = lethal4;
        lethals[4] = lethal5;
        lethals[5] = lethal6;
        lethals[6] = lethal7;
        lethals[7] = lethal8;

    }//end createLethalArray

    /*
    createTacticalArray method stores all possible tactical equipment in arrays
    */
    public static void createTacticalArray(Tactical[] tacticals) {

        //create objects
        Tactical tac1 = new Tactical("Stun Grenade");
        Tactical tac2 = new Tactical("Smoke Grenade");
        Tactical tac3 = new Tactical("Flash Grenade");
        Tactical tac4 = new Tactical("Stim");
        Tactical tac5 = new Tactical("Decoy Grenade");
        Tactical tac6 = new Tactical("Gas Grenade");
        Tactical tac7 = new Tactical("Snapshot Grenade");
        Tactical tac8 = new Tactical("Heartbeat Sensor");

        //place into array
        tacticals[0] = tac1;
        tacticals[1] = tac2;
        tacticals[2] = tac3;
        tacticals[3] = tac4;
        tacticals[4] = tac5;
        tacticals[5] = tac6;
        tacticals[6] = tac7;
        tacticals[7] = tac8;

    }//end createTacticalArray

}//end Loadout_Randomizer

/*
Primary Class defines a primary weapon and the attributes it will have
 */
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

/*
Secondary Class defines a secondary weapon and the attributes it will have
 */
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

/*
Perk Class defines a perk and the attributes it will have
 */
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

/*
Lethal Class defines a piece of lethal equipment and the attributes it will have
 */
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

/*
Tactical Class defines a piece of tactical equipment and the attributes it will have
 */
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