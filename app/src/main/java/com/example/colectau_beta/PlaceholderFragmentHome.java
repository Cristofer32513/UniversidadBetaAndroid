package com.example.colectau_beta;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

public class PlaceholderFragmentHome extends Fragment {

    public PlaceholderFragmentHome() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.section_fragment_home, container, false);
    }
}