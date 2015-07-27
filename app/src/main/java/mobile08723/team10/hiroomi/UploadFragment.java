package mobile08723.team10.hiroomi;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


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
                
            }
        });
        return rootView ;
    }


}
