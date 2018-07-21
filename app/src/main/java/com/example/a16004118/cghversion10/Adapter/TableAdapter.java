package com.example.a16004118.cghversion10.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.a16004118.cghversion10.ObjectPackage.SurgicalTable;
import com.example.a16004118.cghversion10.R;

import java.util.ArrayList;

public class TableAdapter extends ArrayAdapter<SurgicalTable> {
    private ArrayList<SurgicalTable> surgicalTableArrayList;
    private Context context;
    private TextView tv1, tv2,tv3,tv4,tv5,tv6;
    public TableAdapter(Context context, int resource, ArrayList<SurgicalTable> objects){
        super(context, resource, objects);
        // Store the food that is passed to this adapter
        surgicalTableArrayList = objects;
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



        // The parameter "position" is the index of the
        //  row ListView is requesting.
        //  We get back the food at the same index.
        SurgicalTable current = surgicalTableArrayList.get(position);
        // Set the TextView to show the food

        tv1.setText("Surgical code: ");
        // Set the image to star or nostar accordingly
        tv2.setText(current.getSurgicalCode());
        tv3.setText("Table code: ");
        tv4.setText(current.getTableCode());
        tv5.setText(current.getDetail());
        // Return the nicely done up View to the ListView
        return rowView;
    }

}
