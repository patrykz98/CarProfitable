package k.com.hfad.carprofitable;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Klasa obsługująca pierwszy etap w katalogu marek. Uzupełnia listView odpowiednimi parametrami.
 */
public class FirstListView extends AppCompatActivity {

    /**
     * The List view.
     */
    ListView listView;

    /**
     * W onCreate występuję deklaracja widoku listy, stworzenie adaptera uzupełniającego listę
     * oraz obsługa zdarzenia kliknięcia elementu listy.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.model_choice_layout);

        listView = (ListView) findViewById(R.id.listViewBrand);

        ArrayAdapter<String> mAdapter =
                new ArrayAdapter<String>(FirstListView.this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.choice_items_brand));


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(FirstListView.this, SecondListView.class);
                intent.putExtra("Brand", listView.getItemAtPosition(i).toString());
                startActivity(intent);
            }
        });
        listView.setAdapter(mAdapter);
    }
}
