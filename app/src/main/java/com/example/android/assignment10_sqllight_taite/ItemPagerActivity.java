package com.example.android.assignment10_sqllight_taite;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

/**
 * Created by Android on 11/9/2017.
 */

public class ItemPagerActivity extends AppCompatActivity {

    private static final String EXTRA_ITEM_ID = "item_id";
    private ViewPager viewPager;
    private List<Item> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_pager);
        viewPager = (ViewPager)findViewById(R.id.item_pager);
        items = ItemSet.getList(this).getItems();
        UUID itemId = (UUID)getIntent().getSerializableExtra(EXTRA_ITEM_ID);
        FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {

            @Override
            public Fragment getItem(int position) {
                Item item = items.get(position);
                return ItemFragment.newInstance(item.getId());
            }

            @Override
            public int getCount() {
                return items.size();
            }
        });

        for(int i = 0; i < items.size(); i++)
        {
            if(items.get(i).getId().equals(itemId))
            {
                viewPager.setCurrentItem(i);
                break;
            }
        }
    }

    public static Intent newIntent(Context packageContext, UUID itemId)
    {
        Intent intent = new Intent(packageContext, ItemPagerActivity.class);
        intent.putExtra(EXTRA_ITEM_ID, itemId);
        return intent;
    }

}
