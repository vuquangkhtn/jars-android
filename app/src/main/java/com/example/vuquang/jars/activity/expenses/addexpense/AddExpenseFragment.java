package com.example.vuquang.jars.activity.expenses.addexpense;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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

import com.example.vuquang.jars.R;
import com.example.vuquang.jars.activity.base.BaseDialog;
import com.example.vuquang.jars.activity.data.AppDataManager;
import com.example.vuquang.jars.activity.data.db.model.JarType;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by CPU10584-local on 09-Apr-18.
 */

public class AddExpenseFragment extends BaseDialog implements AddExpenseMvpView {
    private static final String TAG = "AddExpenseFragment";
    private ImageButton imbMenu, imbBack, imbHelp;

    private EditText edtAmount, edtTitle;
    private Spinner spinJarType;
    private Button btnAddExpense;

    private AddExpensePresenter<AddExpenseMvpView> mPresenter;

    public static void show(FragmentManager fm) {
        AddExpenseFragment dialog = newInstance();
        dialog.show(fm,TAG);
    }

    private static AddExpenseFragment newInstance() {
        AddExpenseFragment dialog = new AddExpenseFragment();
        return dialog;
    }

    @Override
    protected void setUp(View view) {
        imbBack = view.findViewById(R.id.imv_navi_back);
        imbBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.onBackPressed();
            }
        });

        imbMenu = view.findViewById(R.id.imv_navi_menu);
        imbMenu.setVisibility(View.GONE);

        imbHelp = view.findViewById(R.id.imv_navi_help);
        imbHelp.setVisibility(View.GONE);

        edtAmount = view.findViewById(R.id.edt_amount);
        edtAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mPresenter.onCheckAllEdtFilled();
            }
        });

        edtTitle = view.findViewById(R.id.edt_title);
        edtTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mPresenter.onCheckAllEdtFilled();
            }
        });

        spinJarType = view.findViewById(R.id.spin_jar_type);
        List<String> typeList = new ArrayList<>(Arrays.asList(JarType.nameList));
        typeList.remove(typeList.size()-1);//remove all type
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                getContext(),
                R.layout.item_spinner,
                typeList);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.item_spinner_dropdown);
        spinJarType.setAdapter(spinnerArrayAdapter);

        btnAddExpense = view.findViewById(R.id.btn_add_expenses);
        btnAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = String.valueOf(edtTitle.getText());
                Long amount = Long.valueOf(String.valueOf(edtAmount.getText()));
                String type = (String) spinJarType.getSelectedItem();
                mPresenter.onAddExpenseClicked(amount, title, type);
            }
        });

        mPresenter.onCheckAllEdtFilled();
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

        mPresenter = new AddExpensePresenter<>(new AppDataManager(
                FirebaseDatabase.getInstance().getReference(),
                FirebaseAuth.getInstance()
        ));

        mPresenter.onAttach(AddExpenseFragment.this);
        return view;
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

    @Override
    public void goToMain() {
        dismissDialog("AddExpense");
    }

    @Override
    public void checkAllEdtFilled() {
        if(!TextUtils.isEmpty(edtTitle.getText())
                && !TextUtils.isEmpty(edtAmount.getText()))
        {
            btnAddExpense.setEnabled(true);
        } else {
            btnAddExpense.setEnabled(false);
        }
    }
}
