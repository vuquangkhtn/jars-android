package com.example.vuquang.jars.activity.expenses;

import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;

import com.example.vuquang.jars.R;
import com.example.vuquang.jars.activity.fragments.HelpDialogFragment;

/**
 * Created by CPU10584-local on 02-Apr-18.
 */

public class AddExpenseFragment extends DialogFragment {
    private static final String TAG = "AddExpenseFragment";

    private ImageButton imbMenu, imbBack, imbHelp;

    public static void show(FragmentManager fm) {
        AddExpenseFragment dialog = newInstance();
        dialog.show(fm,TAG);
    }

    private static AddExpenseFragment newInstance() {
        Bundle args = new Bundle();
        AddExpenseFragment dialog = new AddExpenseFragment();
        dialog.setArguments(args);
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getContext(), R.style.DialogNoAnimation);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_add_expense, null);
        init(view);
        return view;
    }

    private void init(View view) {
        imbBack = view.findViewById(R.id.imv_navi_back);
        imbBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        imbMenu = view.findViewById(R.id.imv_navi_menu);
        imbMenu.setVisibility(View.GONE);

        imbHelp = view.findViewById(R.id.imv_navi_help);
        imbHelp.setVisibility(View.GONE);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        }
    }
}
