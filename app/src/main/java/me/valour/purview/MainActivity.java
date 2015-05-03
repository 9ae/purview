package me.valour.purview;

import android.app.Activity;
import android.app.DialogFragment;
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
import java.util.HashMap;


public class MainActivity extends Activity implements FanControlFragment.FanDialogListener {

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
        av.registerUrlListener(new ArchitectView.ArchitectUrlListener() {
            @Override
            public boolean urlWasInvoked(String s) {
                urlParser(s);
                return false;
            }
        });

        try {
            av.load("index.html");
        } catch( IOException ex) {
            Log.d("ex", "IO exception on loading html file");
        }
    }

    private void urlParser(String s){
        String urlParamString = s.replace("architectsdk://callactioninJava?","");
        Log.d("url", urlParamString);
        HashMap<String, String> paramMap = Utils.urlToMap(urlParamString);

        if(paramMap.containsKey("fun")){
            String functionName = paramMap.get("fun");
            switch (functionName){
                case "fan":
                    launchFanControlDialog();
                    break;


            }
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

    private void launchFanControlDialog(){
        FanControlFragment dialog = new FanControlFragment();
        dialog.show(this.getFragmentManager(), "FanControlFragment");
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

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {

    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }

    @Override
    public void onTurnFanOn() {

    }

    @Override
    public void onTurnFanOff() {

    }
}
