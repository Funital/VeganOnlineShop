package com.example.veganonlineshop;

import static com.example.veganonlineshop.MainActivity.cartRepositoryObj;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
        Context context;

        public OrderAdapter(Context context) {
                this.context = context;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.recyclerview_order_item, parent, false);
                return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                VeganThings things = cartRepositoryObj.cartVegans.get(position);
                String str;

                if (things.name.length() > 20) str = things.name.substring(20) + "...";
                else str = things.name;
                holder.orderTitle.setText(str);
                holder.orderPrice.setText(Integer.toString(things.price));
                holder.orderQuantity.setText(Integer.toString(things.quantity));
                holder.orderSum.setText(Integer.toString(things.price * things.quantity));
        }

        @Override
        public int getItemCount() {
                return cartRepositoryObj.cartVegans.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
                TextView orderTitle;
                TextView orderPrice;
                TextView orderQuantity;
                TextView orderSum;

                public ViewHolder(@NonNull View itemView) {
                        super(itemView);
                        orderTitle = itemView.findViewById(R.id.title);
                        orderPrice = itemView.findViewById(R.id.price);
                        orderQuantity = itemView.findViewById(R.id.quantity);
                        orderSum = itemView.findViewById(R.id.sum);
                }
        }
}
