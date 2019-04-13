package com.example.fordeveloper;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

public class FragmentMen extends Fragment {
    MainActivity mainActivity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_men, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainActivity = new MainActivity();
        final CheckBox chb_pen_men = (CheckBox) getView().findViewById(R.id.chb_pen_men);
        final CheckBox chb_cross_men = (CheckBox) getView().findViewById(R.id.chb_cross_men);
        final CheckBox chb_pendent_men = (CheckBox) getView().findViewById(R.id.chb_pendent_men);

        chb_pen_men.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chb_pen_men.isChecked()){
                    Log.wtf("pen","yas");
                    mainActivity.setChbPenString(true);
                }

                else if (!chb_pen_men.isChecked()){
                    Log.wtf("pen","no");
                    mainActivity.setChbPenString(false);
                }
            }
        });

        chb_cross_men.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chb_cross_men.isChecked()){
                    Log.wtf("pen","yas");
                    mainActivity.setChbCrossString(true);
                }

                else if (!chb_cross_men.isChecked()){
                    Log.wtf("pen","no");
                    mainActivity.setChbCrossString(false);
                }
            }
        });

        chb_pendent_men.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chb_pendent_men.isChecked()){
                    Log.wtf("pen","yas");
                    mainActivity.setChbPendentString(true);
                }

                else if (!chb_cross_men.isChecked()){
                    Log.wtf("pen","no");
                    mainActivity.setChbPendentString(false);
                }
            }
        });
    }
}
