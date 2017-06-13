package com.example.azhar.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by azhar on 5/31/2017.
 */

public class ImageListAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Image> imagesList;

    public ImageListAdapter(Context context, int layout, ArrayList<Image> imagesList) {
        this.context = context;
        this.layout = layout;
        this.imagesList = imagesList;
    }

    @Override
    public int getCount() {
        return imagesList.size();
    }

    @Override
    public Object getItem(int position) {
        return imagesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        ImageView imageView;
        TextView txtName, txtPrice;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();

        if (row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.txtName = (TextView) row.findViewById(R.id.txtName);
            holder.txtPrice = (TextView) row.findViewById(R.id.txtPrice);
            holder.imageView = (ImageView) row.findViewById(R.id.imgImage);
            row.setTag(holder);
        }
        else {
            holder = (ViewHolder) row.getTag();
        }

        Image image = imagesList.get(position);

        holder.txtName.setText(image.getName());
        holder.txtPrice.setText(image.getPrice());

        byte[] imageImage= image.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(imageImage,0, imageImage.length);
        holder.imageView.setImageBitmap(bitmap);

        return row;
    }
}
