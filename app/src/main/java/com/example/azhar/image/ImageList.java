package com.example.azhar.image;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import java.util.ArrayList;

/**
 * Created by azhar on 5/31/2017.
 */

public class ImageList extends AppCompatActivity {

    GridView gridView;
    ArrayList<Image> list;
    ImageListAdapter adapter = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_list_activity);

        gridView = (GridView) findViewById(R.id.gridView);
        list = new ArrayList<>();
        adapter = new ImageListAdapter(this, R.layout.image_items, list);
        gridView.setAdapter(adapter);

        //get all data from sqlite
        Cursor cursor = MainActivity.sqLiteHelper.getData("SELECT * FROM IMAGE");
        list.clear();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String price = cursor.getString(2);
            byte[] image = cursor.getBlob(3);

            list.add(new Image(id, name, price, image));
        }
        adapter.notifyDataSetChanged();
    }
}
