package k.com.hfad.carprofitable;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TableLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa uzupełniająca dane przesuwnego layoutu/katalogu.
 */
public class IntroActivity extends AppCompatActivity {

    /**
     * Deklaracje elementów layoutu
     */

    private ViewPager screenPager;
    IntoViewPagerAdapter introViewPagerAdapter;
    TabLayout tabIndicator;
    Button btnNext;
    int position = 0;
    Button btnGetStarted;
    Animation btnAnim;
    Button goBackBtn;

    /**
     * Wypełnianie elemntów, przewijanej listy określonymi danymi.
     * Obsługa przycisków akcji odpowiadających za ruch layoutu.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        btnNext = findViewById(R.id.btn_next);
        btnGetStarted = findViewById(R.id.btn_get_started);
        tabIndicator = findViewById(R.id.tab_indicator);
        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.button_animation);
        goBackBtn = findViewById(R.id.goBack);



        final List<ScreenItem> mList = new ArrayList<>();
        mList.add(new ScreenItem("Wybierz markę", "INSTRUKTAŻ\nW tym kroku wybierz markę samochodu \n dla którego interesuje Cię średnie zużycie paliwa", R.drawable.brand));
        mList.add(new ScreenItem("Wybierz model","Teraz wybierz model szukanego pojazdu.", R.drawable.model));
        mList.add(new ScreenItem("Wybierz swój silnik","Czas na wersję silnikową, \nwybierz swoją spośród elemtów listy.", R.drawable.car));

        screenPager = findViewById(R.id.screen_viewpager);
        introViewPagerAdapter = new IntoViewPagerAdapter(this, mList);
        screenPager.setAdapter(introViewPagerAdapter);

        tabIndicator.setupWithViewPager(screenPager);


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                position = screenPager.getCurrentItem();
                if(position < mList.size()){
                    position++;
                    screenPager.setCurrentItem(position);
                }

                if(position == mList.size()-1){
                    loadLastScreen();
                }


            }
        });


        tabIndicator.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if(tab.getPosition() == mList.size()-1){

                    loadLastScreen();

                }
                if(tab.getPosition() < mList.size()-1){
                    btnGetStarted.setVisibility(View.INVISIBLE);
                    btnNext.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Intent intent = new Intent(getApplicationContext(), ChoosingList.class);
                Intent intent = new Intent(getApplicationContext(), FirstListView.class);
                startActivity(intent);

            }
        });


        goBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LpgStartActivity.class);
                startActivity(intent);
            }
        });

    }

    /**
     * Animacja spadającaego przycisku na ostatnim etapie.
     */
    private void loadLastScreen() {

        btnNext.setVisibility(View.INVISIBLE);
        btnGetStarted.setVisibility(View.VISIBLE);
        tabIndicator.setVisibility(View.VISIBLE);

        btnGetStarted.setAnimation(btnAnim);

    }
}
