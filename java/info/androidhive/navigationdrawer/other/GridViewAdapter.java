package info.androidhive.navigationdrawer.other;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

import info.androidhive.navigationdrawer.R;

/**
 * Created by Administrator on 11/15/2016.
 */

public class GridViewAdapter extends BaseAdapter {

    private ArrayList<ImageItem>data = new ArrayList();
    private final LayoutInflater mInflater;


    public GridViewAdapter(Context context, ArrayList<ImageItem> data) {
        mInflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public ImageItem getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ImageView fb_img;

        if (v == null) {
            v = mInflater.inflate(R.layout.grid_image_item, parent, false);
            v.setTag(R.id.fb_img_view, v.findViewById(R.id.fb_img_view));

        }

        fb_img = (ImageView) v.getTag(R.id.fb_img_view);

        final ImageItem item = getItem(position);

        fb_img.setImageBitmap(item.getImage());
        return v;
    }

}