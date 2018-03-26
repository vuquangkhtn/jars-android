package com.example.vuquang.jars.activity.theoryjar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vuquang.jars.R;
import com.example.vuquang.jars.activity.app.JarsApp;
import com.example.vuquang.jars.activity.theoryjar.adapter.JarAdapter;

/**
 * Created by VuQuang on 3/24/2018.
 */

public class TheoryJarFragment extends Fragment {

    private TextView tvTotal;

    private RecyclerView rvListJar;
    private JarAdapter mJarAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_theory_jar, null);
        tvTotal = view.findViewById(R.id.tv_total);
        tvTotal.setTypeface(JarsApp.getApp().getTypeFace("fonts/lane.ttf"));
        init(view);
        return view;
    }

    private void init(View view) {
        long total = 4000000;
        tvTotal.setText(String.valueOf(total));
        rvListJar = view.findViewById(R.id.rv_list_jar);
        rvListJar.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mJarAdapter = new JarAdapter(getContext());
        mJarAdapter.setData(JarsApp.getApp().initListJar(total));
        rvListJar.setAdapter(mJarAdapter);

    }
}
