package csvparser.cs2340.com.parser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance().getReference();

        try {
            InputStream is = getResources().openRawResource(R.raw.locationdata);
            BufferedReader br = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            }

            String line;
            br.readLine(); //get rid of header line
            while ((line = br.readLine()) != null) {
                //Log.d(MainActivity.TAG, line);
                String[] tokens = line.split(",");
                //int id = Integer.parseInt(tokens[1]);
                //model.addItem(new DataItem(tokens[NAME_POSITION], tokens[2], id, tokens[3]));

                Location l = new Location(tokens[1], tokens[2], tokens[3],
                        tokens[4], tokens[5], tokens[6], tokens[7], tokens[8],
                        tokens[9]);
                database.child("locations").child(tokens[0]).setValue(l);
            }
            br.close();
        } catch (IOException e) {
            Log.e("MainActivityError", e.getMessage());
        }
    }
}
