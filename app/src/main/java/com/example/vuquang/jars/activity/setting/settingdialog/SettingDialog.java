package com.example.vuquang.jars.activity.setting.settingdialog;

import android.app.Dialog;
import android.content.Intent;
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
import android.widget.Button;
import android.widget.EditText;

import com.example.vuquang.jars.R;
import com.example.vuquang.jars.activity.base.BaseDialog;
import com.example.vuquang.jars.activity.data.AppDataManager;
import com.example.vuquang.jars.activity.main.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by VuQuang on 6/1/2018.
 */

public class SettingDialog extends BaseDialog implements SettingDialogMvpView {
    private static final String TAG = "SettingDialog";

    private EditText edtMonthlyIncome;
    private Button btnSave;
    private SettingDialogPresenter<SettingDialog> mPresenter;

    public static void show(FragmentManager fm) {
        SettingDialog dialog = newInstance();
        dialog.show(fm,TAG);
    }

    private static SettingDialog newInstance() {
        return new SettingDialog();
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
        View view = View.inflate(getActivity(), R.layout.dialog_setting, null);

        mPresenter = new SettingDialogPresenter<>(new AppDataManager(
                FirebaseDatabase.getInstance().getReference(),
                FirebaseAuth.getInstance()
        ));

        mPresenter.onAttach(SettingDialog.this);
        return view;
    }

    @Override
    protected void setUp(View view) {
        edtMonthlyIncome = view.findViewById(R.id.edt_total);
        btnSave = view.findViewById(R.id.btn_save);
        mPresenter.loadOldIncome();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.onBtnSaveClicked(Long.valueOf(String.valueOf(edtMonthlyIncome.getText())));
            }
        });

        edtMonthlyIncome.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkAllEdtFilled();
            }
        });
    }

    @Override
    public void goToMain() {
        dismissDialog(TAG);
        getBaseActivity().finish();
        Intent i = new Intent(getBaseActivity(), MainActivity.class);
        startActivity(i);
    }

    @Override
    public void updateIncome(long monthlyIncome) {
        if(monthlyIncome > 0) {
            edtMonthlyIncome.setText(String.valueOf(monthlyIncome));
        }
    }

    private void checkAllEdtFilled() {
        if(!TextUtils.isEmpty(edtMonthlyIncome.getText()))
        {
            btnSave.setEnabled(true);
        } else {
            btnSave.setEnabled(false);
        }
    }
}
