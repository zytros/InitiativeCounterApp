package com.inCounter.userEnd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.inCounter.userEnd.core.Communicator;

public class MainActivity extends AppCompatActivity {

    String name;
    int hp;
    double initiative;

    Configuration configuration;
    Communicator communicator;

    Button cmd_setName;
    Button cmd_setInitiative;
    Button cmd_pOne;
    Button cmd_nOne;
    Button cmd_pTen;
    Button cmd_nTen;
    TextView lbl_hp;
    EditText txt_charName;
    EditText txt_initiative;

    public MainActivity(){
        configuration = new Configuration("192.168.178.52", 8080, "userEnd", "getId", "OK", "setName", "setInitiative", "changeHP");
        communicator = new Communicator(configuration);
        System.out.println("new instanca");
        System.out.println("new instanca");
        System.out.println("new instanca");
        System.out.println("new instance");
        System.out.println("new instance");
        System.out.println("new instance");
        System.out.println("new instance");
        System.out.println("new instance");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        /**
         *Initializing GUI items
         */

        cmd_setName = (Button) findViewById(R.id.cmd_setName);
        cmd_setInitiative = (Button) findViewById(R.id.cmd_setInitiative);
        cmd_nOne = (Button) findViewById(R.id.cmd_nOne);
        cmd_nTen = (Button) findViewById(R.id.cmd_nTen);
        cmd_pOne = (Button) findViewById(R.id.cmd_pOne);
        cmd_pTen = (Button) findViewById(R.id.cmd_pTen);
        lbl_hp = (TextView) findViewById(R.id.lbl_hp);
        txt_charName = (EditText) findViewById(R.id.txt_charName);
        txt_initiative = (EditText) findViewById(R.id.txt_initiative);

        /**
         * setting up Listeners
         */

        cmd_setName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = txt_charName.getText().toString();
                communicator.sendMessage(configuration.getSetName(), name);
                System.out.println("name Set to: " + name);
            }
        });

        cmd_setInitiative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    initiative = Double.parseDouble(txt_initiative.getText().toString());  //Integer.parseInt(txt_initiative.getText().toString());
                } catch (NumberFormatException e) {
                    initiative = 0;
                }
                communicator.sendMessage(configuration.getSetInitiative(), Double.toString(initiative));
                System.out.println("Initiative set to: " + Double.toString(initiative));
            }
        });

        cmd_pTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hp = hp + 10;
                lbl_hp.setText(Integer.toString(hp));
                communicator.sendMessage(configuration.getChangeHP(), "10");
            }
        });

        cmd_pOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hp++;
                lbl_hp.setText(Integer.toString(hp));
                communicator.sendMessage(configuration.getChangeHP(), "1");
            }
        });

        cmd_nTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hp = hp - 10;
                lbl_hp.setText(Integer.toString(hp));
                communicator.sendMessage(configuration.getChangeHP(), "-10");
            }
        });

        cmd_nOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hp--;
                lbl_hp.setText(Integer.toString(hp));
                communicator.sendMessage(configuration.getChangeHP(), "-1");
            }
        });

    }
}