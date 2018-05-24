package com.example.vuquang.jars.activity.expenses.addexpense;

import android.app.Dialog;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.example.vuquang.jars.R;
import com.example.vuquang.jars.activity.data.db.model.JarType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by CPU10584-local on 09-Apr-18.
 */

public class AddExpenseFragment extends DialogFragment {
    private static final String TAG = "AddExpenseFragment";
    private ImageButton imbMenu, imbBack, imbHelp;

    private EditText edtAmount, edtTitle;
    private Spinner spinJarType;
    private Button btnAddExpense;

    public static void show(FragmentManager fm) {
        AddExpenseFragment dialog = newInstance();
        dialog.show(fm,TAG);
    }

    private static AddExpenseFragment newInstance() {
        AddExpenseFragment dialog = new AddExpenseFragment();
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

        edtAmount = view.findViewById(R.id.edt_amount);
        edtTitle = view.findViewById(R.id.edt_title);

        spinJarType = view.findViewById(R.id.spin_jar_type);
        List<String> typeList = new ArrayList<>(Arrays.asList(JarType.nameList));
        typeList.remove(typeList.size()-1);//remove all type
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                typeList);
        spinJarType.setAdapter(spinnerArrayAdapter);

        btnAddExpense = view.findViewById(R.id.btn_add_expenses);
        btnAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
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
