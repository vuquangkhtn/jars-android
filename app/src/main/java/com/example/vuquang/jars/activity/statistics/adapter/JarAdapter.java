package com.example.vuquang.jars.activity.statistics.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vuquang.jars.R;
import com.example.vuquang.jars.activity.model.Jar;

import java.util.List;

/**
 * Created by CPU10584-local on 11-Apr-18.
 */

public class JarAdapter extends RecyclerView.Adapter<JarAdapter.JarHolder> {

    private List<Jar> listJar;
    private Context mContext;

    public JarAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<Jar> listData) {
        this.listJar = listData;
        notifyDataSetChanged();
    }

    @Override
    public JarHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new JarHolder(LayoutInflater.from(mContext).inflate(R.layout.item_part_jar, parent, false));
    }

    @Override
    public void onBindViewHolder(JarHolder holder, int position) {
        Jar jar = listJar.get(position);
        holder.imvIcon.setImageResource(jar.getResIcon());
        String name = jar.getName();
        holder.tvName.setText(name);
        holder.tvAmount.setText(String.valueOf(jar.getCurrentAmount())+" / "+String.valueOf(jar.totalAmount));
    }

    @Override
    public int getItemCount() {
        if (listJar != null) {
            return listJar.size();
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