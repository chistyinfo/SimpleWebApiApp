package greendustbd.simplewebapiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView showdata;
    ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv= (ListView) findViewById(R.id.mylist);


         fetchingdata();
    }

    void fetchingdata(){

        String url= "http://192.168.0.100/SimpleServer/api/gettingnews.php";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                final String[] news_title=new String[response.length()];
                final String[] news_date=new String[response.length()];
                final String[] news_details=new String[response.length()];

                for (int i=0; i< response.length();i++){
                    try {
                        JSONObject jsonObject =(JSONObject) response.get(i);
                        news_title[i]=jsonObject.getString("title");
                        news_date[i]=jsonObject.getString("time");
                        news_details[i]=jsonObject.getString("news");



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                lv.setAdapter(new ArrayAdapter (getApplicationContext(),R.layout.mylistview, R.id.textviewforlist, news_title));
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(MainActivity.this, Details.class);
                        intent.putExtra("MyTITLE", news_title[position]);
                        intent.putExtra("MyNEWS", news_details[position]);
                        intent.putExtra("MyTime", news_date[position]);
                        startActivity(intent);

                    }
                });
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Volley log",error);
            }
        });
        greendustbd.simplewebapiapp.AppController.getInstance().addToRequestQueue(jsonArrayRequest);
        Toast.makeText(getApplicationContext(),"Chisty your are success!",Toast.LENGTH_SHORT).show();
    }
}
