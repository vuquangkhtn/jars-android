package com.example.vuquang.jars.activity.theoryjar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vuquang.jars.R;
import com.example.vuquang.jars.activity.app.JarsApp;

/**
 * Created by VuQuang on 3/24/2018.
 */

public class TheoryJarFragment extends Fragment {

    private TextView tvTotal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_theory_jar, null);
        tvTotal = view.findViewById(R.id.tv_total);
        tvTotal.setTypeface(JarsApp.getApp().getTypeFace("fonts/lane.ttf"));
        return view;
    }
}
