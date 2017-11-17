package com.example.android.assignment10_sqllight_taite;

import android.support.v4.app.Fragment;

/**
 * Created by Android on 11/9/2017.
 */

public class ItemListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new ItemListFragment();
    }

}
