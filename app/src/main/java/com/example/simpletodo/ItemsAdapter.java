package com.example.simpletodo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// responsible for displaying data from model into a row in the recycler view
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder>{

    //Define a public interface
    public interface OnClickListener {
        void onItemCLicked(int position);
    }
    public interface OnLongClickListener {
        //Notify adapter where to delete
        void onItemLongClicked(int position);
    }
    List<String> items;
    OnLongClickListener longClickListener;
    OnClickListener clickListener;

    public ItemsAdapter(List<String> items, OnLongClickListener longClickListener, OnClickListener clickListener) {
        this.items = items;
        this.longClickListener = longClickListener;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    //reponsible for creating each view
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Use layout inflator to inflate view
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        // wrap the inflated view inside a viewholder and return it
        return new ViewHolder(todoView);
    }

    @Override
    // responsible for taking data and putting into a viewholder
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // grab item at the position
        String item = items.get(position);
        // bind item into view holder
        holder.bind(item);
        

    }

    //tells how many items are in the list
    @Override
    public int getItemCount() {
        return items.size();
    }

    //create a container to access views easily
    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvItem;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        }

        // Update view inside of the view holder with the data
        public void bind(String item) {
            tvItem.setText(item);
            tvItem.setOnClickListener((new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Invoke method on interface & implement on specified position
                    clickListener.onItemCLicked(getAdapterPosition());
                }
            }));

            tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    // Notify the listener which position was long pressed.
                    longClickListener.onItemLongClicked(getAdapterPosition());
                    return false;
                }
            });
        }
    }
}
