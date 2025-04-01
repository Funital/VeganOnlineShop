package com.example.veganonlineshop;

import java.util.ArrayList;

public class CartRepo {
    public ArrayList<VeganThings> cartVegans  = new ArrayList<VeganThings>();

    public void addCartItems(VeganThings things )  {
        int cnt = 0;
        ArrayList<VeganThings> goodsList = cartVegans;
        VeganThings goods = things;
        VeganThings goodsQnt = new VeganThings();

        for (int i = 0; i<goodsList.size();i++) {
            goodsQnt = goodsList.get(i);
            if (goodsQnt.veganId.equals(things.veganId)) {
                cnt++;
                goodsQnt.quantity += 1;
            }
        }

        if (cnt == 0) {
            goods.quantity = 1;
            goods.isCheck = true;
            cartVegans.add(goods);
        }
    }

    public int countCartItems( ){
        int totalQuantity = 0;
        if (cartVegans != null) {
            for (int i=0; i< cartVegans.size(); i++) {
                totalQuantity += cartVegans.get(i).quantity;
            }
        }
        return totalQuantity;
    }

    public int grandTotalCartItems() {
        int totalPrice = 0;

        for (int i=0 ; i< cartVegans.size(); i++) {
            if (cartVegans.get(i).isCheck)
                totalPrice += cartVegans.get(i).price * cartVegans.get(i).quantity;
        }
        return totalPrice;
    }
}
