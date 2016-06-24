package com.example.user.nilosdice;

import android.location.GpsStatus;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;
import android.os.Message;
import java.util.Random;
import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity {
    public static int userscore = 0;
    public static int uaccum;
    public static int compscore = 0;
    public static int compaccum;
    public static String USER_TURN = "Roll Your Luck";
    public static String COMPUTER_TURN = "Asimo is with the Dice";
    public static boolean userTurn = true;
    public String myTag = "InfoTag";
    public ProgressBar bar;
    private Random random = new Random();
    public static int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(myTag, "Iam in Oncreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bar = (ProgressBar) findViewById(R.id.progressBar);

        Log.i(myTag, "I did oncreate");
        Log.i(myTag, "Iam in playuser");
        Log.i(myTag, "Iam listening to player");

        }
    public void rolled(View view)
    {
        final ImageView dice = (ImageView) findViewById(R.id.imageView);
        final EditText acc = (EditText) findViewById(R.id.editText2);
        final EditText utotal = (EditText) findViewById(R.id.editText);
        final EditText ctotal = (EditText) findViewById(R.id.editText1);
        final TextView status = (TextView) findViewById(R.id.Status);
        index = 0;
        if (userTurn)
        {
            int rid = getResources().getIdentifier("dice3d", "drawable", getPackageName());
            dice.setImageResource(rid);
            bar.setProgress(0);
            new CountDownTimer(1000,10) {

                @Override
                public void onTick(long millisUntilFinished)
                {
                    int f = random.nextInt(5);
                    int rid = getResources().getIdentifier("dice" + Integer.toString(f + 1), "drawable", getPackageName());
                    dice.setImageResource(rid);
                    index++;
                   bar.setProgress(index);
                }
                @Override
                public void onFinish() {
                    bar.setProgress(10);
                    index = 0;
                    int f = random.nextInt(5);
                    int rid = getResources().getIdentifier("dice" + Integer.toString(f + 1), "drawable", getPackageName());
                    dice.setImageResource(rid);
                    if ((f + 1) == 1)
                    {
                        uaccum = 0;
                        acc.setText(Integer.toString(uaccum));
                        Toast.makeText(getApplicationContext(), "You ran into 1...Turn passes", Toast.LENGTH_LONG).show();
                        userTurn = false;
                        computerTurn();
                    }
                    else
                    {
                        uaccum = uaccum + f + 1;
                        acc.setText(Integer.toString(uaccum));
                    }
                }
            }.start();

        }
        else
        {
            Toast.makeText(getApplicationContext(), "not ur turn", Toast.LENGTH_LONG).show();
        }
    }
    public void holded(View view)
    {
        final EditText acc = (EditText) findViewById(R.id.editText2);
        final EditText utotal = (EditText) findViewById(R.id.editText);
        final EditText ctotal = (EditText) findViewById(R.id.editText1);
        final TextView status = (TextView) findViewById(R.id.Status);

        if (userTurn) {
            userscore = userscore + uaccum;
            uaccum = 0;
            acc.setText(Integer.toString(uaccum));
            utotal.setText(Integer.toString(userscore));
            userTurn = false;
            computerTurn();
        } else {
            Toast.makeText(getApplicationContext(), "wait", Toast.LENGTH_LONG).show();
        }
        if ((userscore >= 100) && (compscore < 100)) {
            Toast.makeText(getApplicationContext(), "you win", Toast.LENGTH_LONG).show();
            status.setText("YOU WON");
            userscore = 0;
            compscore = 0;
            uaccum = 0;
            compaccum = 0;
            acc.setText(Integer.toString(uaccum));
            utotal.setText(Integer.toString(userscore));
            ctotal.setText(Integer.toString(compscore));
        }
    }
    public void reseted(View view)
    {
        final EditText acc = (EditText) findViewById(R.id.editText2);
        final EditText utotal = (EditText) findViewById(R.id.editText);
        final EditText ctotal = (EditText) findViewById(R.id.editText1);
        final TextView status = (TextView) findViewById(R.id.Status);
        final ImageView dice = (ImageView)findViewById(R.id.imageView);
           if(userTurn) {
               userscore = 0;
               compscore = 0;
               uaccum = 0;
               compaccum = 0;
               int rid = getResources().getIdentifier("dice3d", "drawable", getPackageName());
               dice.setImageResource(rid);
               acc.setText(Integer.toString(uaccum));
               utotal.setText(Integer.toString(userscore));
               ctotal.setText(Integer.toString(compscore));
               Toast.makeText(getApplicationContext(), "Game Reseted... You can start New Roll ", Toast.LENGTH_LONG).show();
           }
        else
           {
               Toast.makeText(getApplicationContext(),"Asimo is wid the Dice",Toast.LENGTH_LONG).show();
           }
    }

    public void computerTurn() {
        Log.i(myTag, "Iam in computer turn");
        final ImageView dice = (ImageView) findViewById(R.id.imageView);
        Button hold = (Button) findViewById(R.id.button2);
        Button roll = (Button) findViewById(R.id.button);
        Button reset = (Button) findViewById(R.id.button3);
        final EditText acc = (EditText) findViewById(R.id.editText2);
        final EditText utotal = (EditText) findViewById(R.id.editText);
        final EditText ctotal = (EditText) findViewById(R.id.editText1);
        final TextView status = (TextView) findViewById(R.id.Status);
        status.setText("Asimos is with the dice");
        index=0;
        if (userTurn == false) {

            new CountDownTimer(1000,10) {

                @Override
                public void onTick(long millisUntilFinished)
                {
                    int f = random.nextInt(5);
                    int rid = getResources().getIdentifier("dice" + Integer.toString(f + 1), "drawable", getPackageName());
                    dice.setImageResource(rid);
                    index++;
                    bar.setProgress(index);
                }
                @Override
                public void onFinish() {
                    index=0;
                    bar.setProgress(10);
                    for (int i = 0; i < 5; i++) {
                        int face = random.nextInt(5);
                        int rid = getResources().getIdentifier("dice" + Integer.toString(face + 1), "drawable", getPackageName());
                        dice.setImageResource(rid);
                        if ((face + 1) == 1) {
                            compaccum = 0;
                            acc.setText(Integer.toString(compaccum));
                            ctotal.setText(Integer.toString(compaccum));
                            Toast.makeText(getApplicationContext(), "ASIMO ran into 1", Toast.LENGTH_LONG).show();
                            break;
                        } else {
                            compaccum = compaccum + face + 1;
                        }
                    }
                            acc.setText(Integer.toString(compaccum));
                            compscore = compscore + compaccum;
                            compaccum = 0;
                            ctotal.setText(Integer.toString(compscore));
                            if((compscore>=100)&&(userscore<100))
                            {
                                Toast.makeText(getApplicationContext(), "Asimo wins", Toast.LENGTH_LONG).show();
                                userscore = 0;
                                compscore = 0;
                                uaccum = 0;
                                compaccum = 0;
                                acc.setText(Integer.toString(uaccum));
                                utotal.setText(Integer.toString(userscore));
                                ctotal.setText(Integer.toString(compscore));
                                status.setText("ASIMO WON");
                            }
                            userTurn = true;
                            status.setText("Roll your luck");
                        }


            }.start();
                   }
        return;
    }



}



