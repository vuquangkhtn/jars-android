package com.example.vuquang.jars.activity.theoryjar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vuquang.jars.R;

/**
 * Created by VuQuang on 3/24/2018.
 */

public class TheoryJarFragment extends Fragment {

    public TheoryJarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_theory_jar, container, false);
    }
}
