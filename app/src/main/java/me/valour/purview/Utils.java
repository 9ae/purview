package me.valour.purview;

import android.util.Log;

import java.util.HashMap;

/**
 * Created by alice on 5/2/15.
 */
public class Utils {

    public static HashMap<String, String> urlToMap(String urlParams){
        HashMap<String, String> map = new HashMap<String,String>();
        String[] params = urlParams.split("&");

        for(int i=0; i<params.length; i++){
            String[] param = params[i].split("=");
            map.put(param[0], param[1]);

        }
        return map;
    }

}
