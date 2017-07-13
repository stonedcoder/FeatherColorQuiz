package com.android.example.feathercolorquiz;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /* global variables */
    int score = 0;
    String name = "";
    boolean correct_q1 = false, correct_q2 = false, correct_q3 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*Methods to get users input*/
/* Get the user name*/
    public void getUserName() {
        EditText nameField = (EditText) findViewById(R.id.name_field);
        Editable nameEditable = nameField.getText();
        name += nameEditable.toString();
    }

    /* Check the first question*/
    public void onRadioButtonClickQ1(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        boolean tempCorrect = false;
        switch (view.getId()) {
            case R.id.radio_button_q1_red:
                if (checked)
                    tempCorrect = true;
                break;
            case R.id.radio_button_q1_green:
                if (checked)
                    tempCorrect = false;
                break;
            case R.id.radio_button_q1_grey:
                if (!checked)
                    tempCorrect = true;
                break;
            case R.id.radio_button_q1_purple:
                if (!checked)
                    tempCorrect = true;
                break;
        }
        if (tempCorrect) {
            score++; // Prepare for toastMessage
            correct_q1 = true; // use for markUp icon
        }
    }

    /* Check the second question */
    public void onRadioButtonClickQ2(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        boolean tempCorrect = false;
        switch (view.getId()) {
            case R.id.radio_button_q2_yellow:
                if (!checked)
                    tempCorrect = true;
                break;
            case R.id.radio_button_q2_orange:
                if (!checked)
                    tempCorrect = true;
                break;
            case R.id.radio_button_q2_red:
                if (!checked)
                    tempCorrect = true;
                break;
            case R.id.radio_button_q2_blue:
                if (checked)
                    tempCorrect = true;
                break;
        }
        if (tempCorrect) {
            score++; // Parameters required for toastMessage
            correct_q2 = true; // Use for markUp icon
        }
    }


    public void checkQ3checkboxesStates() {

        int q3Score = 0;
        CheckBox yellowBox = (CheckBox) findViewById(R.id.checkbox_yellow);
        CheckBox redBox = (CheckBox) findViewById(R.id.checkbox_red);
        CheckBox blueBox = (CheckBox) findViewById(R.id.checkbox_blue);
        CheckBox brownBox = (CheckBox) findViewById(R.id.checkbox_brown);

        boolean yellowState = yellowBox.isChecked();
        boolean redState = redBox.isChecked();
        boolean blueState = blueBox.isChecked();
        boolean brownState = brownBox.isChecked();

        if (yellowState)
            q3Score++;
        if (!redState)
            q3Score++;
        if (blueState)
            q3Score++;
        if (brownState)
            q3Score++;

        if (q3Score == 4) {
            score++;
            correct_q3 = true;
        }
    }

    public void markUp() {

        /* initialize the image views */

        ImageView img1 = (ImageView) findViewById(R.id.question1_markUp);
        ImageView img2 = (ImageView) findViewById(R.id.question2_markUp);
        ImageView img3 = (ImageView) findViewById(R.id.question3_markUp);

        /* Set 'tick' when answer is correct, and 'cross' when answer is incorrect */

        if (correct_q1) {
            img1.setImageResource(R.drawable.correct);
        } else
            img1.setImageResource(R.drawable.incorrect);
        if (correct_q2) {
            img2.setImageResource(R.drawable.correct);
        } else
            img2.setImageResource(R.drawable.incorrect);
        if (correct_q3) {
            img3.setImageResource(R.drawable.correct);
        } else
            img3.setImageResource(R.drawable.incorrect);

    }


    /*Responsive markUp and toastMessage*/

    public void markUpMakeToast(View view) {

    /* Local variables */

        String finalMessage = "";
        int duration = 5000;

        /* Methods to get user's input, check button states and display markUp icons */

        getUserName();
        checkQ3checkboxesStates();
        markUp();

        /* Compose the toast message*/

        if (score == 0)
            finalMessage += "Hi, " + name + "! \nYou score " + score + " out of 3! \nGo check your eyes!!";
        if (score == 1)
            finalMessage += "Hi, " + name + "! \nYou score " + score + " out of 3! \nWay to go!!";
        if (score == 2)
            finalMessage += "Hi, " + name + "! \nYou score " + score + " out of 3! \nAlmost there!!";
        if (score == 3)
            finalMessage += "Hi, " + name + "! \nYou score " + score + " out of 3! \nBravo!!";

    /*  Set longer duration for the toast message*/

        final Toast myToast = Toast.makeText(this, finalMessage, Toast.LENGTH_LONG);
        CountDownTimer toastCountDown;
        toastCountDown = new CountDownTimer(duration, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                myToast.show();
            }

            @Override
            public void onFinish() {
                myToast.cancel();
            }
        };
        myToast.show();
        toastCountDown.start();
    }
}

