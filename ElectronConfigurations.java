import java.util.Scanner;
/**
 * Condensed Electron Configuration Generator
 * 
 * This program will write the condesned electron configuration in the full
 *      notation for any atom given it's atomic number and period
 * 
 * @author Nadeen
 */

public class ElectronConfigurations {  
  public static void main(String[] args) {
    int atomicNumber = 0;
    
    atomicNumber = getAtomNum();
    printEConfig(atomicNumber);
  }

  // getAtomNum() prompts the user to enter the atomic number of their choice
  public static int getAtomNum () {
    Scanner input = new Scanner (System.in);
    int atomNum = 0;

    System.out.println ("Electron Configuration Generator");
    System.out.println ("\nThe program will write the full electron " + 
                        "configuration for any atom \n on the periodic table," +
                        "given it's atomic number and period.");
    System.out.println ("\nPress enter to continue");
    input.nextLine ();

    // Gathering user input
    System.out.println ("\nWhat is the atomic number of the atom you would " +
                        "like to write \na condensed electron configuration " +
                        "for? (a positive whole number)");
    atomNum = input.nextInt ();
    input.close();
    return atomNum;
  }

  // getPeriod(atomicNumber) returns the period of the element with atomicNumber
  public static int getPeriod (int atomicNumber) {
    int period = 0;
    // Calculating the period based on the atomic number
    if (atomicNumber <= 2) {
      period = 1;
    } else if (atomicNumber <= 10) {
      period = 2;
    } else if (atomicNumber <= 18) {
      period = 3;
    } else if (atomicNumber <= 36) {
      period = 4;
    } else if (atomicNumber <= 54) {
      period = 5;
    } else if (atomicNumber <= 86) {
      period = 6;
    } else {
      period = 7;
    }
    return period;
  }
}

// fillOrbital(electrons, atomNum, period, orbital, electronConfig) updates
  //    electronConfig by adding to the specified orbital, filled with the 
  //    appropriate number of electrons based on the atomNum
  public static String fillOrbital (int [] electrons, int atomNum, int period, 
                                    String orbital, String electronConfig) {
    int electronsInOrbital = 0;

    // default values for the s orbital (smallest orbital)
    int periodAdjustment = 0;
    int maxElectronsInOrbital = 2; 

    // Updating variables for different orbitals
    if (orbital == "p") {
      maxElectronsInOrbital = 6;
    } else if (orbital == "d") {
      maxElectronsInOrbital = 10;
      periodAdjustment = 1;
    } else if (orbital == "f") {
      maxElectronsInOrbital = 14;
      periodAdjustment = 2;
    }

    // Updating # of electrons in the orbital, and total # of electrons
    while (electronsInOrbital < maxElectronsInOrbital && electrons[0] <= atomNum)  
    {
      electronsInOrbital ++;
      (electrons[0])++;
    }

    // If orbital is empty, we do not modify electronConfig
    if (electronsInOrbital > 0)
    {
      electronConfig += (period - periodAdjustment) + orbital + "^" + 
                        electronsInOrbital + " ";
    }
    return electronConfig;
  }

  // printEConfig(atomNum) will calculate and print the electron configuration
  //    for the users chosen atom with atomic number atomNum
  static void printEConfig(int atomNum) {
    // array used to limit and keep track of # of electrons added into orbitals
    int [] totElectrons = new int [1]; 
    totElectrons[0] = 1;
    int period = getPeriod(atomNum);;
    String eConfig = "";

    //Processing
    for (int curPeriod = 1; curPeriod <= period; curPeriod++) {
      eConfig = fillOrbital (totElectrons, atomNum, curPeriod, "s", eConfig);
      // dealing with the special case of the Copper or Chromium atom where the
      //    4s orbital is only half filled
      if ((atomNum == 29 || atomNum == 24) && curPeriod == 4) {
        eConfig = eConfig.substring(0, eConfig.length() - 2);
        eConfig += 1 + " ";
        totElectrons [0] --;
      }
      if (curPeriod >= 6) { // fill f orbital
        eConfig = fillOrbital (totElectrons, atomNum, curPeriod, "f", eConfig);
      }

      if (curPeriod >= 4) { // fill d orbital
        eConfig = fillOrbital (totElectrons, atomNum, curPeriod, "d", eConfig);
      }
      
      if (curPeriod >= 2) { // fill p orbital
        eConfig = fillOrbital (totElectrons, atomNum, curPeriod, "p", eConfig);
      }
    }

    System.out.println ("\nThe electron configuration for atom #" + atomNum
                        + " is... ");
    System.out.println (eConfig);
  }
