package com.example.android.assignment10_sqllight_taite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class ItemListFragment extends Fragment {

    private static final String SAVED_SUBTITLE_VISIBLE = "subtitle_visible";
    public static final String EXTRA_ITEM_ID = "item_id";
    private RecyclerView itemRecyclerView;
    private ToDoAdapter adapter;

    public ItemListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        itemRecyclerView = (RecyclerView)view.findViewById(R.id.recycler_view_item);
        itemRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("TEST", "in on resume");
        updateUI();
    }

    public void updateUI() {
        ItemSet itemSet = ItemSet.getList(getActivity());
        List<Item> items = itemSet.getItems();
        Log.d("TEST", String.valueOf(items.size()));
        if(adapter == null) {
            adapter = new ToDoAdapter(items);
            itemRecyclerView.setAdapter(adapter);
        }
        else
        {
            adapter.setItems(items);
            adapter.notifyDataSetChanged();
        }
    }

    private class ToDoHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        public TextView titleTV;
        public TextView retailTV;
        public TextView saleTV;
        public TextView wholesaleTV;
        public TextView stockCountTV;
        private Item item;

        public ToDoHolder(View itemView) {
            super(itemView);
            titleTV = (TextView)itemView.findViewById(R.id.tv_item_title);
            retailTV = (TextView)itemView.findViewById(R.id.tv_item_retail);
            saleTV = (TextView)itemView.findViewById(R.id.tv_item_sale);
            wholesaleTV = (TextView)itemView.findViewById(R.id.tv_item_wholesale);
            stockCountTV = (TextView)itemView.findViewById(R.id.tv_item_instock);
            itemView.setOnClickListener(this);
        }

        public void bindToDo(Item item)
        {
            this.item = item;
            titleTV.setText(item.getItemName());
            retailTV.setText(String.valueOf(item.getRetailPrice()));
            saleTV.setText(String.valueOf(item.getSalePrice()));
            wholesaleTV.setText(String.valueOf(item.getWholesalePrice()));
            stockCountTV.setText(String.valueOf(item.getStockCount()));
        }

        @Override
        public void onClick(View view)
        {
            Intent intent = new Intent(getActivity(), ItemPagerActivity.class);
            intent.putExtra(EXTRA_ITEM_ID, item.getId());
            startActivity(intent);
        }
    }

    private class ToDoAdapter extends RecyclerView.Adapter<ToDoHolder>{
        private List<Item> items;

        public ToDoAdapter(List<Item> items) {
            this.items = items;
        }

        @Override
        public ToDoHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(
                    R.layout.list_i_item, parent, false);
            return new ToDoHolder(view);
        }

        @Override
        public void onBindViewHolder(ToDoHolder holder, int position)
        {
            Item item = items.get(position);
            holder.bindToDo(item);
        }

        @Override
        public int getItemCount()
        {
            return items.size();
        }

        public void setItems(List<Item> items)
        {
            this.items = items;
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_item_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId())
        {
            case R.id.menu_item_new_todo:
                Item i = new Item();
                ItemSet.getList(getActivity()).addItem(i);
                Intent intent = new Intent(getActivity(), ItemPagerActivity.class);
                intent.putExtra(EXTRA_ITEM_ID, i.getId());
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


}
