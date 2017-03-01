package edu.brcc.maxfieldj.rpncalcandroid;


import edu.brcc.maxfieldj.rpncalcmc.RPNCalc;

import java.util.*;

/**
 * this class has the logic handling all of the calculator keys. no GUI code
 * moves logic specific to GUI away from the actual GUI code
 * 2015-02-24
 *
 * @author John Maxfield
 * @author Ryan Hurst
 * @author Nick Rhodes
 */
public class RPNCalcGUIHelper {
   // the string that is returned for the user to see
   private StringBuilder display;

   private boolean isNumberEntryFinished;   // current num is completely entered
   private boolean negPi = false;           // used primarily for negative pi
   private RPNCalc calc = new RPNCalc();    // MVC controller object

   /**
    * no-arg constructor. initialize display attributes
    */
   public RPNCalcGUIHelper() {
      // clears display
      setDisplay("");
      // number entry is finished
      isNumberEntryFinished = true;
   }

   /**
    * set/reset the string to be displayed
    */
   private String setDisplay(String init) {
      if (init == null)
         init = "";
      display = new StringBuilder(init);
      return display.toString();
   }

   /**
    * do all the work
    *
    * @param key a key pressed by the user: 0-9,+/-,<
    * @return the current number as a string
    */
   public String addKey(String key) {
      double num;                  // the double version of the current number

      switch (key) {
         case ".":  // only one . in a number!
            if (isNumberEntryFinished) {
               setDisplay(".");
               isNumberEntryFinished = false;
               break;
            }
            if (display.toString().contains("."))
               break;
            // else, fall through and add to display string

         case "0": //TODO consider using default for these (with comment)
         case "1":
         case "2":
         case "3":
         case "4":
         case "5":
         case "6":
         case "7":
         case "8":
         case "9":
            if (isNumberEntryFinished)
               setDisplay("");
            isNumberEntryFinished = false;
            display.append(key);
            break;

         case "C":
            setDisplay("");
            break;

         case "+/-":
            if (isNumberEntryFinished)
               setDisplay("-");
            else {
               if (display.toString().charAt(0) == '-')
                  display.deleteCharAt(0);
               else
                  display.insert(0, '-');
            }
            isNumberEntryFinished = false;
            break;

         case "<":
            if (!isNumberEntryFinished)
               display.deleteCharAt(display.length() - 1);
            break;

         case "^":
            try         // e.g. "-" is invalid
            {

               num = Double.parseDouble(display.toString());
               calc.enterNumber(num);
               setDisplay("" + num);

               isNumberEntryFinished = true;
            } catch (Exception e) {
               // if it isn't a number, ignore the '^'
            }
            break;

         case "pi":
            if (!(isNumberEntryFinished || display.toString().equals("-"))) {
               num = Double.parseDouble(display.toString());
               calc.enterNumber(num);
            } else if (!isNumberEntryFinished && display.toString().equals("-")) {
               setDisplay("-" + Math.PI);
               calc.enterNumber(-Math.PI);
               negPi = true;
            }
            if (!negPi) {
               setDisplay("" + Math.PI);
               calc.enterNumber(Math.PI);
            }
            negPi = false;
            isNumberEntryFinished = true;
            break;


         case "+":
            try {

               addOperator();
               setDisplay("" + calc.add());

                /*
                 * this usually occurs when the stack is empty or an illegal
                 * String/CharacterSequence is entered by itself, e.g. "-", ".", "-."
                 */
            } catch (NumberFormatException e) {
            }
            break;

         case "-":
            try {

               addOperator();
               setDisplay("" + calc.subtract());
                /*
                 * this usually occurs when the stack is empty or an illegal
                 * String/CharacterSequence is entered by itself, e.g. "-", ".", "-."
                 */
            } catch (NumberFormatException e) {
            }
            break;

         case "*":
            try {

               addOperator();
               setDisplay("" + calc.multiply());

                /*
                 * this usually occurs when the stack is empty or an illegal
                 * String/CharacterSequence is entered by itself, e.g. "-", ".", "-."
                 */
            } catch (NumberFormatException e) {
            }
            break;

         case "/":
            try {

               addOperator();
               setDisplay("" + calc.divide());

                /*
                 * this usually occurs when the stack is empty or an illegal
                 * String/CharacterSequence is entered by itself, e.g. "-", ".", "-."
                 */
            } catch (NumberFormatException e) {
            }
            break;

      }

      return display.toString();
   }

   /**
    * some code common to all the operators in addKey
    */
   private void addOperator() //TODO rename - this isn't about adding
   {
      double num;                  // the double version of the current number

      if (!isNumberEntryFinished) {
         num = Double.parseDouble(display.toString());
         calc.enterNumber(num);
      }
      isNumberEntryFinished = true;

   }
}