package com.nazimah.my_dictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nazimah.my_dictionary.data.DictionaryStoreDatabase;
import com.nazimah.my_dictionary.data.dao.DictionaryDao;
import com.nazimah.my_dictionary.data.entity.Dictionary;

public class AddActivity extends AppCompatActivity {

    EditText addWord, addRemark, addMeaning;
    Button btnSave;
    private DictionaryDao dictionaryDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        addWord=findViewById(R.id.addWord);
        addRemark=findViewById(R.id.addRemark);
        addMeaning=findViewById(R.id.addMeaning);
        btnSave=findViewById(R.id.btnSave);

        dictionaryDao = DictionaryStoreDatabase.getInstance(this).dictionaryDao();
        btnSave.setOnClickListener(view -> {

            Dictionary dictionary = new Dictionary();
            dictionary.word = addWord.getText().toString();
            dictionary.remark = addRemark.getText().toString();
            dictionary.meaning = addMeaning.getText().toString();
            dictionaryDao.save(dictionary);
            Toast.makeText(AddActivity.this, "saved", Toast.LENGTH_SHORT).show();

        });

    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
        super.onBackPressed();

    }
}
