package com.nazimah.my_dictionary;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.PopupMenu;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nazimah.my_dictionary.adapter.DictionaryAdapter;
import com.nazimah.my_dictionary.data.DictionaryStoreDatabase;
import com.nazimah.my_dictionary.data.dao.DictionaryDao;
import com.nazimah.my_dictionary.data.entity.Dictionary;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton btnAdd;
    private DictionaryDao dictionaryDao;
    private RecyclerView recyclerView;
    private DictionaryAdapter adapter;
    private int position;
    private static final int REQUEST_ADD_DICTIONARY_CODE = 10;
    private static final int REQUEST_EDIT_DICTIONARY_CODE=11;
    private static final String KEY_DICTIONARY = "dictionary";
    private Object object;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.floatingActionButton);
        dictionaryDao = DictionaryStoreDatabase.getInstance(this).dictionaryDao();
        recyclerView = findViewById(R.id.rvDictionary);
        setupRecyclerView();
        loadDictionaryList();

        btnAdd.setOnClickListener(view -> {
            Intent intent = new Intent(this,
                    AddActivity.class);
            startActivityForResult(intent, REQUEST_ADD_DICTIONARY_CODE);
        });
    }

    private void setupRecyclerView(){
        adapter = new DictionaryAdapter(new ArrayList<>(), (view, dictionary, adapterPosition) -> openPopupMenu(view, dictionary, position));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void loadDictionaryList(){
        adapter.addMoreItems(dictionaryDao.getDictionary());
    }

    private void reload(){
        adapter.reloadItems(dictionaryDao.getDictionary());
    }
    
    private void openPopupMenu(Object view, final Object dictionary, final int pos){
        position=pos;
        PopupMenu menu = new PopupMenu(this, (View) view);
        menu.inflate(R.menu.item);
        menu.setOnMenuItemClickListener(menuItem -> {
            switch (menuItem.getItemId()){
                case R.id.edit:
                    editDictionary((Dictionary) dictionary);
                    return true;
                case R.id.delete:
                    deleteDictionary((Dictionary) dictionary);
                    return true;
                    default:
                        return false;
            }
        });
    }

    private void editDictionary(Dictionary dictionary){
        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra(KEY_DICTIONARY, dictionary);
        startActivityForResult(intent, REQUEST_EDIT_DICTIONARY_CODE);
    }
    private void deleteDictionary(Dictionary dictionary){
        if (position >=0){
            dictionaryDao.delete(dictionary);
            adapter.remove(position);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && (requestCode==REQUEST_ADD_DICTIONARY_CODE || requestCode==REQUEST_EDIT_DICTIONARY_CODE)){
            reload();
        }
    }
}
