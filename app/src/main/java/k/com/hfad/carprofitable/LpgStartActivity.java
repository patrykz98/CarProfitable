package k.com.hfad.carprofitable;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

/**
 * The type Lpg start activity.
 * Klasa odpowiadająca za obsługę kalkulatora opłacalności instalacji gazowej.
 *
 * @author Patryk Zimny
 */
public class LpgStartActivity extends AppCompatActivity {


    /**
     * The My lpg layout.
     */
    ConstraintLayout myLpgLayout;
    /**
     * The Animation drawable.
     */
    AnimationDrawable animationDrawable;

    private TextInputLayout textKilometers;
    private TextInputLayout textCost;
    private TextInputLayout textConsumption;
    private TextInputLayout textCost2;
    private TextInputLayout textCostLpg;

    private Button catalogue;

    /**
     * The My dialog.
     */
    Dialog myDialog;


    /**
     * W onCreate występuje definicja wszystkich kontorlek, editTextów, przejście do katalogu.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lpg_start_activity);

        myLpgLayout = (ConstraintLayout) findViewById(R.id.myLpgLayout);

        animationDrawable = (AnimationDrawable) myLpgLayout.getBackground();
        animationDrawable.setEnterFadeDuration(3900);
        animationDrawable.setExitFadeDuration(3900);
        animationDrawable.start();

        Button goToMenu = (Button) findViewById(R.id.button3);

        textKilometers = findViewById(R.id.text_kilometers);
        textCost = findViewById(R.id.text_cost);
        textConsumption = findViewById(R.id.text_consumption);
        textCost2 = findViewById(R.id.text_cost2);
        textCostLpg = findViewById(R.id.text_cost_lpg);

        catalogue = (Button) findViewById(R.id.button5);

        goToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LpgStartActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        catalogue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LpgStartActivity.this, IntroActivity.class);
                startActivity(intent);
            }
        });

        myDialog = new Dialog(this);

    }

    /**
     * Klasa sprawdzająca poprawność danych w editText.
     * @return
     */
    private boolean validateKilometers(){
        String inputKilometers = textKilometers.getEditText().getText().toString().trim();

        if(inputKilometers.isEmpty()){
            textKilometers.setError("Pole 'PRZEBIEG' nie może być puste!");
            return false;
        }else if(inputKilometers.length()>6){
            textKilometers.setError("Niemożliwa roczna ilość przebiegu!");
            return false;
        }else{
            textKilometers.setError(null);
            return true;
        }
    }

    /**
     * Klasa sprawdzająca poprawność danych w editText.
     * @return
     */
    private boolean validateCost(){
        String inputCost = textCost.getEditText().getText().toString().trim();
        String inputCost2 = textCost2.getEditText().getText().toString().trim();
        String inputCostLpg = textCostLpg.getEditText().getText().toString().trim();
        if(inputCost.isEmpty()){
            textCost.setError("Pole 'KOSZTY' nie może być puste!");
            return false;
        }else if(inputCost.length()>6) {
            textCost.setError("Niemożliwa kwota kosztów.");
            return false;
        }else if(inputCost2.isEmpty()) {
            textCost2.setError("Pole 'Cena 1 litra' nie może być puste!");
            return false;
        }else if(inputCost2.length()>3) {
            textCost2.setError("Niemożliwa kwota kosztów.");
            return false;
        }else if(inputCostLpg.isEmpty()){
            textCostLpg.setError("Pole 'Cena 1 litra' nie może być puste!");
        }else if(inputCostLpg.length()>3){
            textCostLpg.setError("Niemożliwa kwota kosztów.");
        }else{
            textCost.setError(null);
            textCost2.setError(null);
            textCostLpg.setError(null);
            return true;
        }
        return true;
    }

    /**
     * Klasa sprawdzająca poprawność danych w editText.
     * @return
     */
    private boolean validateConsumption(){
        String inputConsumption = textConsumption.getEditText().getText().toString().trim();

        if(inputConsumption.isEmpty()){
            textConsumption.setError("Pole 'Zużycie' nie może być puste!");
            return false;
        }else if(inputConsumption.length()>6){
            textConsumption.setError("Niemożliwa wartość zużycia.");
            return false;
        }else{
            textConsumption.setError(null);
            return true;
        }
    }


    /**
     * On click liczymy.
     * Metoda wywoływana po kliknięciu głównego przycisku w aktywności.
     * Wyliczenia na podstawie podanych danych.
     * Wyświetla podsumowanie w customowym oknie pop-up.
     * @param view the view
     */
    public void onClickLiczymy(View view) {
        if(!validateConsumption() | !validateCost() | !validateKilometers()){
            return;
        }

        String kmPerYear = textKilometers.getEditText().getText().toString();
        int kmPerYearInt = Integer.parseInt(kmPerYear);

        String instCost = textCost.getEditText().getText().toString();
        int instCostInt = Integer.parseInt(instCost);

        String consumption = textConsumption.getEditText().getText().toString();
        double consumptionInt = Double.parseDouble(consumption);

        String pbCost = textCost2.getEditText().getText().toString();
        double pbCostInt = Double.parseDouble(pbCost);

        String lpgCost = textCostLpg.getEditText().getText().toString();
        double lpgCostInt = Double.parseDouble(lpgCost);

        TextView popUpClose;
        TextView popText1;
        TextView popText2;
        TextView popText4;
        TextView popText6;

        myDialog.setContentView(R.layout.pop_up_layout);
        popText1 = (TextView) myDialog.findViewById(R.id.popText1);
        popText2 = (TextView) myDialog.findViewById(R.id.popText2);
        popText4 = (TextView) myDialog.findViewById(R.id.popText4);
        popText6 = (TextView) myDialog.findViewById(R.id.popText6);

        DecimalFormat df = new DecimalFormat("#.##");

        popText1.setText("Koszt przejechania 100km na benzynie: " + df.format(pbCostInt*consumptionInt) +"zł");
        popText2.setText("Koszt przejechania 100km na gazie: " + df.format(lpgCostInt*(consumptionInt*1.1)) +"zł");

        double pbCons = pbCostInt*consumptionInt;
        double lpgCons = lpgCostInt*(consumptionInt*1.1);
        double costA = (instCostInt/(pbCons-lpgCons))*100;
        int result2 = (int) (costA/(kmPerYearInt/12));

        popText4.setText(""+df.format(costA)+" km");
        popText6.setText(""+result2+" miesięcy");

        popUpClose = (TextView) myDialog.findViewById(R.id.textViewClose);
        popUpClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }





}
