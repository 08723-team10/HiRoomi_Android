package mobile08723.team10.hiroomi;


import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class UploadFragment extends Fragment {


    EditText address;
    EditText title;
    EditText description;
    EditText tenants;
    EditText fromYear;
    EditText fromMonth;
    EditText fromDay;
    EditText toYear;
    EditText toMonth;
    EditText toDay;
    EditText price;

    Button addPhotos;
    Button upload;

    static Button startdate;
    static Button enddate;

    static int FromYear, FromMonth, FromDay, ToYear, ToMonth, ToDay;

    ParseUser user;
    String username;
    public UploadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_upload, container, false);

        //View rootView =  inflater.inflate(R.layout.fragment_upload, container, false);
        address = (EditText)rootView.findViewById(R.id.address);
        title = (EditText)rootView.findViewById(R.id.title);
        description = (EditText)rootView.findViewById(R.id.description);
        tenants = (EditText)rootView.findViewById(R.id.tenants);
//        fromYear = (EditText)rootView.findViewById(R.id.startYear);
//        fromMonth = (EditText)rootView.findViewById(R.id.startMonth);
//        fromDay = (EditText)rootView.findViewById(R.id.startDay);
//        toYear = (EditText)rootView.findViewById(R.id.endYear);
//        toMonth = (EditText)rootView.findViewById(R.id.endMonth);
//        toDay = (EditText)rootView.findViewById(R.id.endDay);
        price = (EditText)rootView.findViewById(R.id.price);

        addPhotos = (Button) rootView.findViewById(R.id.addPhotoButton);
        upload = (Button) rootView.findViewById(R.id.upload_apartment);

        user = ParseUser.getCurrentUser();

        startdate = (Button)rootView.findViewById(R.id.startdate);
        enddate = (Button)rootView.findViewById(R.id.enddate);

        rootView.findViewById(R.id.upload_apartment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("HiRoomi", "Clicked");
                Log.d("HiRoomi", address.getText().toString());
                ParseObject postInfo = new ParseObject("PostInfo");
//                postInfo.put("UserName", "quriola");
                postInfo.put("UserName", user.getUsername());
                postInfo.put("Address", address.getText().toString());
                postInfo.put("FromYear", FromYear);
                postInfo.put("FromMonth", FromMonth);
                postInfo.put("FromDay", FromDay);
                postInfo.put("ToYear", ToYear);
                postInfo.put("ToMonth", ToMonth);
                postInfo.put("ToDay", ToDay);

                postInfo.put("Title", title.getText().toString());
                postInfo.put("description", description.getText().toString());
                postInfo.put("NeedTenant", Integer.parseInt(tenants.getText().toString()));
                postInfo.put("Price", Integer.parseInt(price.getText().toString()));
                postInfo.saveInBackground();
//                ParseObject postInfo = new ParseObject("PostInfo");
//                postInfo.put("UserName", "quriola");
//                postInfo.put("FromYear", 2015);
//                postInfo.put("FromMonth", 9);
//                postInfo.put("FromDay", 1);
//                postInfo.put("ToYear", 2016);
//                postInfo.put("ToMonth", 9);
//                postInfo.put("ToDay", 1);
//                postInfo.put("NeedTenant", 1);
//                postInfo.put("Longitude", 40.4778941);
//                postInfo.put("Latitude", -79.9041036);
//                postInfo.put("Price", 700);
//                postInfo.put("Address", "5030 Centre Ave");
//                postInfo.put("Title", "CMU Mobile Apartment");
//                postInfo.saveInBackground();

            }
        });

        startdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(UploadFragment.this.getActivity().getFragmentManager(), "datePicker");

            }
        });
        enddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment1();
                newFragment.show(UploadFragment.this.getActivity().getFragmentManager(), "datePicker");

            }
        });
        return rootView ;
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(this.getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            startdate.setText(String.format("%d/%d/%d", day, month+1, year));
            FromYear = year;
            FromMonth = month+1;
            FromDay = day;
        }
    }
    public static class DatePickerFragment1 extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {



        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(this.getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            enddate.setText(String.format("%d/%d/%d", day, month+1, year));
            ToYear = year;
            ToMonth = month+1;
            ToDay = day;
        }
    }

}

