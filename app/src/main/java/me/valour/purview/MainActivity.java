package me.valour.purview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

import com.wikitude.architect.ArchitectView;
import com.wikitude.architect.StartupConfiguration;

import java.io.IOException;


public class MainActivity extends Activity {

    ArchitectView av;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        av = (ArchitectView) this.findViewById(R.id.architectView);
        final StartupConfiguration config = new StartupConfiguration(this.getString(R.string.wiki_key));
        av.onCreate( config );
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        av.onPostCreate();
        try {
            av.load("index.html");
            av.getSupportedFeaturesForDevice(this);
        } catch( IOException ex) {
            Log.d("ex", "IO exception on loading html file");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        av.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        av.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        av.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

}
