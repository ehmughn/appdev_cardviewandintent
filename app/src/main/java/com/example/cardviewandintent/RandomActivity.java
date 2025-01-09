package com.example.cardviewandintent;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class RandomActivity extends AppCompatActivity {

    Spinner spinner_dropDownMenu;
    String[] items_dropDown;
    String[] items_autoCompleteTextView;
    ArrayAdapter<String> adapter_dropDown;
    ArrayAdapter<String> adapter_autoCompleteTextView;
    AutoCompleteTextView autoCompleteTextView;
    Button button_toast;
    Button button_toastWithSnackbar;
    LayoutInflater inflater;
    View dialogView;
    Button dialogButton_okay;
    AlertDialog.Builder builder;
    AlertDialog alertDialog;
    Button popupDialog;
    Button button_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_random);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        spinner_dropDownMenu = findViewById(R.id.spinner_dropdown);
        items_dropDown = new String[]{"Option 1", "Option 2", "Option 3"};
        adapter_dropDown = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items_dropDown);
        adapter_dropDown.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_dropDownMenu.setAdapter(adapter_dropDown);
        spinner_dropDownMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Selected " + selectedItem, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "Selected nothing", Toast.LENGTH_SHORT).show();
            }
        });
        autoCompleteTextView = findViewById(R.id.autocompletetextview_sample);
        items_autoCompleteTextView = new String[]{"Android", "Java", "Kotlin", "Flutter", "Swift", "React Native"};
        adapter_autoCompleteTextView = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, items_autoCompleteTextView);
        autoCompleteTextView.setAdapter(adapter_autoCompleteTextView);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "You selected " + selectedItem,Toast.LENGTH_SHORT).show();
            }
        });
        button_toast = findViewById(R.id.button_toast);
        button_toast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "This is a TOAST",Toast.LENGTH_SHORT).show();
            }
        });
        button_toastWithSnackbar = findViewById(R.id.button_toastwithsnackbar);
        button_toastWithSnackbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(findViewById(android.R.id.content), "This is a SNACKBAR", Snackbar.LENGTH_LONG).setAction("CLICK ME", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "You clicked \"CLICK ME\"",Toast.LENGTH_SHORT).show();
                    }
                }).show();
            }
        });
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.custom_dialog, null);
        dialogButton_okay = dialogView.findViewById(R.id.button_dialog_okay);
        builder = new AlertDialog.Builder(this);
        builder.setView(dialogView).setCancelable(true);
        alertDialog = builder.create();
        dialogButton_okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Closed the popup dialog",Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });
        popupDialog = findViewById(R.id.button_popupdialog);
        popupDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.show();
            }
        });
        button_login = findViewById(R.id.button_login);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RandomActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}