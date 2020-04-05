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

public class EditActivity extends AppCompatActivity {


    EditText editWord, editRemark, editMeaning;
    Button btnUpdate, btnCancel;
    private Dictionary dictionary;
    private DictionaryDao dictionaryDao;
    private static final String KEY_DICTIONARY = "dictionary";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);


        editWord= findViewById(R.id.editWord);
        editRemark = findViewById(R.id.editRemark);
        editMeaning = findViewById(R.id.editMeaning);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnCancel = findViewById(R.id.btnCancel);

        dictionaryDao= DictionaryStoreDatabase.getInstance(this).dictionaryDao();

        bind();

        btnUpdate.setOnClickListener(view -> { updateDictionary();});
    }

    private void bind(){
        Intent intent = getIntent();
        if (intent != null){

            dictionary=intent.getParcelableExtra(KEY_DICTIONARY);
            editWord.setText(dictionary.word);
            editRemark.setText(dictionary.remark);
            editMeaning.setText(dictionary.meaning);
        }
    }
    private void updateDictionary(){
        if (dictionary!= null){
            dictionary.word= editWord.getText().toString();
            dictionary.remark = editRemark.getText().toString();
            dictionary.meaning = editMeaning.getText().toString();
            dictionaryDao.update(dictionary);

            Toast.makeText(this, "updated", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
        super.onBackPressed();
    }
}
