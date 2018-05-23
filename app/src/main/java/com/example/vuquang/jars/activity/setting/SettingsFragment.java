package com.example.vuquang.jars.activity.setting;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.vuquang.jars.R;
import com.example.vuquang.jars.activity.base.BaseFragment;
import com.example.vuquang.jars.activity.data.AppDataManager;
import com.example.vuquang.jars.activity.statistics.StatisticsMvpView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends BaseFragment implements SettingMvpView {

    private EditText edtMonthlyIncome;
    private Button btnSave;

    private SettingPresenter<SettingMvpView> mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = View.inflate(getActivity(), R.layout.fragment_settings, null);
        mPresenter = new SettingPresenter<>(new AppDataManager(
                FirebaseDatabase.getInstance().getReference(),
                FirebaseAuth.getInstance()
        ));

        mPresenter.onAttach(this);
        return view;
    }

    @Override
    protected void setUp(View view) {
        edtMonthlyIncome = view.findViewById(R.id.edt_total);
        btnSave = view.findViewById(R.id.btn_save);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.onSaveIncomeClicked(Long.valueOf(String.valueOf(edtMonthlyIncome.getText())));
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

    private void checkAllEdtFilled() {
        if(!TextUtils.isEmpty(edtMonthlyIncome.getText()))
        {
            btnSave.setEnabled(true);
        } else {
            btnSave.setEnabled(false);
        }
    }
}
