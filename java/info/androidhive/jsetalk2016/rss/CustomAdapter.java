package info.androidhive.jsetalk2016.rss;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import info.androidhive.jsetalk2016.R;

/**
 * Created by Administrator on 10/25/2016.
 */
public class CustomAdapter extends ArrayAdapter<FeedItem> {

    public CustomAdapter(Context context, int resource, List<FeedItem> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view =  inflater.inflate(R.layout.rss_recyclerview_row, null);
        }
        FeedItem p = getItem(position);
        if (p != null) {
            // Anh xa + Gan gia tri
            TextView txttitle = (TextView) view.findViewById(R.id.textView_title);
            txttitle.setText(p.getTitle());

            ImageView imageView = (ImageView) view.findViewById(R.id.img_view_at_reclycler);
//            Glide.with(getContext()).load(p.img_url)
//                    .thumbnail(0.5f)
//                    .crossFade()
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .fitCenter()
//                    .into(imageView);

            Picasso.with(getContext()).load(p.getImg_url()).fit().into(imageView);
        }
        return view;
    }

}