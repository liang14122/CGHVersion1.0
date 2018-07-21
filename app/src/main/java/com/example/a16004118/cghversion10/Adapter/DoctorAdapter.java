package com.example.a16004118.cghversion10.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.a16004118.cghversion10.ObjectPackage.Doctor;
import com.example.a16004118.cghversion10.R;

import java.util.ArrayList;

public class DoctorAdapter extends ArrayAdapter<Doctor> {
    private ArrayList<Doctor> doctorArrayList;
    private Context context;
    private TextView tv1, tv2,tv3,tv4,tv5,tv6;
    public DoctorAdapter(Context context, int resource, ArrayList<Doctor> objects){
        super(context, resource, objects);
        // Store the food that is passed to this adapter
        doctorArrayList = objects;
        // Store Context object as we would need to use it later
        this.context = context;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // The usual way to get the LayoutInflater object to
        //  "inflate" the XML file into a View object
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // "Inflate" the row.xml as the layout for the View object

        View rowView = inflater.inflate(R.layout.list_adapter, parent, false);

        // Get the TextView object
        tv1 =  rowView.findViewById(R.id.tv1);
        tv2 =  rowView.findViewById(R.id.tv2);
        tv3 =  rowView.findViewById(R.id.tv3);
        tv4 =  rowView.findViewById(R.id.tv4);
        tv5 =  rowView.findViewById(R.id.tv5);
        tv6 =  rowView.findViewById(R.id.tv6);

        Doctor current = doctorArrayList.get(position);
        // Set the TextView to show the food

        tv1.setText("Surgeron ID: ");
        // Set the image to star or nostar accordingly
        tv2.setText(current.getSurgeronId());
        tv3.setText("Name: ");
        tv4.setText(current.getName());
        tv5.setText("Rank: "+current.getRank());
        tv6.setText("Pager: "+current.getPager());


        return rowView;
    }

}
