package mobile08723.team10.hiroomi;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parse.ParseObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class UploadFragment extends Fragment {


    public UploadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_upload, container, false);

        rootView.findViewById(R.id.upload_apartment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("HiRoomi","Clicked");
                ParseObject postInfo = new ParseObject("PostInfo");
                postInfo.put("UserName", "quriola");
                postInfo.put("FromYear", 2015);
                postInfo.put("FromMonth", 9);
                postInfo.put("FromDay", 1);
                postInfo.put("ToYear", 2016);
                postInfo.put("ToMonth", 9);
                postInfo.put("ToDay", 1);
                postInfo.put("NeedTenant", 1);
                postInfo.put("Longitude", 40.4778941);
                postInfo.put("Latitude", -79.9041036);
                postInfo.put("Price", 700);
                postInfo.put("Address", "5030 Centre Ave");
                postInfo.put("Title", "CMU Mobile Apartment");
                postInfo.saveInBackground();

            }
        });
        return rootView ;
    }


}
