package com.example.vuquang.jars.activity.statistics.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vuquang.jars.R;
import com.example.vuquang.jars.activity.data.db.model.JarType;
import com.example.vuquang.jars.activity.data.db.model.MonthlyHistory;

/**
 * Created by CPU10584-local on 11-Apr-18.
 */

public class JarAdapter extends RecyclerView.Adapter<JarAdapter.JarHolder> {

    private MonthlyHistory mHistory;
    private Context mContext;

    public JarAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(MonthlyHistory history) {
        this.mHistory = history;
        notifyDataSetChanged();
    }

    @Override
    public JarHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new JarHolder(LayoutInflater.from(mContext).inflate(R.layout.item_part_jar, parent, false));
    }

    @Override
    public void onBindViewHolder(JarHolder holder, int position) {
        JarType type = JarType.values()[position];
        holder.imvIcon.setImageResource(type.getResIdIcon());
        String name = type.getName();
        holder.tvName.setText(name);
        long curAmount = mHistory.calculateCurrentAmountBy(type);
        long totalIncome = mHistory.calculateTotalIncomeBy(type);
        if(curAmount > totalIncome) {
            holder.tvAmount.setTextColor(Color.RED);
        }
        holder.tvAmount.setText(String.valueOf(curAmount + " / "
                + totalIncome));
    }

    @Override
    public int getItemCount() {
        if (mHistory != null && mHistory.expenseList != null) {
            return JarType.values().length - 1;
        }
        return 0;
    }

    public class JarHolder extends RecyclerView.ViewHolder {

        private ImageView imvIcon;
        private TextView tvName;
        private TextView tvAmount;

        public JarHolder(View itemView) {
            super(itemView);
            imvIcon = itemView.findViewById(R.id.imv_icon);
            tvAmount = itemView.findViewById(R.id.tv_amount);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }
}