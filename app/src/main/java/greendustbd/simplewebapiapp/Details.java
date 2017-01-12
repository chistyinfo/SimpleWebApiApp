package greendustbd.simplewebapiapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Details extends AppCompatActivity {
    TextView time,title,details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        time= (TextView) findViewById(R.id.mtime);
        title= (TextView) findViewById(R.id.mtitle);
        details= (TextView) findViewById(R.id.mdetails);

        String _title = getIntent().getStringExtra("MyTITLE");
        String _time = getIntent().getStringExtra("MyNEWS");
        String _details = getIntent().getStringExtra("MyTime");

        title.setText(_title);
        time.setText(_time);
        details.setText(_details);
    }
}
