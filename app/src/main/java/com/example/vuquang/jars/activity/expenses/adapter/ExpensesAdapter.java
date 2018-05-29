package com.example.vuquang.jars.activity.expenses.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vuquang.jars.R;
import com.example.vuquang.jars.activity.data.db.model.Expense;

import java.util.List;

/**
 * Created by VuQuang on 4/13/2018.
 */

public class ExpensesAdapter extends RecyclerView.Adapter<ExpensesAdapter.ExpensesHolder> {
    private List<Expense> expenseList;
    private Context mContext;

    public ExpensesAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<Expense> listData) {
        this.expenseList = listData;
        notifyDataSetChanged();
    }

    @Override
    public ExpensesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ExpensesHolder(LayoutInflater.from(mContext).inflate(R.layout.item_part_jar, parent, false));
    }

    @Override
    public void onBindViewHolder(ExpensesHolder holder, int position) {
        Expense expense = expenseList.get(position);
        holder.imvIcon.setImageResource(expense.calculateType().getResIdIcon());
        holder.tvName.setText(expense.title);
        holder.tvAmount.setText(String.valueOf(expense.amount));
    }

    @Override
    public int getItemCount() {
        if (expenseList != null) {
            return expenseList.size();
        }
        return 0;
    }

    public class ExpensesHolder extends RecyclerView.ViewHolder {

        private ImageView imvIcon;
        private TextView tvName;
        private TextView tvAmount;
        public ExpensesHolder(View itemView) {
            super(itemView);
            imvIcon = itemView.findViewById(R.id.imv_icon);
            tvAmount = itemView.findViewById(R.id.tv_amount);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }
}