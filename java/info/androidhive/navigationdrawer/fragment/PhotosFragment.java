package info.androidhive.navigationdrawer.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import info.androidhive.navigationdrawer.R;
import info.androidhive.navigationdrawer.other.GridViewAdapter;
import info.androidhive.navigationdrawer.other.ImageItem;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PhotosFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PhotosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PhotosFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PhotosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PhotosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PhotosFragment newInstance(String param1, String param2) {
        PhotosFragment fragment = new PhotosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private GridView gridView;
    private GridViewAdapter gridAdapter;
    View v;
    ProgressBar progressBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_photos, container, false);

        //change dynamically
        String album_id = "669739276539513";
        //can get the permanent token
        String access_token = "EAAIOULJXEmwBAM8cK6i71lO923hl7gpqZBTIoJzz6Nnfu38vLMTndPmc1R4xXgW95U2eFyfhzjJXppgB6BgZCYZAt8ZCSgzlhMU68IVxBdNxUzX6ZBPYkz13aDBiv7Y7HZBwtrZBuJ1uAq1sHE0nCM1diXM8hEzZCus1ZCs8v8Qb8ewZDZD";
        //give items list to here
        new RequestTask().execute("https://graph.facebook.com/v2.8/" + album_id + "/photos?fields=name,source&access_token=" + access_token);

        gridView = (GridView) v.findViewById(R.id.gridview_fb_img);
        progressBar = (ProgressBar) v.findViewById(R.id.progressBarAtPhoto);
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    ArrayList<ImageItem> imageItems = new ArrayList<>();
    class RequestTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String responseString = "";
            try {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(params[0])
                        .build();
                Response responses = null;

                try {
                    responses = client.newCall(request).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String jsonData = responses.body().string();
                JSONObject jobject = new JSONObject(jsonData);
                JSONArray jarray = jobject.getJSONArray("data");
                for (int i = 0; i < jarray.length(); i++) {
                    JSONObject object     = jarray.getJSONObject(i);
                    String imgUrl = object.getString("source");
                    //load img to imgview
                    URL u = new URL(imgUrl);
                    Bitmap bitmap = BitmapFactory.decodeStream(u.openConnection().getInputStream());
                    imageItems.add(new ImageItem(bitmap, i));
                    Log.d("IMGSRC", imgUrl);
                }
                responseString = jsonData;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return responseString;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            //Do anything with response..
            Log.d("ANSWER", String.valueOf(imageItems.size()));

            gridAdapter = new GridViewAdapter(getContext(), imageItems);
            gridView.setAdapter(gridAdapter);
            progressBar.setVisibility(View.GONE);
        }
    }
}
