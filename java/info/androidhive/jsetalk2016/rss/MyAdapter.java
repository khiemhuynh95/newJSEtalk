package info.androidhive.jsetalk2016.rss;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import info.androidhive.jsetalk2016.R;

/**
 * Created by Administrator on 11/27/2016.
 */

public class MyAdapter extends RecyclerView.Adapter<NewsfeedViewHolder> {
    Context c;
    ArrayList<FeedItem> articles;
    public MyAdapter(Context c, ArrayList<FeedItem> articles) {
        this.c = c;
        this.articles = articles;
    }
    @Override
    public NewsfeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(c).inflate(R.layout.rss_recyclerview_row,parent,false);
        return new NewsfeedViewHolder(v);
    }
    @Override
    public void onBindViewHolder(NewsfeedViewHolder holder, int position) {
        FeedItem article=articles.get(position);
        String title=article.getTitle();

        String imageUrl=article.getImg_url();
        holder.titleTxt.setText(title);

        Picasso.with(c).load(imageUrl).fit().into(holder.img);
//        PicassoClient.downloadImage(c,imageUrl,holder.img);
    }
    @Override
    public int getItemCount() {
        return articles.size();
    }
}