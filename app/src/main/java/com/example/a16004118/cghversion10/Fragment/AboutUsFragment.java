package com.example.a16004118.cghversion10.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a16004118.cghversion10.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutUsFragment extends Fragment {
    TextView tvHi;

    public AboutUsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_about_us, container, false);
        tvHi = v.findViewById(R.id.tvHi);
        tvHi.setText("Hi all,\n\n"
            +"This is a group of students from Republic Poly, school of infocom.\n\n"
            +"We have spent 13 study weeks to apply what we have learned to build this app.\n\n"
            +"Though some of the functions couldn't be done before our deadline, we could say we've tried our best to apply our knowledge we learned from school & internet to this app.\n\n" +
                "Hopefully, this version will be helpful to the idea in future.\n\n" +
                "Regards,\n\n" +
                "Yao Liang, Kwow Xiangwen,\n" +
                "Zhang Zhongtian");
        return v;
    }

}
