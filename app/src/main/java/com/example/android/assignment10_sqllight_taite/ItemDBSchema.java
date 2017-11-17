package com.example.android.assignment10_sqllight_taite;

/**
 * Created by Android on 11/7/2017.
 */

public class ItemDBSchema {

    public static final class ItemTable {

        public static final String NAME = "items";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String RETAIL = "retail";
            public static final String WHOLESALE = "wholesale";
            public static final String SALE = "sale";
            public static final String COUNT = "count";
        }

    }

}
