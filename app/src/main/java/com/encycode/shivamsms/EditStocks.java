package com.encycode.shivamsms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.encycode.shivamsms.database.SViewModel;
import com.encycode.shivamsms.database.Stocks;

import java.util.ArrayList;
import java.util.List;

public class EditStocks extends AppCompatActivity {

    List<Stocks> stocksList = new ArrayList<>();
    Spinner flavours;
    String selectedText;
    TextView quantity;
    EditText changes;
    RadioGroup rg;
    Button submit;
    int index;
    SViewModel viewModel;
    ArrayList<String> array = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_stocks);

        Intent intent = getIntent();
        stocksList = (List<Stocks>) intent.getSerializableExtra("allStocks");
        viewModel = new SViewModel(getApplication());

        //Toast.makeText(this, ""+stocksList.size(), Toast.LENGTH_SHORT).show();

        flavours = findViewById(R.id.spinner);
        quantity = findViewById(R.id.quantity);
        rg = findViewById(R.id.rg);
        changes = findViewById(R.id.changes);
        submit = findViewById(R.id.submitBtn);

        for(int i=0;i<stocksList.size();i++) {
            array.add(stocksList.get(i).getFlavour());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, array);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        flavours.setAdapter(adapter);

        flavours.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedText = String.valueOf(flavours.getSelectedItem());
                for(int i=0;i<array.size();i++) {

                    if(array.get(i).equals(selectedText)) {
                        index = i;
                        break;
                    }

                }
                quantity.setText(String.valueOf(stocksList.get(index).getQuantity()));
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       if(rg.getCheckedRadioButtonId()==-1) {
                           Toast.makeText(EditStocks.this, "Please, Select Add or Remove", Toast.LENGTH_SHORT).show();
                       } if(changes.getText().toString().length() == 0) {
                            Toast.makeText(EditStocks.this, "Please Enter Number", Toast.LENGTH_SHORT).show();
                       } else {
                            Intent j = new Intent(EditStocks.this,MainActivity.class);
                            if(rg.getCheckedRadioButtonId() == R.id.addBtn) {

                                stocksList.get(index).setQuantity(Integer.parseInt(changes.getText().toString())+stocksList.get(index).getQuantity());
                                viewModel.update(stocksList.get(index));

                            }
                            if(rg.getCheckedRadioButtonId() == R.id.removeBtn) {

                                stocksList.get(index).setQuantity(stocksList.get(index).getQuantity()-Integer.parseInt(changes.getText().toString()));
                                viewModel.update(stocksList.get(index));

                            }
                            Toast.makeText(EditStocks.this, "Stock Updated", Toast.LENGTH_SHORT).show();
                            startActivity(j);
                            finish();
                        }

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




    }
}