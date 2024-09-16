package com.example.sprintmobile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<String> itemList;
    private List<String> itemListFull; // Lista completa para restaurar após a filtragem

    public MyAdapter(List<String> itemList) {
        this.itemList = itemList;
        itemListFull = new ArrayList<>(itemList); // Copia a lista original
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String currentItem = itemList.get(position);
        holder.textView.setText(currentItem);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void filter(String text) {
        itemList.clear();
        if (text.isEmpty()) {
            itemList.addAll(itemListFull); // Restaura a lista completa se a pesquisa estiver vazia
        } else {
            String searchText = text.toLowerCase();
            for (String item : itemListFull) {
                if (item.toLowerCase().contains(searchText)) {
                    itemList.add(item); // Adiciona os itens que correspondem à pesquisa
                }
            }
        }
        notifyDataSetChanged(); // Atualiza a lista exibida
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }
    }
}
