package com.example.vuquang.jars.activity.theoryjar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.vuquang.jars.R;
import com.example.vuquang.jars.activity.app.JarsApp;
import com.example.vuquang.jars.activity.theoryjar.adapter.JarAdapter;

/**
 * Created by VuQuang on 3/24/2018.
 */

public class TheoryJarFragment extends Fragment {

    private EditText tvTotal;

    private RecyclerView rvListJar;
    private JarAdapter mJarAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_theory_jar, null);
        tvTotal = view.findViewById(R.id.edit_total);
        tvTotal.setTypeface(JarsApp.getApp().getTypeFace("fonts/lane.ttf"));
        tvTotal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                long val = Long.parseLong(editable.toString());
                updateDataJars(val);
            }
        });
        init(view);
        return view;
    }

    private void init(View view) {
        rvListJar = view.findViewById(R.id.rv_list_jar);
        rvListJar.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mJarAdapter = new JarAdapter(getContext());
        rvListJar.setAdapter(mJarAdapter);
        tvTotal.setText(String.valueOf(4000000));
    }

    private void updateDataJars(long total) {
        mJarAdapter.setData(JarsApp.getApp().initListJar(total));
    }
}
