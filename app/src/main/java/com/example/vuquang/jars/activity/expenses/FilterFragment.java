package com.example.vuquang.jars.activity.expenses;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.vuquang.jars.R;
import com.example.vuquang.jars.activity.model.Jar;

/**
 * Created by CPU10584-local on 02-Apr-18.
 */

public class FilterFragment extends Fragment {
    private static final String TAG = "HelpDialogFragment";

    private Jar.JarType type;
    private ImageButton imbMenu, imbBack, imbHelp;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_help, null);
        init(view);
        return view;
    }

    private void init(View view) {
        imbBack = view.findViewById(R.id.imv_navi_back);
//        imbBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dismiss();
//            }
//        });

        imbMenu = view.findViewById(R.id.imv_navi_menu);
        imbMenu.setVisibility(View.GONE);

        imbHelp = view.findViewById(R.id.imv_navi_help);
        imbHelp.setVisibility(View.GONE);
    }
}
