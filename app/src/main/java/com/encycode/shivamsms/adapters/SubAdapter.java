package com.encycode.shivamsms.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.encycode.shivamsms.R;
import com.encycode.shivamsms.database.Stocks;

import java.util.ArrayList;
import java.util.List;

public class SubAdapter extends RecyclerView.Adapter<SubAdapter.SubHolder> {

    List<Stocks> allStocks = new ArrayList<>();

    @NonNull
    @Override
    public SubHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler_view_design,parent,false);
        return new SubHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SubAdapter.SubHolder holder, int position) {
        Stocks current = allStocks.get(position);
        holder.srNo.setText(String.valueOf(position+1));
        holder.flavour.setText(current.getFlavour());
        holder.quantity.setText(String.valueOf(current.getQuantity()));
    }

    @Override
    public int getItemCount() {
        return allStocks.size();
    }

    public void setAllStocks(List<Stocks> allStocks) {
        this.allStocks = allStocks;
    }

    class SubHolder extends RecyclerView.ViewHolder {

        private TextView srNo,flavour,quantity;

        public SubHolder(@NonNull View itemView) {
            super(itemView);
            srNo = itemView.findViewById(R.id.srNo);
            flavour = itemView.findViewById(R.id.flavour);
            quantity = itemView.findViewById(R.id.quantity);
        }
    }
}
