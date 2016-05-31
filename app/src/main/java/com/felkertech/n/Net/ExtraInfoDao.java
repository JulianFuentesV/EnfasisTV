package com.felkertech.n.Net;

import android.os.AsyncTask;
import android.util.Log;

import com.felkertech.n.cumulustv.model.Channelsinfo;
import com.felkertech.n.cumulustv.model.Extrainfo;
import com.felkertech.n.cumulustv.model.JSONChannel;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceException;
import com.microsoft.windowsazure.mobileservices.MobileServiceList;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by jhon on 31/05/16.
 */
public class ExtraInfoDao
{

    public static final int  COMPLETE_RESPONSE = 0;
    public static final int FAILED_RESPONSE = 1;

    public interface OnDataBaseResponse{
        void OnQueryResponse (int state,MobileServiceList<Extrainfo> data);
    }
    MobileServiceClient mClient;
    MobileServiceTable<Extrainfo> mTable;
    MobileServiceList<Extrainfo>  mList;
    OnDataBaseResponse dataBaseResponse;

    public ExtraInfoDao(MobileServiceClient mClient) {
        this.mClient = mClient;
        mTable = mClient.getTable(Extrainfo.class);
    }

    public void getExtraInfo(final OnDataBaseResponse dataBaseResponse,final String msm){
        this.dataBaseResponse = dataBaseResponse;

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    mList = mTable.where().field("msgnot").eq(msm).execute().get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    this.cancel(true);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                    this.cancel(true);

                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                dataBaseResponse.OnQueryResponse(COMPLETE_RESPONSE,mList);
            }

            @Override
            protected void onCancelled() {
                super.onCancelled();
                dataBaseResponse.OnQueryResponse(FAILED_RESPONSE,null);
            }
        }.execute();

    }
}
