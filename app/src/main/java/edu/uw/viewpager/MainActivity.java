package edu.uw.viewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Adapter;
import android.widget.EditText;

import static android.os.Build.VERSION_CODES.M;

public class MainActivity extends AppCompatActivity implements MovieListFragment.OnMovieSelectedListener, SearchFragment.OnSearchListener {

    private static final String TAG = "MainActivity";
    public static final String MOVIE_LIST_FRAGMENT_TAG = "MoviesListFragment";
    public static final String MOVIE_DETAIL_FRAGMENT_TAG = "DetailFragment";
    public MoviePagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SearchFragment searchFrag = SearchFragment.newInstance();
        FragmentManager fm = getSupportFragmentManager();
        adapter = new MoviePagerAdapter(fm);
        ViewPager vPager = (ViewPager) findViewById(R.id.view_pager);
        vPager.setAdapter(adapter);
    }

    //respond to search button clicking
    public void handleSearchClick(View v){
        EditText text = (EditText)findViewById(R.id.txt_search);
        String searchTerm = text.getText().toString();

        MovieListFragment fragment = MovieListFragment.newInstance(searchTerm);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, fragment, MOVIE_LIST_FRAGMENT_TAG);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onSearchSubmitted(String searchTerm) {
        MovieListFragment movList = MovieListFragment.newInstance(searchTerm);
        adapter.notifyDataSetChanged();
        ViewPager vPager = (ViewPager) findViewById(R.id.view_pager);
        vPager.setCurrentItem(1);
    }

    @Override
    public void onMovieSelected(Movie movie) {
        DetailFragment fragment = DetailFragment.newInstance(movie);
        adapter.notifyDataSetChanged();
        ViewPager vPager = (ViewPager) findViewById(R.id.view_pager);
        vPager.setCurrentItem(2);
    }

    public class MoviePagerAdapter extends FragmentStatePagerAdapter {
        SearchFragment search = new SearchFragment();
        MovieListFragment movie = new MovieListFragment();
        DetailFragment detail = new DetailFragment();

        public MoviePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getItemPosition(Object object){
            return POSITION_NONE;
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return search;
            } else if (position == 1) {
                return movie;
            } else {
                return detail;
            }
        }

        @Override
        public int getCount() {
            if (movie != null && detail == null) {
                return 2;
            } else if (detail != null && movie != null) {
                return 3;
            } else {
                return 1;
            }
        }
    }
}
