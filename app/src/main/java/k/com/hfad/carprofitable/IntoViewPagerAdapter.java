package k.com.hfad.carprofitable;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Klasa odpowiadająca za przesuwane menu z instrukatażem wyboru danych z katalogu.
 */
public class IntoViewPagerAdapter extends PagerAdapter {
    /**
     * The M context.
     */
    Context mContext;
    /**
     * The M list screen.
     */
    List<ScreenItem> mListScreen;


    /**
     * Konstruktor
     *
     * @param mContext    the m context
     * @param mListScreen the m list screen
     */
    public IntoViewPagerAdapter(Context mContext, List<ScreenItem> mListScreen) {
        this.mContext = mContext;
        this.mListScreen = mListScreen;
    }


    /**
     * Metoda obsługująca przesuwany layout, uzupełnianie elemnetów layoutu -
     * obrazek, tytuł, opis
     * elementami zależnymi od pozycji.
     * @param container
     * @param position
     * @return
     */
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutScreen = inflater.inflate(R.layout.layout_screen,null);

        ImageView imgSlide = layoutScreen.findViewById(R.id.intro_img);
        TextView title = layoutScreen.findViewById(R.id.introTitle);
        TextView description = layoutScreen.findViewById(R.id.introDescription);

        title.setText(mListScreen.get(position).getTitle());
        description.setText(mListScreen.get(position).getDescription());
        imgSlide.setImageResource(mListScreen.get(position).getScreenImg());

        container.addView(layoutScreen);

        return layoutScreen;
    }

    /**
     * Licznik zwracjący rozmiar listy.
     * @return
     */
    @Override
    public int getCount() {
        return mListScreen.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    /**
     * Metoda usuwająca dane już niepotrzebne.
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
