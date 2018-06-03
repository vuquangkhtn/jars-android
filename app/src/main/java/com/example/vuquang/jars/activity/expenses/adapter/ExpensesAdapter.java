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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

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
        return new ExpensesHolder(LayoutInflater.from(mContext).inflate(R.layout.item_expense, parent, false));
    }

    @Override
    public void onBindViewHolder(ExpensesHolder holder, int position) {
        Expense expense = expenseList.get(position);
        holder.imvIcon.setImageResource(expense.calculateType().getResIdIcon());
        holder.tvName.setText(expense.title);
        holder.tvAmount.setText(String.valueOf(expense.amount));
        holder.tvDate.setText(String.valueOf("Date "+ getTimeFormat(expense.getCreatedAt(), "dd")));
        holder.tvHour.setText(getTimeFormat(expense.getCreatedAt(),"HH:mm"));
    }

    @Override
    public int getItemCount() {
        if (expenseList != null) {
            return expenseList.size();
        }
        return 0;
    }

    private String getTimeFormat(long createdAt, String format) {
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(createdAt);
        SimpleDateFormat format1 = new SimpleDateFormat(format, Locale.CHINA);
        return format1.format(cal.getTime());
    }

    public class ExpensesHolder extends RecyclerView.ViewHolder {

        private ImageView imvIcon;
        private TextView tvName;
        private TextView tvAmount;
        private TextView tvDate;
        private TextView tvHour;
        public ExpensesHolder(View itemView) {
            super(itemView);
            imvIcon = itemView.findViewById(R.id.imv_icon);
            tvAmount = itemView.findViewById(R.id.tv_amount);
            tvName = itemView.findViewById(R.id.tv_name);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvHour = itemView.findViewById(R.id.tv_hour);
        }
    }
}