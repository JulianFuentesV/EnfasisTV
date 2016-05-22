package com.felkertech.n.cumulustv.Intro;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.felkertech.n.cumulustv.R;


/**
 * Created by N on 7/1/2015.
 */
public class FirstSlide extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.intro_1, container, false);
        ((TextView) v.findViewById(R.id.title)).setText(R.string.intro_title_1);
        ((TextView) v.findViewById(R.id.description)).setText(R.string.intro_msg_1);
        ((ImageView) v.findViewById(R.id.image)).setImageDrawable(getResources().getDrawable(R.drawable.c_banner_3_2));
        v.findViewById(R.id.main).setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        return v;
    }
}