package com.example.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    //Variables
    EditText etItem;
    Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        etItem = findViewById(R.id.etItem);
        btnSave = findViewById((R.id.btnSave));

        getSupportActionBar().setTitle("Edit item");

        // Call Intent and set text to autofill when the edit is clicked
        etItem.setText(getIntent().getStringExtra(MainActivity.KEY_ITEM_TEXT));
        // Save our entry when we type in the edit
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent that will contain results of user edits
                Intent intent = new Intent();

                // Edits user made passed in
                intent.putExtra(MainActivity.KEY_ITEM_TEXT, etItem.getText().toString());
                intent.putExtra(MainActivity.KEY_ITEM_POSITION, getIntent().getExtras().getInt(MainActivity.KEY_ITEM_POSITION));
                // Set result of intent
                setResult(RESULT_OK, intent);
                // Finish activity (complete the edit and go back to add screen)
                finish(); // closes screen and goes back to MainActivity
            }
        });


    }
}