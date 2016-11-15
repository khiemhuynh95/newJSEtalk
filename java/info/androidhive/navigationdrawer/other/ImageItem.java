package info.androidhive.navigationdrawer.other;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 11/15/2016.
 */

public class ImageItem {
    private Bitmap image;
    private int id;

    public ImageItem(Bitmap image, int id) {
        super();
        this.image = image;
        this.id = id;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setTitle(int id) {
        this.id = id;
    }
}
