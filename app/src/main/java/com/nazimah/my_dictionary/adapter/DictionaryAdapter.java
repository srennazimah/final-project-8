package com.nazimah.my_dictionary.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nazimah.my_dictionary.DefinitionActivity;
import com.nazimah.my_dictionary.R;
import com.nazimah.my_dictionary.data.entity.Dictionary;

import java.util.List;

public class DictionaryAdapter extends RecyclerView.Adapter<DictionaryAdapter.ViewHolder> {

    List<Dictionary> dictionaries;
    private AdapterListener listener;
    Context context;

    public DictionaryAdapter(List<Dictionary> dictionaries,Context context, AdapterListener listener) {
        this.dictionaries = dictionaries;
        this.listener = listener;
        this.context = context;
    }

    public DictionaryAdapter(List<Dictionary> dictionaries, AdapterListener listener) {
        this.dictionaries = dictionaries;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.activity_item, parent, false
        );
//
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(dictionaries.get(position));
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, DefinitionActivity.class);
            intent.putExtra("dictionary", position);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return dictionaries.size();
    }

    public void remove(int position) {
        this.dictionaries.remove(position);
        notifyItemRemoved(position);
    }

    public void update(Dictionary dictionary, int position) {
        this.dictionaries.set(position, dictionary);
        notifyItemChanged(position);
    }

    public void addMoreItems(List<Dictionary> dictionary) {
        int previousItems = this.dictionaries.size();
        this.dictionaries.addAll(dictionary);
        notifyItemRangeInserted(previousItems, dictionaries.size());
    }

    public void reloadItems(List<Dictionary> dictionaries) {
        this.dictionaries.clear();
        this.dictionaries.addAll(dictionaries);
        notifyDataSetChanged();
    }

    public interface AdapterListener {

        void onButtonMoreClicked(View view, Dictionary dictionary, int adapterPosition);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView word;
        ImageView btnMore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            word = itemView.findViewById(R.id.word);
            btnMore = itemView.findViewById(R.id.btnMore);
        }

        void bind(final Dictionary dictionary) {
            word.setText(dictionary.word);
            btnMore.setOnClickListener(view -> {
                if (listener != null)
                    listener.onButtonMoreClicked(view, dictionary, getAdapterPosition());
            });
        }
    }

}
