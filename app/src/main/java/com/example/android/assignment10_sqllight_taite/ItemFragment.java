package com.example.android.assignment10_sqllight_taite;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.Date;
import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class ItemFragment extends Fragment {

    private static final String ARGS_ITEM_ID = "item_id";
    private Item item;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID itemId = (UUID)getArguments().getSerializable(ARGS_ITEM_ID);
        item = (ItemSet.getList(getActivity())).getItem(itemId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState)
    {
        View view =  inflater.inflate(R.layout.fragment_item, parent, false);
        initTitleET(view);
        initRetailET(view);
        initSaleET(view);
        initWholeET(view);
        initStockCount(view);
        return view;
    }

    public void initTitleET(View view)
    {
        EditText titleET = (EditText) view.findViewById(R.id.itemNameID);
        titleET.setText(item.getItemName());
        titleET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(
                    CharSequence text, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                item.setItemName(editable.toString());
            }
        });
    }

    public void initRetailET(View view)
    {
        EditText retailET = (EditText) view.findViewById(R.id.retailPID);
        retailET.setText(String.valueOf(item.getRetailPrice()));
        retailET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(
                    CharSequence text, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    item.setRetailPrice(Float.parseFloat(editable.toString()));
                }catch(NumberFormatException nfe){
                    item.setRetailPrice(0);
                }
            }
        });
    }

    public void initSaleET(View view)
    {
        EditText saleET = (EditText) view.findViewById(R.id.salePID);
        saleET.setText(String.valueOf(item.getSalePrice()));
        saleET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(
                    CharSequence text, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //convert to float
                try {
                    item.setSalePrice(Float.parseFloat(editable.toString()));
                }catch(NumberFormatException nfe){
                    item.setSalePrice(0);
                }
            }
        });
    }

    public void initWholeET(View view)
    {
        EditText wholeET = (EditText) view.findViewById(R.id.wholesalePID);
        wholeET.setText(String.valueOf(item.getWholesalePrice()));
        wholeET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(
                    CharSequence text, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    item.setWholesalePrice(Float.parseFloat(editable.toString()));
                }catch(NumberFormatException nfe){
                    item.setWholesalePrice(0);
                }
            }
        });
    }

    public void initStockCount(View view)
    {
        EditText stockET = (EditText) view.findViewById(R.id.stockCountID);
        stockET.setText(String.valueOf(item.getStockCount()));
        stockET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(
                    CharSequence text, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //convert to float
                if(editable.toString().equals("")){
                    item.setStockCount(0);
                }
                else{
                    try {
                        item.setStockCount(Integer.parseInt((String.valueOf(editable)).trim()));
                    }catch(NumberFormatException nfe){
                        item.setStockCount(0);
                    }
                }
            }
        });
    }

    public static ItemFragment newInstance(UUID itemId)
    {
        Bundle args = new Bundle();
        args.putSerializable(ARGS_ITEM_ID, itemId);
        ItemFragment itemFragment = new ItemFragment();
        itemFragment.setArguments(args);
        return itemFragment;
    }

    @Override
    public void onPause() {
        super.onPause();

        ItemSet.getList(getActivity()).updateItem(item);
    }


}
