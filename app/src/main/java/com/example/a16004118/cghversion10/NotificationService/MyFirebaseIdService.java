package com.example.a16004118.cghversion10.NotificationService;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseIdService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIIDService";

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = null;
        Log.d(TAG, "Refreshed token: " + refreshedToken);
        Common.currentToken = refreshedToken;

        try {
            refreshedToken = FirebaseInstanceId.getInstance().getToken();
            Log.d(TAG, "Refreshed token: " + refreshedToken);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(refreshedToken);
    }

    private void sendRegistrationToServer(String token) {
        // TODO: Implement this method to send token to your app server.
    }

}
