package info.androidhive.jsetalk2016.rss;

/**
 * Created by Administrator on 10/25/2016.
 */
public class FeedItem {
    private String title;
    private String link;
    private String img_url;

    public FeedItem(String title, String link,String img_url) {
        this.title = title;
        this.link = link;
        this.img_url = img_url;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getImg_url() {
        return img_url;
    }
}
