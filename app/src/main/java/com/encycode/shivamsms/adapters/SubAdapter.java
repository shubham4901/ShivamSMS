package com.encycode.shivamsms.adapters;

import android.app.Application;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.encycode.shivamsms.R;
import com.encycode.shivamsms.database.SViewModel;
import com.encycode.shivamsms.database.Stocks;

import java.util.ArrayList;
import java.util.List;

public class SubAdapter extends RecyclerView.Adapter<SubAdapter.SubHolder> {

    List<Stocks> allStocks = new ArrayList<>();
    Application application;
    SViewModel viewModel;

    public SubAdapter(Application application) {
        this.application = application;
    }

    @NonNull
    @Override
    public SubHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler_view_design,parent,false);
        viewModel = new SViewModel(application);
        return new SubHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SubAdapter.SubHolder holder, int position) {
        Stocks current = allStocks.get(position);
        holder.srNo.setText(String.valueOf(position+1));
        holder.flavour.setText(current.getFlavour());
        holder.quantity.setText(String.valueOf(current.getQuantity()));

        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current.setQuantity(current.getQuantity()+10);
                viewModel.update(current);
                notifyDataSetChanged();
            }
        });

        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(current.getQuantity() != 0) {
                    current.setQuantity(current.getQuantity() - 1);
                    viewModel.update(current);
                    notifyDataSetChanged();
                }
                else {
                    Toast.makeText(application, "Cannot be less than 0", Toast.LENGTH_SHORT).show();
                }
            }
        });
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
        private Button plus,minus;

        public SubHolder(@NonNull View itemView) {
            super(itemView);
            srNo = itemView.findViewById(R.id.srNo);
            flavour = itemView.findViewById(R.id.flavour);
            quantity = itemView.findViewById(R.id.quantity);
            plus = itemView.findViewById(R.id.plus);
            minus = itemView.findViewById(R.id.minus);

        }
    }
}
