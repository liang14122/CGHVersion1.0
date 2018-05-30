package com.example.a16004118.cghversion10.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a16004118.cghversion10.ObjectPackage.Notification;
import com.example.a16004118.cghversion10.R;

import java.util.ArrayList;

public class NotificationAdapter extends ArrayAdapter<Notification> {
    private ArrayList<Notification> notificationArrayList;
    private Context context;
    private TextView tvName, tvMessage, tvDateAndTime;
    private ImageView alertImg, checkImg;
    public NotificationAdapter(Context context, int resource, ArrayList<Notification> objects){
        super(context, resource, objects);
        // Store the food that is passed to this adapter
        notificationArrayList = objects;
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
        View rowView = inflater.inflate(R.layout.notification_list_adapter, parent, false);

        // Get the TextView object
        tvDateAndTime =  rowView.findViewById(R.id.dateAndTime);
        tvMessage =  rowView.findViewById(R.id.message);
        tvName =  rowView.findViewById(R.id.name);

        // Get the ImageView object
        alertImg =  rowView.findViewById(R.id.alertImg);
        checkImg =  rowView.findViewById(R.id.checkImg);


        // The parameter "position" is the index of the
        //  row ListView is requesting.
        //  We get back the food at the same index.
        Notification current = notificationArrayList.get(position);
        // Set the TextView to show the food

        tvName.setText(current.getPatientName());
        // Set the image to star or nostar accordingly
        tvMessage.setText(current.getSentence());
        if(current.isAlert() == false) {
            alertImg.setImageResource(R.drawable.normal);
        }

        else {
            alertImg.setImageResource(R.drawable.alert);
        }
        if(current.isRead() == false) {
            checkImg.setImageResource(R.drawable.ic_launcher_background);
        }
        else {
            checkImg.setImageResource(R.drawable.check);
        }
        // Return the nicely done up View to the ListView
        return rowView;
    }

}
