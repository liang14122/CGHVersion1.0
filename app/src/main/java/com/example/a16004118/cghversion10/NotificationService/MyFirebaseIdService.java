package com.example.a16004118.cghversion10.NotificationService;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseIdService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        String refreshToken = FirebaseInstanceId.getInstance().getToken();
        Common.currentToken = refreshToken;

        Log.d("Refreshed Token", "MyFirebaseIDService "+ refreshToken);
//        sendRegisterationToServer(refreshToken);
    }

}
