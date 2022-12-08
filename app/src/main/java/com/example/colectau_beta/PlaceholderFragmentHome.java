package com.example.colectau_beta;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class PlaceholderFragmentHome extends Fragment {

    public static final String ARG_SECTION_TITLE = "section_number";

    public static PlaceholderFragmentHome newInstance(String sectionTitle) {
        PlaceholderFragmentHome fragment = new PlaceholderFragmentHome();
        Bundle args = new Bundle();
        args.putString(ARG_SECTION_TITLE, sectionTitle);
        fragment.setArguments(args);
        return fragment;
    }

    public PlaceholderFragmentHome() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.section_fragment_home, container, false);

        // Ubicar argumento en el text view de section_fragment.xml
        String title = getArguments().getString(ARG_SECTION_TITLE);

        return view;
    }

}