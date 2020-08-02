package com.example.simpletodo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//Responsible for displaying data from the model into a row in the recycler view
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    public interface OnClickListener {
        void onItemClicked(int position);
    }

    public interface OnLongClickListener {
        void onItemLongClicked(int position);
    }

    //define a variable that can be used to reference all the other methods
    List<String> items;

    OnLongClickListener longClickListener;
    OnClickListener clickListener;

    //setting the member variable equal to the variable passed in by the constructor
    public ItemsAdapter(List<String> items, OnLongClickListener longClickListener, OnClickListener clickListener){
        this.items = items;
        this.longClickListener = longClickListener;
        this.clickListener = clickListener;
    }


    @NonNull
    @Override
    //responsible for creating a view
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //Use layout inflator to inflate a view
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);

        //wrap it inside a view holder and return it
        return new ViewHolder(todoView);
    }

    //responsible for binding data to a particular view holder

    @Override
    //takes data from a particular position and puts it into viewholder
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //Grab the item at the position
        String item = items.get(position);

        //Bind the item into the specified view holder
        holder.bind(item);
    }

    @Override
    //tells the Recycler View how many items are in the list
    public int getItemCount() {
        return items.size();
    }

    //Container to provide easy access to views that represent each row of the list
    class ViewHolder extends RecyclerView.ViewHolder {

        //define a text view
        TextView tvItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        }

        //update the view inside of the view holder with the data of string item
        public void bind(String item) {
            tvItem.setText(item);
            tvItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onItemClicked(getAdapterPosition());
                }
            });

            tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    //Notify the listener which position was long pressed.
                    longClickListener.onItemLongClicked(getAdapterPosition());

                    //return false;
                    return true;
                }
            });

            }
        }


}


