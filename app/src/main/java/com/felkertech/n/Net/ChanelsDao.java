package com.felkertech.n.Net;

import android.os.AsyncTask;
import android.util.Log;

import com.felkertech.n.cumulustv.model.Channelsinfo;
import com.felkertech.n.cumulustv.xmltv.Channel;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceException;
import com.microsoft.windowsazure.mobileservices.MobileServiceList;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.util.concurrent.ExecutionException;

/**
 * Created by jhon on 25/05/16.
 */
public class ChanelsDao {
    public static final int  COMPLETE_RESPONSE = 0;
    public static final int FAILED_RESPONSE = 1;

    public interface OnDataBaseResponse{
        void OnQueryResponse (int state,MobileServiceList<Channelsinfo> data);
    }
    MobileServiceClient mClient;
    MobileServiceTable<Channelsinfo> mTable;
    MobileServiceList<Channelsinfo>  mList;
    OnDataBaseResponse dataBaseResponse;

    public ChanelsDao(MobileServiceClient mClient) {
        this.mClient = mClient;
        mTable = mClient.getTable(Channelsinfo.class);
    }

    public void getChannelsInfo(final OnDataBaseResponse dataBaseResponse){
        this.dataBaseResponse = dataBaseResponse;

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    mList = mTable.execute().get();
                } catch (InterruptedException e) {
                    dataBaseResponse.OnQueryResponse(FAILED_RESPONSE,null);
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                    dataBaseResponse.OnQueryResponse(FAILED_RESPONSE,null);
                } catch (MobileServiceException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                dataBaseResponse.OnQueryResponse(COMPLETE_RESPONSE,mList);
            }
        }.execute();

    }

    public void createNewChannels(final OnDataBaseResponse dataBaseResponse, final Channelsinfo channelsinfo){


        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    mTable.insert(channelsinfo).get();
                } catch (InterruptedException e) {
                    Log.i("AZURE","FAILED INSERTION " + e.toString());
                    dataBaseResponse.OnQueryResponse(FAILED_RESPONSE,null);

                } catch (ExecutionException e) {
                    e.printStackTrace();
                    Log.i("AZURE","FAILE INSERTIO "+ e.toString());
                    dataBaseResponse.OnQueryResponse(FAILED_RESPONSE,null);
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                if (channelsinfo.getId()!= null){
                dataBaseResponse.OnQueryResponse(COMPLETE_RESPONSE,mList);
                }

                else{
                    dataBaseResponse.OnQueryResponse(FAILED_RESPONSE,mList);
                }
            }
        };

    }


}



