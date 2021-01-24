package k.com.hfad.carprofitable;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tomer.fadingtextview.FadingTextView;

/**
 * Główna klasa, występuje tutaj obsługa animacji tła oraz definicja i obłsuga
 * przycisków określających dwie główne funkcje aplikacji.
 *
 * @author Patryk Zimny
 */
public class MainActivity extends AppCompatActivity {

    private FadingTextView fadingTextView;

    /**
     * The My layout.
     */
    ConstraintLayout myLayout;
    /**
     * The Animation drawable.
     */
    AnimationDrawable animationDrawable;

    /**
     * onCreate. Obsługa animowawnego tła oraz obsługa przejścia do głównych funkcjonalności aplikacji - kalkulatorów.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myLayout = (ConstraintLayout) findViewById(R.id.myLayout);

        animationDrawable = (AnimationDrawable) myLayout.getBackground();
        animationDrawable.setEnterFadeDuration(3900);
        animationDrawable.setExitFadeDuration(3900);
        animationDrawable.start();

        fadingTextView = findViewById(R.id.fading_text_view);
        Button goToLpgButton = (Button) findViewById(R.id.button);
        Button goToElectricButton = (Button) findViewById(R.id.button2);

        goToLpgButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LpgStartActivity.class);
                startActivity(intent);
        }
        });

        goToElectricButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ElectricStartActivity.class);
                startActivity(intent);
            }
        });

    }

}
