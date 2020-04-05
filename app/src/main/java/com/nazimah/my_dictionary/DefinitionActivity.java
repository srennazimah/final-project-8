package com.nazimah.my_dictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.nazimah.my_dictionary.data.entity.Dictionary;

public class DefinitionActivity extends AppCompatActivity {
TextView wordText, definitionText, remarkText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definition);

        wordText = findViewById(R.id.textView);
        definitionText= findViewById(R.id.definitionText);
        remarkText = findViewById(R.id.remarkText);

        Dictionary dictionary = getIntent().getParcelableExtra("dictionary");
        wordText.setText(dictionary.getWord());
        remarkText.setText(dictionary.getRemark());
        definitionText.setText(dictionary.getMeaning());



    }
}
