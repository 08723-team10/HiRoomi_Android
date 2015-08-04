package mobile08723.team10.hiroomi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;


public class DetailActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();


        //ParseProxyObject ppo = (ParseProxyObject) intent.getSerializableExtra("po");
        setTitle(intent.getStringExtra("title"));

        ParseQuery<ParseObject> query = ParseQuery.getQuery("PostInfo");
        query.whereEqualTo("objectId", intent.getStringExtra("id"));
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> apList, ParseException e) {
                if (e == null) {
                    Log.d("HiRoomi", "Retrieved " + apList.size() + " Infos");
                    for (ParseObject ap : apList) {
                        setDetail(ap);

                    }
                } else {
                    Log.d("HiRoomi", "Error: " + e.getMessage());
                }
            }
        });
//        ParseFile image = (ParseFile) ppo.getValues().get("Image");
//        ImageView image_expert = (ImageView)findViewById(R.id.imageView);
//        displayImage(image, image_expert);
        // Add and download the image
//        ParseFile imageFile = (ParseFile)ppo.getValues().get("Image");
//
//        ParseImageView todoImage = (ParseImageView) this.findViewById(R.id.icon);
//        ParseFile imageFile = ppo.getFile("Image");
//        if (imageFile != null) {
//            todoImage.setParseFile(imageFile);
//            todoImage.loadInBackground();
//        }
    }

    public void setDetail (ParseObject po){
        ParseFile imageFile = (ParseFile)po.getParseFile("Image");

        ParseImageView todoImage = (ParseImageView) this.findViewById(R.id.icon);

        if (imageFile != null) {
            todoImage.setParseFile(imageFile);
            todoImage.loadInBackground();
        }
        TextView price = (TextView) this.findViewById(R.id.price);

        price.setText(String.format(" $%d ",po.getInt("Price")));


        TextView available = (TextView) this.findViewById(R.id.textView);
        available.setText(String.format("available from %d/%d/%d to %d/%d/%d", po.getInt("FromDay"), po.getInt("FromMonth")
                , po.getInt("FromYear"), po.getInt("ToDay"), po.getInt("ToMonth"), po.getInt("ToYear")));

        TextView upload = (TextView) this.findViewById(R.id.textView2);
        upload.setText(String.format("Uploaded by %s", po.getString("UserName")));

        TextView tenant = (TextView) this.findViewById(R.id.textView3);
        tenant.setText(String.format("Need %d tenants", po.getInt("NeedTenant")));

        TextView description = (TextView) this.findViewById(R.id.textView4);
        description.setText(String.format("Description:%s", po.getString("description")));


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void displayImage(ParseFile thumbnail, final ImageView img) {

        if (thumbnail != null) {
            thumbnail.getDataInBackground(new GetDataCallback() {

                @Override
                public void done(byte[] data, ParseException e) {

                    if (e == null) {
                        Bitmap bmp = BitmapFactory.decodeByteArray(data, 0,
                                data.length);

                        if (bmp != null) {

                            Log.e("parse file ok", " null");
                            // img.setImageBitmap(Bitmap.createScaledBitmap(bmp,
                            // (display.getWidth() / 5),
                            // (display.getWidth() /50), false));
                            img.setImageBitmap(bmp);
                            // img.setPadding(10, 10, 0, 0);



                        }
                    } else {
                        Log.e("paser after downloade", " null");
                    }

                }
            });
        } else {

            Log.e("parse file", " null");

            // img.setImageResource(R.drawable.ic_launcher);

            img.setPadding(10, 10, 10, 10);
        }

    }
}
