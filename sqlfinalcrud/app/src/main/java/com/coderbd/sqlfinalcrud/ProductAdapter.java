package com.coderbd.sqlfinalcrud;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.List;

public class ProductAdapter extends BaseAdapter {
    Context context;
    List<Product>productList;
    LayoutInflater layoutInflater;

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
        this.layoutInflater = layoutInflater;
    }


    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return productList.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = layoutInflater.inflate(R.layout.list, null);
        TextView pid=view.findViewById(R.id.pid);
        TextView pname=view.findViewById(R.id.name);
        TextView pqty =view.findViewById(R.id.qty);
        pid.setText(String.valueOf(productList.get(position).getId()));
        pname.setText(productList.get(position).getProductname());
        pqty.setText(String.valueOf(productList.get(position).getQuantity()));
        return view;
    }
}
