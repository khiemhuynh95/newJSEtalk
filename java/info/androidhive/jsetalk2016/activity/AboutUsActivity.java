package info.androidhive.jsetalk2016.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import info.androidhive.jsetalk2016.R;
import info.androidhive.jsetalk2016.other.AppWebViewClients;

public class AboutUsActivity extends AppCompatActivity {

    WebView about_us_view;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        about_us_view = (WebView) findViewById(R.id.about_us_html_view);
        progressBar = (ProgressBar) findViewById(R.id.progressBarAtAboutUs);
        String url = "file:///android_asset/about_us.html";
        loadFormUrl(url);
    }

    public void loadFormUrl(String url){
        WebSettings webSettings = about_us_view.getSettings();
        webSettings.setJavaScriptEnabled(true);

        about_us_view.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        about_us_view.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        about_us_view.getSettings().setAppCacheEnabled(true);

        about_us_view.getSettings().setBuiltInZoomControls(true);
        webSettings.setEnableSmoothTransition(true);
        webSettings.setSaveFormData(true);

        about_us_view.loadUrl(url);

        about_us_view.setWebViewClient(new AppWebViewClients(progressBar) {
            @Override
            public void onPageFinished(WebView view, String url) {
                // TODO Auto-generated method stub
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            // finish the activity
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
