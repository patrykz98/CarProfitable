package k.com.hfad.carprofitable;

import android.app.Dialog;
import android.content.Intent;
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
 * Klasa odpowiadająca za obsługę kalkulatora opłacalności zakupu samochodu elektrycznego.
 */
public class ElectricStartActivity extends AppCompatActivity {

    /**
     * The My electric layout.
     */
    ConstraintLayout myElectricLayout;
    /**
     * The Animation drawable.
     */
    AnimationDrawable animationDrawable;

    /**
     * The My dialog.
     */
    Dialog myDialog;

    private TextInputLayout textKilometers;
    private TextInputLayout textElectricCarCost;
    private TextInputLayout textCarCost;
    private TextInputLayout textConsumption;
    private TextInputLayout textLiterCost;
    private TextInputLayout textEleCost;

    /**
     * Definicja wszystkich składowych oraz obsługa przycisku powrotu do okna głównego.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.electric_start_layout);

        myElectricLayout = (ConstraintLayout) findViewById(R.id.myElectricLayout);

        animationDrawable = (AnimationDrawable) myElectricLayout.getBackground();
        animationDrawable.setEnterFadeDuration(3900);
        animationDrawable.setExitFadeDuration(3900);
        animationDrawable.start();

        textKilometers = findViewById(R.id.text_kilometers);
        textElectricCarCost = findViewById(R.id.text_cost_new);
        textCarCost = findViewById(R.id.text_cost);
        textConsumption = findViewById(R.id.text_consumption);
        textLiterCost = findViewById(R.id.text_cost2);
        textEleCost = findViewById(R.id.text_cost_electric);

        myDialog = new Dialog(this);


        Button goToMenu = (Button) findViewById(R.id.button4);

        goToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ElectricStartActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
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
        String inputElectricCarCost = textElectricCarCost.getEditText().getText().toString().trim();
        String inputCarCost = textCarCost.getEditText().getText().toString().trim();
        String inputLiterCost = textLiterCost.getEditText().getText().toString().trim();
        String inputEleCost = textEleCost.getEditText().getText().toString().trim();
        if(inputElectricCarCost.isEmpty()){
            textElectricCarCost.setError("Pole 'KOSZTY' nie może być puste!");
            return false;
        }else if(inputElectricCarCost.length()>6) {
            textElectricCarCost.setError("Niemożliwa kwota kosztów.");
            return false;
        }else if(inputCarCost.isEmpty()) {
            textCarCost.setError("Pole nie może być puste!");
            return false;
        }else if(inputCarCost.length()>6) {
            textCarCost.setError("Niemożliwa kwota kosztów.");
            return false;
        }else if(inputLiterCost.isEmpty()){
            textLiterCost.setError("Pole nie może być puste!");
        }else if(inputLiterCost.length()>4){
            textLiterCost.setError("Niemożliwa kwota kosztów.");
        }else if(inputEleCost.length()>4){
            textEleCost.setError("Niemożliwa kwota kosztów.");
        }else if(inputEleCost.isEmpty()){
            textEleCost.setError("Pole nie może być puste!");
        }else{
            textEleCost.setError(null);
            textLiterCost.setError(null);
            textCarCost.setError(null);
            textElectricCarCost.setError(null);
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

        String carCost = textCarCost.getEditText().getText().toString();
        int carCosInt = Integer.parseInt(carCost);

        String consumption = textConsumption.getEditText().getText().toString();
        double consumptionDb = Double.parseDouble(consumption);

        String pbCost = textLiterCost.getEditText().getText().toString();
        double pbCostInt = Double.parseDouble(pbCost);

        String elCarCost = textElectricCarCost.getEditText().getText().toString();
        double elCarCostDb = Double.parseDouble(elCarCost);

        String elCost = textEleCost.getEditText().getText().toString();
        double elCostDb = Double.parseDouble(elCost);



        //roczne koszty
        double kmYears = kmPerYearInt/100;
        double costPb = kmYears * consumptionDb * pbCostInt;
        double eleCost = kmYears * elCostDb;

        double resultPb = costPb + carCosInt;
        double resultEle = eleCost + elCarCostDb;

        TextView popUpClose;
        TextView popText1;
        TextView popText2;
        TextView popText4;
        TextView popText6;
        TextView popText5;
        TextView popText3;


        myDialog.setContentView(R.layout.pop_up_layout);
        popText1 = (TextView) myDialog.findViewById(R.id.popText1);
        popText2 = (TextView) myDialog.findViewById(R.id.popText2);
        popText4 = (TextView) myDialog.findViewById(R.id.popText4);
        popText6 = (TextView) myDialog.findViewById(R.id.popText6);
        popText3 = (TextView) myDialog.findViewById(R.id.popText3);
        popText5 = (TextView) myDialog.findViewById(R.id.popText5);

        DecimalFormat df = new DecimalFormat("#.##");

        popText1.setText("Roczny koszt jazdy na benzynie: " + df.format(costPb) +"zł");
        popText2.setText("Roczny koszt jazdy na prądzie: " + df.format(eleCost) +"zł");

        popText3.setText("");
        popText5.setText("");

        double subCost = (elCarCostDb - carCosInt);
        double fuel = (pbCostInt*consumptionDb)-(elCostDb);
        double a = (subCost/fuel)*100;
        double b = a * 100;

        popText4.setText("Zwrot po : "+df.format(a)+" km");
        popText5.setText("Co odpowiada ok.: ");
        double monthResult = a/(kmPerYearInt/12);
        popText6.setText(""+df.format(monthResult)+" miesiącom");

        if(eleCost>costPb){
            popText4.setText("Zakup nieopłacalny");
            popText6.setText("Zakup nieopłacalny");
        }

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
