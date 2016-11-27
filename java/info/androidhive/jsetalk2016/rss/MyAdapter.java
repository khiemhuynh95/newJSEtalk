package info.androidhive.jsetalk2016.rss;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import info.androidhive.jsetalk2016.R;

/**
 * Created by Administrator on 11/27/2016.
 */

public class MyAdapter extends RecyclerView.Adapter<NewsfeedViewHolder>  {
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
    FeedItem article;
    @Override
    public void onBindViewHolder(NewsfeedViewHolder holder, int position) {
        article = articles.get(position);
        String title=article.getTitle();

        String imageUrl=article.getImg_url();
        holder.titleTxt.setText(title);

        Picasso.with(c).load(imageUrl).fit().into(holder.img);

    }
    @Override
    public int getItemCount() {
        return articles.size();
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }


    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private MyAdapter.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final MyAdapter.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
}