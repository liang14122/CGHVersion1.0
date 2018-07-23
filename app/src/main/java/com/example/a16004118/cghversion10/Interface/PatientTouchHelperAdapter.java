package com.example.a16004118.cghversion10.Interface;

import android.view.View;

public interface PatientTouchHelperAdapter {

    void onClick(View view, int position);

    void onItemMove(int fromPosition, int toPosition, View view);

//    void onItemDismiss(int position);
}
