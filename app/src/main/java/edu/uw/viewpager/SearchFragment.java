package edu.uw.viewpager;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import edu.uw.viewpager.R;

import static android.R.attr.id;
import static edu.uw.viewpager.R.id.container;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    interface OnSearchListener {
        void onSearchSubmitted(String searchTerm);
    }

    OnSearchListener callback;
    public SearchFragment() {
        // Required empty public constructor
    }

    public  static SearchFragment newInstance() {

        Bundle args = new Bundle();

        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void onAttach(Context context){
        super.onAttach(context);

        try {
            callback = (OnSearchListener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnSearchListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.fragment_movie_list, container, false);
        Button btn = rootView.findViewById(R.id.btn_search);
        String search = ((EditText)rootView.findViewById(R.id.txt_search)).toString();
        btn.setOnClickListener(new View.OnClickListener() {
            public void Onclick (View v){
                ((MainActivity)getActivity()).onSearchSubmitted(search);
            }
        };
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

}
