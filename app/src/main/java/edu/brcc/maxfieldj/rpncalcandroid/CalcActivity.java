package edu.brcc.maxfieldj.rpncalcandroid;

import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import edu.brcc.maxfieldj.rpncalcandroid.R;

import java.util.*;

/**
 * generates the GUI for an RPNCalculator
 * that is compatible with an Android device
 * 2015-02-24
 *
 * @author Ryan Hurst
 * @author Nick Rhodes
 */
public class CalcActivity extends AppCompatActivity {
   // used to hold the value(s) in the text editor (display)
   private CharSequence mHoldDisplay;
   // used to hold the Stack used in RPNCalcGUIHelper
   // Stack is set as an ArrayList in order to transfer values efficiently
   private ArrayList mHoldStack;
   // used to hold the boolean value of "isNumberEntryFinished" from RPNCalcGUIHelper
   private boolean mHoldEnter;

   // helper class which gives our buttons actions
   private RPNCalcGUIHelper mHelper = new RPNCalcGUIHelper();
   // used to display the text on a text bar
   private TextView mScreenText;

   // an array to hold our 20 buttons
   private Button[] mButtons = new Button[20];

   // array that holds the reference id of each button of the calculator
   private final Integer[] mReferences = new Integer[]{
       R.id.zero_button, R.id.one_button, R.id.two_button, R.id.three_button, R.id.four_button,
       R.id.five_button, R.id.six_button, R.id.seven_button, R.id.eight_button,
       R.id.nine_button, R.id.pi_button, R.id.divide_button, R.id.multiply_button,
       R.id.add_button, R.id.subtract_button, R.id.decimal_button,
       R.id.posneg_button, R.id.clear_button, R.id.delete_button, R.id.enter_button
   };

   // an array that contains the String of each possible number/operation
   private final String[] mButtonNames = {"0", "1", "2", "3", "4", "5", "6", "7",
       "8", "9", "pi", "/", "*", "+", "-", ".", "+/-", "C", "<", "^"};


   /**
    * creates our Android calculator
    * @param savedInstanceState save state that restores/holds saved data
    */
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      // execute the application
      super.onCreate(savedInstanceState);
      // display the layout for the application
      setContentView(R.layout.activity_calc);

      // used to display text for our calculator
      mScreenText = (TextView) findViewById(R.id.editText);


      // creates a button for each value present on the RPN Calculator
      for (int i = 0; i < mButtons.length; i++) {
         // casts each reference variable to a Button,
         // then sets that value to its respective element in the mButtons array
         mButtons[i] = (Button) findViewById(mReferences[i]);
      }

      // gives each button an action
      for (int i = 0; i < mButtons.length; i++) {
         // index's through the array of buttons
         final int finalI = i;
         // set an onclick listener for each of the buttons
         mButtons[i].setOnClickListener(new View.OnClickListener() {
            // when one of the buttons are clicked
            @Override
            public void onClick(View view) {
               try {
                  // set the text according to the user's input (through buttons)
                  mScreenText.setText(mHelper.addKey(mButtonNames[finalI]));
                  // hold the current value of the display screen
                  mHoldDisplay = mScreenText.getText();
                    /*
                     * used to catch the exception that occurs when
                     * the user attempts to delete a character from
                     * an empty String, i.e. "<"
                     */
               } catch (StringIndexOutOfBoundsException e) {
               }
            }
         });
      }

   }

}