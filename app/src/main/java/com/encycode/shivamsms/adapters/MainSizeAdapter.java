package com.encycode.shivamsms.adapters;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.encycode.shivamsms.EditStocks;
import com.encycode.shivamsms.MainActivity;
import com.encycode.shivamsms.R;
import com.encycode.shivamsms.database.Stocks;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainSizeAdapter extends RecyclerView.Adapter<MainSizeAdapter.MainSizeHolder> {

    private Context context;
    private Application application;

    public MainSizeAdapter(Context context, Application application) {
        this.context = context;
        this.application = application;
    }

    String[] sizes = new String[]{"160ml","200ml","250ml","1l","Mojito(250ml)"};
    private List<Stocks> allStocks = new ArrayList<>();

    @NonNull
    @Override
    public MainSizeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bottle_main_recycler_view_design,parent,false);
        return new MainSizeHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MainSizeAdapter.MainSizeHolder holder, int position) {

        //Toast.makeText(context, ""+allStocks.size(), Toast.LENGTH_SHORT).show();
        String current = sizes[position];
        List<Stocks> filtered = new ArrayList<>();
        for(int i=0;i<allStocks.size();i++) {
            if(allStocks.get(i).getSize().equals(current)) {
                filtered.add(allStocks.get(i));
            }
        }
        holder.sizeName.setText(current);
        holder.items.setLayoutManager(new LinearLayoutManager(context));
        holder.items.setHasFixedSize(true);

        SubAdapter adapter = new SubAdapter(application);
        holder.items.setAdapter(adapter);

        adapter.setAllStocks(filtered);
        holder.open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!((Boolean) holder.open.getTag())) {
                    holder.open.setImageResource(R.drawable.opened);
                    holder.header.setVisibility(View.VISIBLE);
                    holder.items.setVisibility(View.VISIBLE);
                    holder.open.setTag(new Boolean(true));
                }
                else if((Boolean) holder.open.getTag()) {
                    holder.open.setImageResource(R.drawable.closed);
                    holder.header.setVisibility(View.GONE);
                    holder.items.setVisibility(View.GONE);
                    holder.open.setTag(new Boolean(false));
                }
            }
        });

        holder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, EditStocks.class);
                i.putExtra("allStocks",(Serializable) filtered);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public void setAllStocks(List<Stocks> stocks) {
        //Toast.makeText(context, ""+stocks.size(), Toast.LENGTH_SHORT).show();
        allStocks = stocks;
        notifyDataSetChanged();
    }

    class MainSizeHolder extends RecyclerView.ViewHolder {

        private TextView sizeName;
        private Button editBtn;
        private RecyclerView items;
        private LinearLayout header;
        private ImageButton open;

        public MainSizeHolder(@NonNull View itemView) {
            super(itemView);
            sizeName = itemView.findViewById(R.id.sizeName);
            editBtn = itemView.findViewById(R.id.editBtn);
            items = itemView.findViewById(R.id.allItems);
            header = itemView.findViewById(R.id.header);
            open = itemView.findViewById(R.id.open);
            open.setTag(new Boolean(false));
        }
    }
}
