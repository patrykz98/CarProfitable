package k.com.hfad.carprofitable;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Klasa wypełniająca drugi i trzeci krok listy katalogowej.
 * Przyjmuje dane od widoku wywołującego.
 */
public class SecondListView extends AppCompatActivity {

    /**
     * The List view second.
     */
    ListView listViewSecond;

    /**
     * W onCreate występują wszystkie funkcjonalności akatywności.
     * Stworzenie adaptera dla listy, uzupełnienie listy w zależności od odebranego klucza.
     * Zmiana wartości elementów listy po kliknięciu w dany element.
     * Przekierwoanie do aktywności kalkulatora.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.model_choice_layout);

        listViewSecond = (ListView) findViewById(R.id.listViewBrand);
        String extra;

        final ArrayList<String> arrayList = new ArrayList<>();
        arrayList.clear();

        final ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
                extra = bundle.getString("Brand");
                switch(extra){
                    case "Alfa Romeo":
                            listViewSecond.setAdapter(null);
                            arrayList.clear();
                            arrayList.add("159");
                            listViewSecond.setAdapter(arrayAdapter);
                            listViewSecond.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                        listViewSecond.setAdapter(null);
                                        arrayList.clear();
                                        arrayList.add("2.2 JTS 185KM                  11 LITRÓW");
                                        arrayList.add("3.2 JTS 24V 260KM              13 LITRÓW");
                                        arrayList.add("1.8 MPI 140KM                  8 LITRÓW");
                                        listViewSecond.setAdapter(arrayAdapter);
                                    listViewSecond.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                            Intent intent = new Intent(SecondListView.this, LpgStartActivity.class);
                                            startActivity(intent);
                                        }
                                    });
                                }
                            });

                        break;
                    case "Volkswagen":
                            listViewSecond.setAdapter(null);
                            arrayList.clear();
                            arrayList.add("Golf");
                            listViewSecond.setAdapter(arrayAdapter);
                            listViewSecond.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    listViewSecond.setAdapter(null);
                                    arrayList.clear();
                                    arrayList.add("1.4 MPI 75KM              7 LITRÓW");
                                    arrayList.add("1.6 MPI 105KM             7 LITRÓW");
                                    arrayList.add("1.8 Turbo 150KM           10 LITRÓW");
                                    listViewSecond.setAdapter(arrayAdapter);
                                    listViewSecond.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                            Intent intent = new Intent(SecondListView.this, LpgStartActivity.class);
                                            startActivity(intent);
                                        }
                                    });
                                }
                            });
                        break;
                    case "Peugeot":
                            listViewSecond.setAdapter(null);
                             arrayList.clear();
                            arrayList.add("407");
                            listViewSecond.setAdapter(arrayAdapter);
                            listViewSecond.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    listViewSecond.setAdapter(null);
                                    arrayList.clear();
                                    arrayList.add("1.8 16V 125KM                8 LITRÓW");
                                    arrayList.add("2.0 16V 140KM                9 LITRÓW");
                                    arrayList.add("3.0 V6 211KM                 13 LITRÓW");
                                    listViewSecond.setAdapter(arrayAdapter);
                                    listViewSecond.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                            Intent intent = new Intent(SecondListView.this, LpgStartActivity.class);
                                            startActivity(intent);
                                        }
                                    });
                                }
                            });
                        break;
                }
        }
    }
}
