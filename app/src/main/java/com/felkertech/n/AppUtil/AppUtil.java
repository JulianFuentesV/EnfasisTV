package com.felkertech.n.AppUtil;

import com.felkertech.n.cumulustv.model.JSONChannel;

/**
 * Created by jhon on 26/05/16.
 */
public class AppUtil {

    /**
     *Send data just the first time, after that, json channel alarady exist.
     */


    private static JSONChannel[] jsonChannels;
    public static JSONChannel[] getJsonChannels(JSONChannel[] data){
        if (jsonChannels == null){
            jsonChannels = data;
        }

     return jsonChannels;

    }

}
