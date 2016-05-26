package com.felkertech.n.Test;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.felkertech.n.Net.ChanelsDao;
import com.felkertech.n.cumulustv.R;
import com.felkertech.n.cumulustv.model.Channelsinfo;
import com.felkertech.n.cumulustv.model.JSONChannel;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceList;

import java.net.MalformedURLException;

public class TestAzureActivity extends Activity implements ChanelsDao.OnDataBaseResponse {

    public MobileServiceClient mClient;
    ChanelsDao chanelsDao;
    MobileServiceList<Channelsinfo> channels;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_azure);

        try {
            mClient = new MobileServiceClient(
                    "https://enfasistv.azure-mobile.net/",
                    "LfyxHixuSjrIUIakypACSXzVdhlPGd16",
                    this
            );
            chanelsDao = new ChanelsDao(mClient);
            chanelsDao.getChannelsInfo(this);
            chanelsDao.getChanelsInJsonObject(this);

        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.i("AZURE","NOT CONECTED "+ e.toString());
        }




    }

    @Override
    public void OnQueryResponse(int state, MobileServiceList<Channelsinfo> data) {
        if (state == ChanelsDao.FAILED_RESPONSE){
            Log.i("AZURE","QUERY FAILED");
        }
        else if (state == ChanelsDao.COMPLETE_RESPONSE){

            for (int i = 0; i<data.size() ; i++){
                Log.i("CHANELS","CHANEL_NAME "+ data.get(i).getName());
                Log.i("CHANELS","CHANEL_ID "+ data.get(i).getId());
            }

        }
    }

    @Override
    public void OnQueryResponseInJson(int state, JSONChannel[] data) {
        if (state == ChanelsDao.FAILED_RESPONSE){
            Log.i("AZURE","QUERY FAILED");
        }
        else if (state == ChanelsDao.COMPLETE_RESPONSE){
                Log.i("AZURE",data[0].toString() + " " + data[1].toString());
        }
    }
    }


    /*
    @Override
    public void OnQueryResponse(int state, MobileServiceList<Channelsinfo> data) {


    }
    */





