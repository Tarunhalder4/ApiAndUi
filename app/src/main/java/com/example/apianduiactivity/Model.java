package com.example.apianduiactivity;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mikepenz.fastadapter.items.AbstractItem;
import java.util.List;
import java.util.PrimitiveIterator;

public class Model extends AbstractItem<Model, Model.ViewHolder> {

    private String mImageUrl;
    private String id,type;
    private int price;

    private String TAG= "tag";


    public Model(String mImageUrl, String id, String type, int price) {
        this.mImageUrl = mImageUrl;
        this.id = id;
        this.type = type;
        this.price = price;
    }

    public Model withImage(String imageUrl) {
        this.mImageUrl = imageUrl;
        return this;
    }

    public Model withId(String id) {
        this.id = id;
        return this;
    }

    public Model withType(String type) {
        this.type = type;
        return this;
    }

    public Model withPrice(int price) {
        this.price = price;
        return this;
    }

    @Override
    public int getType() {
        return R.id.each_row_layout;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.each_row;
    }

    @Override
    public void bindView(Model.ViewHolder viewHolder, List<Object> payloads) {
        super.bindView(viewHolder, payloads);

        //get the context
        Context ctx = viewHolder.itemView.getContext();

        //define our data for the view
        viewHolder.id.setText("id:");
        viewHolder.idNumber.setText(id);
        viewHolder.price.setText(String.valueOf(price));
        viewHolder.type.setText(type);
       // viewHolder.imageView.setImageBitmap(null);

        //set the background for the item
        //int color = UIUtils.getThemeColor(ctx, R.attr.colorPrimary);
       // viewHolder.view.clearAnimation();
        //viewHolder.view.setForeground(FastAdapterUIUtils.getSelectablePressedBackground(ctx, FastAdapterUIUtils.adjustAlpha(color, 100), 50, true));

        //load glide
        //Glide.clear(viewHolder.imageView);
       // Log.d(TAG, "bindView: "+mImageUrl);

        Log.d(TAG, "bindView: "+"\""+mImageUrl+"\"");

       // String str="\"Rajesh\"";
        Glide.with(ctx).load(mImageUrl).centerCrop().placeholder(R.drawable.ic_launcher_background).into(viewHolder.imageView);

        if (type.equals("buy")){
            viewHolder.tagImage.setImageDrawable(ctx.getResources().getDrawable(R.drawable.label_red));
            viewHolder.type.setTextColor(ctx.getResources().getColor(R.color.white));
        }else {
            viewHolder.tagImage.setImageDrawable(ctx.getResources().getDrawable(R.drawable.label_yellow));
            viewHolder.type.setTextColor(ctx.getResources().getColor(R.color.black));
        }
    }

    @Override
    public void unbindView(ViewHolder holder) {
        super.unbindView(holder);
        //Glide.clear(holder.imageView);
        holder.imageView.setImageDrawable(null);
        holder.id.setText(null);
        holder.price.setText(null);
        holder.type.setText(null);
        holder.tagImage.setImageDrawable(null);
    }

    @Override
    public ViewHolder getViewHolder(View v) {
        return new ViewHolder(v);
    }


    protected static class ViewHolder extends RecyclerView.ViewHolder {
        //protected FrameLayout view;
        protected ImageView imageView, tagImage;
        protected TextView id, type,price,idNumber;

        public ViewHolder(View view) {
            super(view);
           // this.view = (FrameLayout) view;
            imageView = view.findViewById(R.id.ec_image);
            id = view.findViewById(R.id.ec_id);
            type = view.findViewById(R.id.ec_type);
            price = view.findViewById(R.id.ec_price);
            tagImage = view.findViewById(R.id.ec_image_tag);
            idNumber = view.findViewById(R.id.ec_id_number);
        }
    }
}