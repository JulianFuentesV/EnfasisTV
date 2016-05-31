package com.felkertech.n;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.felkertech.n.AppUtil.AppUtil;
import com.felkertech.n.Net.ChanelsDao;
import com.felkertech.n.cumulustv.R;
import com.felkertech.n.cumulustv.activities.MainActivity;
import com.felkertech.n.cumulustv.model.Channelsinfo;
import com.felkertech.n.cumulustv.model.JSONChannel;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceList;

import java.net.MalformedURLException;

public class SetInfoActivity extends Activity implements ChanelsDao.OnDataBaseResponse {
   ProgressDialog dialog;
    MobileServiceClient mClient;
    MobileServiceList<Channelsinfo> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_info);
        dialog = ProgressDialog.show(this,"Obteniendo informacion de canalaes","por favor espere",true);
        try {
            mClient = new MobileServiceClient(
                    "https://enfasistv.azure-mobile.net/",
                    "LfyxHixuSjrIUIakypACSXzVdhlPGd16",
                    this
            );
            ChanelsDao chanelsDao = new ChanelsDao(mClient);
            chanelsDao.getChanelsInJsonObject(this);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            dialog.dismiss();
            Toast.makeText(this,"Error conectando con Servicio de azure",Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void OnQueryResponse(int state, MobileServiceList<Channelsinfo> data) {

    }

    @Override
    public void OnQueryResponseInJson(int state, JSONChannel[] data) {
     if (state == ChanelsDao.COMPLETE_RESPONSE){
         AppUtil.getJsonChannels(data);
         dialog.dismiss();
         startActivity(new Intent(this, MainActivity.class));
         finish();

     }
        else if (state == ChanelsDao.FAILED_RESPONSE){
         dialog.dismiss();
         Toast.makeText(this,"Fallo la obtencion de canales",Toast.LENGTH_LONG).show();
     }
    }
}
