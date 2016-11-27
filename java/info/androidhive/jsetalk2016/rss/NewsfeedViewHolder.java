package info.androidhive.jsetalk2016.rss;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import info.androidhive.jsetalk2016.R;

/**
 * Created by Administrator on 11/27/2016.
 */

public class NewsfeedViewHolder extends RecyclerView.ViewHolder {
    TextView titleTxt;
    ImageView img;

    public NewsfeedViewHolder(View itemView) {
        super(itemView);
        titleTxt= (TextView) itemView.findViewById(R.id.textView_title);

        img= (ImageView) itemView.findViewById(R.id.img_view_at_reclycler);
    }
}