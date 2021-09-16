package com.inCounter.userEnd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.inCounter.userEnd.core.Communicator;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    String name;
    int hp;
    double initiative;
    int[] back_images;
    ArrayList<Integer>[] charImg;
    Random rnd = new Random();
    String[] classes = new String[]{"artificer", "barbarian", "bard", "bloodHunter", "cleric", "druid", "fighter", "monk", "paladin", "ranger", "rogue", "sorcerer", "warlock", "wizard"};
    int[] colors = new int[]{0xFFD59139, 0xFFE7623E, 0xFFAB6DAC, 0xFFC73032, 0xFF91A1B2, 0xFF7A853B, 0xFF7F513E, 0xFF51A5C5, 0xFFB59E54, 0xFF507F62, 0xFF555752, 0xFF992E2E, 0xFF7B469B, 0xFF2A50A1};
    View screenView;
    ArrayList<Button> buttonList;

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
    RadioGroup radioGroup;
    RadioButton radioButton;
    RadioGroup rg0;
    RadioGroup rg1;
    RadioGroup rg2;
    RadioGroup rg3;

    public MainActivity(){
        configuration = new Configuration("192.168.178.52", 8080, "userEnd", "getId", "OK", "setName", "setInitiative", "changeHP", "changeClass");
        communicator = new Communicator(configuration);
        buttonList = new ArrayList<>();
        charImg = new ArrayList[14];
        for(int i = 0; i < 14; i++){
            charImg[i] = new ArrayList<Integer>();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);                     //no rotation

        back_images = new int[]{R.drawable.mountain, R.drawable.mountain2, R.drawable.mountain3, R.drawable.mountain4, R.drawable.mountain5, R.drawable.mountain6};

        /**
         * Load Images in ArrayList
         */


        for(int i = 0; i < 14; i++){
            charImg[i].add(back_images[rnd.nextInt(6)]);
            charImg[i].add(back_images[rnd.nextInt(6)]);
            charImg[i].add(back_images[rnd.nextInt(6)]);
        }
        charImg[0].add(R.drawable.mountain2);
        /**
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         */





        /**
         *Initializing GUI items
         */

        cmd_setName = (Button) findViewById(R.id.cmd_setName);
        buttonList.add(cmd_setName);
        cmd_setInitiative = (Button) findViewById(R.id.cmd_setInitiative);
        buttonList.add(cmd_setInitiative);
        cmd_nOne = (Button) findViewById(R.id.cmd_nOne);
        buttonList.add(cmd_nOne);
        cmd_nTen = (Button) findViewById(R.id.cmd_nTen);
        buttonList.add(cmd_nTen);
        cmd_pOne = (Button) findViewById(R.id.cmd_pOne);
        buttonList.add(cmd_pOne);
        cmd_pTen = (Button) findViewById(R.id.cmd_pTen);
        buttonList.add(cmd_pTen);
        lbl_hp = (TextView) findViewById(R.id.lbl_hp);
        txt_charName = (EditText) findViewById(R.id.txt_charName);
        txt_initiative = (EditText) findViewById(R.id.txt_initiative);
        radioGroup = (RadioGroup) findViewById(R.id.firstGroup);
        rg0 = (RadioGroup) findViewById(R.id.rg_zero);
        rg1 = (RadioGroup) findViewById(R.id.rg_one);
        rg2 = (RadioGroup) findViewById(R.id.rg_two);
        rg3 = (RadioGroup) findViewById(R.id.rg_three);
        screenView = (View) findViewById(R.id.constraintLayout);

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
                    initiative = Double.parseDouble(txt_initiative.getText().toString());
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
                lbl_hp.setText(hp);
                communicator.sendMessage(configuration.getChangeHP(), "10");
            }
        });

        cmd_pOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hp++;
                lbl_hp.setText(hp);
                communicator.sendMessage(configuration.getChangeHP(), "1");
            }
        });

        cmd_nTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hp = hp - 10;
                lbl_hp.setText(hp);
                communicator.sendMessage(configuration.getChangeHP(), "-10");
            }
        });

        cmd_nOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hp--;
                lbl_hp.setText(hp);
                communicator.sendMessage(configuration.getChangeHP(), "-1");
            }
        });

    }

    public void classChange(RadioButton rb){
        String classChar = (String) rb.getText();
        communicator.sendMessage(configuration.getChangeClass(), classChar);
        int numImg = 0;
        int classNum = 0;
        int img = 0;
        switch (classChar){
            case "Artificer":
                classNum = 0;
                changeColor(colors[classNum]);
                numImg = charImg[classNum].size();
                img = charImg[classNum].get(rnd.nextInt(numImg));
                screenView.setBackground(ContextCompat.getDrawable(getApplicationContext(), img));
                break;
            case "Barbarian":
                classNum = 1;
                changeColor(colors[classNum]);
                numImg = charImg[classNum].size();
                img = charImg[classNum].get(rnd.nextInt(numImg));
                screenView.setBackground(ContextCompat.getDrawable(getApplicationContext(), img));
                break;
            case "Bard":
                classNum = 2;
                changeColor(colors[classNum]);
                numImg = charImg[classNum].size();
                img = charImg[classNum].get(rnd.nextInt(numImg));
                screenView.setBackground(ContextCompat.getDrawable(getApplicationContext(), img));
                break;
            case "Blood Hunter":
                classNum = 3;
                changeColor(colors[classNum]);
                numImg = charImg[classNum].size();
                img = charImg[classNum].get(rnd.nextInt(numImg));
                screenView.setBackground(ContextCompat.getDrawable(getApplicationContext(), img));
                break;
            case "Cleric":
                classNum = 4;
                changeColor(colors[classNum]);
                numImg = charImg[classNum].size();
                img = charImg[classNum].get(rnd.nextInt(numImg));
                screenView.setBackground(ContextCompat.getDrawable(getApplicationContext(), img));
                break;
            case "Druid":
                classNum = 5;
                changeColor(colors[classNum]);
                numImg = charImg[classNum].size();
                img = charImg[classNum].get(rnd.nextInt(numImg));
                screenView.setBackground(ContextCompat.getDrawable(getApplicationContext(), img));
                break;
            case "Fighter":
                classNum = 6;
                changeColor(colors[classNum]);
                numImg = charImg[classNum].size();
                img = charImg[classNum].get(rnd.nextInt(numImg));
                screenView.setBackground(ContextCompat.getDrawable(getApplicationContext(), img));
                break;
            case "Monk":
                classNum = 7;
                changeColor(colors[classNum]);
                numImg = charImg[classNum].size();
                img = charImg[classNum].get(rnd.nextInt(numImg));
                screenView.setBackground(ContextCompat.getDrawable(getApplicationContext(), img));
                break;
            case "Paladin":
                classNum = 8;
                changeColor(colors[classNum]);
                numImg = charImg[classNum].size();
                img = charImg[classNum].get(rnd.nextInt(numImg));
                screenView.setBackground(ContextCompat.getDrawable(getApplicationContext(), img));
                break;
            case "Ranger":
                classNum = 9;
                changeColor(colors[classNum]);
                numImg = charImg[classNum].size();
                img = charImg[classNum].get(rnd.nextInt(numImg));
                screenView.setBackground(ContextCompat.getDrawable(getApplicationContext(), img));
                break;
            case "Rogue":
                classNum = 10;
                changeColor(colors[classNum]);
                numImg = charImg[classNum].size();
                img = charImg[classNum].get(rnd.nextInt(numImg));
                screenView.setBackground(ContextCompat.getDrawable(getApplicationContext(), img));
                break;
            case "Sorcerer":
                classNum = 11;
                changeColor(colors[classNum]);
                numImg = charImg[classNum].size();
                img = charImg[classNum].get(rnd.nextInt(numImg));
                screenView.setBackground(ContextCompat.getDrawable(getApplicationContext(), img));
                break;
            case "Warlock":
                classNum = 12;
                changeColor(colors[classNum]);
                numImg = charImg[classNum].size();
                img = charImg[classNum].get(rnd.nextInt(numImg));
                screenView.setBackground(ContextCompat.getDrawable(getApplicationContext(), img));
                break;
            case "Wizard":
                classNum = 13;
                changeColor(colors[classNum]);
                numImg = charImg[classNum].size();
                img = charImg[classNum].get(rnd.nextInt(numImg));
                screenView.setBackground(ContextCompat.getDrawable(getApplicationContext(), img));
                break;
            default:
                screenView.setBackground(ContextCompat.getDrawable(getApplicationContext(), back_images[5]));
                break;
        }
    }


    public void changeColor(int color){
        for(Button button : buttonList){
            button.setBackgroundColor(color);
            button.setTextColor(0xFF000000);
            txt_charName.setBackgroundColor(color - 0x7F000000);
            txt_initiative.setBackgroundColor(color - 0x7F000000);
        }
    }

    public void groupZero(View v){
        rg1.clearCheck();
        rg2.clearCheck();
        rg3.clearCheck();
        int radioID = rg0.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(radioID);
        classChange(radioButton);

    }
    public void groupOne(View v){
        rg0.clearCheck();
        rg2.clearCheck();
        rg3.clearCheck();
        int radioID = rg1.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(radioID);
        classChange(radioButton);
    }
    public void groupTwo(View v){
        rg0.clearCheck();
        rg1.clearCheck();
        rg3.clearCheck();
        int radioID = rg2.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(radioID);
        classChange(radioButton);
    }
    public void groupThree(View v){
        rg0.clearCheck();
        rg1.clearCheck();
        rg2.clearCheck();
        int radioID = rg3.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(radioID);
        classChange(radioButton);
    }
}