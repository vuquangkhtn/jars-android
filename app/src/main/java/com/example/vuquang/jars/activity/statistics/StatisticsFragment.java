package com.example.vuquang.jars.activity.statistics;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.vuquang.jars.R;
import com.example.vuquang.jars.activity.app.JarsApp;
import com.example.vuquang.jars.activity.statistics.adapter.JarAdapter;

/**
 * Created by VuQuang on 3/24/2018.
 */

public class StatisticsFragment extends Fragment {

    private EditText editTotal;

    private RecyclerView rvListJar;
    private JarAdapter mJarAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_statistics, null);
        editTotal = view.findViewById(R.id.edit_total);
        editTotal.setTypeface(JarsApp.getApp().getTypeFace("fonts/lane.ttf"));
        editTotal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                long val;
                if(editable.toString().isEmpty()) {
                    val = 0;
                    editable.append('0');
                } else {
                    val = Long.parseLong(editable.toString());
                }
                updateDataJars(val);
            }
        });

        //unfocus when touch outside edittext
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (editTotal.isFocused()) {
                        Rect outRect = new Rect();
                        editTotal.getGlobalVisibleRect(outRect);
                        if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                            editTotal.clearFocus();
                            InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                        }
                    }
                }
                return false;
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
        editTotal.setText(String.valueOf(4000000));
    }

    private void updateDataJars(long total) {
        mJarAdapter.setData(JarsApp.getApp().initListJar(total));
    }
}
