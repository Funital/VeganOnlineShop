package com.example.veganonlineshop;

import static com.example.veganonlineshop.CartActivity.cartUpdate;
import static com.example.veganonlineshop.MainActivity.cartRepositoryObj;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.veganonlineshop.R;
import com.example.veganonlineshop.VeganThings;


import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    interface OnSelectChangedListener {
        void onSelectChanged(ArrayList<VeganThings> item );
    }

    Context context;


    public CartAdapter(Context context) {
        this.context = context;
    }

    private CartAdapter.OnSelectChangedListener  selectChangedListener;

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_cart_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        VeganThings things = cartRepositoryObj.cartVegans.get(position);

        holder.cartTitle.setText(things.name);
        holder.cartPrice.setText(Integer.toString(things.price));
        holder.cartCheckBox.setChecked(things.isCheck);
        holder.cartQuantity.setText(Integer.toString(things.quantity));
        holder.cartSum.setText(Integer.toString(things.price * things.quantity));

        switch (things.veganId) {
            case "Vegan1":
                holder.cartPicture.setImageResource(R.drawable.clothes1);
                break;

            case "Vegan2":
                holder.cartPicture.setImageResource(R.drawable.clothes2);
                break;

            case "Vegan3":
                holder.cartPicture.setImageResource(R.drawable.clothes4);
                break;


            case "Vegan4":
                holder.cartPicture.setImageResource(R.drawable.food1);
                break;
        }

        holder.cartCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                things.isCheck = holder.cartCheckBox.isChecked();
                selectChangedListener.onSelectChanged(cartRepositoryObj.cartVegans);
            }
        });
        holder.cartDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("비건 상품 삭제");
                alertDialog.setMessage("선택한 비건 상품을 삭제하겠습니까?");

                alertDialog.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        VeganThings things1 = cartRepositoryObj.cartVegans.get(position);
                        cartRepositoryObj.cartVegans.remove(things1);

                        cartUpdate();
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, cartRepositoryObj.cartVegans.size());

                        dialogInterface.cancel();
                    }
                });
                alertDialog.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                alertDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartRepositoryObj.cartVegans.size();
    }


    void setOnSelectChangedListener( OnSelectChangedListener listener) {
        selectChangedListener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView cartPicture;
        TextView cartTitle;
        TextView cartPrice;
        CardView cartParentLayout;
        CheckBox cartCheckBox;
        ImageButton cartDelete;
        TextView cartQuantity;
        TextView cartSum;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cartPicture = itemView.findViewById(R.id.cart_pic);
            cartTitle = itemView.findViewById(R.id.title);
            cartPrice = itemView.findViewById(R.id.price);
            cartParentLayout = itemView.findViewById(R.id.cart_parent_layout);
            cartDelete = itemView.findViewById(R.id.cart_delete);

            cartCheckBox= itemView.findViewById(R.id.cart_item_checkbox);
            cartQuantity = itemView.findViewById(R.id.quantity);
            cartSum = itemView.findViewById(R.id.sum);
        }
    }
}
