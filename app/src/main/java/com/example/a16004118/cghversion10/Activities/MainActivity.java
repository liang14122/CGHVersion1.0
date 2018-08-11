package com.example.a16004118.cghversion10.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a16004118.cghversion10.NotificationService.Common;
import com.example.a16004118.cghversion10.ObjectPackage.Doctor;
import com.example.a16004118.cghversion10.ObjectPackage.PatientAndMedicalDetail;
import com.example.a16004118.cghversion10.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.Objects;

public class MainActivity extends Activity {

    DatabaseReference databaseReferenceDoctor;
    private Button btnLogin;
    private EditText etPassword, etUserName;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //find the table name and start to add


        Log.d("Test Purpose", "Refreshed token: " + Common.currentToken);

        btnLogin = findViewById(R.id.btnLogin);
        etPassword = findViewById(R.id.etPassword);
        etUserName = findViewById(R.id.etUserName);
//
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String username = etUserName.getText().toString().trim();
                final String pw = etPassword.getText().toString().trim();

//                final String username = "P0148F";
//                final String pw = "123";

                databaseReferenceDoctor = FirebaseDatabase.getInstance().getReference("cghversion20").child("Doctor");

                databaseReferenceDoctor.addValueEventListener(new ValueEventListener() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        Log.i(TAG, String.valueOf(dataSnapshot));
                        for (DataSnapshot child : dataSnapshot.getChildren()) {
                            Log.i(TAG, String.valueOf(child));

                            String surgeronId = Objects.requireNonNull(child.child("surgeronId").getValue()).toString();
                            String password = Objects.requireNonNull(child.child("password").getValue()).toString();

                            if (surgeronId.equalsIgnoreCase(username) && password.equalsIgnoreCase(pw)) {
                                String key = Objects.requireNonNull(child.getKey());
                                String doctorName = Objects.requireNonNull(child.child("name").getValue()).toString();
                                SharedPreferences prefs =
                                        PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

                                Doctor currentDoctor = new Doctor(key, surgeronId, doctorName);

                                SharedPreferences.Editor editor = prefs.edit();
                                Gson gson = new Gson();
                                String doctorString = gson.toJson(currentDoctor);
                                editor.putString("doctorString", doctorString);

                                editor.apply();

                                Intent i = new Intent(MainActivity.this, HomeActivity.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(i);

                                btnLogin.setEnabled(false);
                                etPassword.setEnabled(false);
                                etUserName.setEnabled(false);

                                Log.i(TAG, "onDataChange: " + key);
                                break;
                            }else{
                                Toast.makeText(MainActivity.this, "Invalid username/password!", Toast.LENGTH_LONG).show();
                                btnLogin.setEnabled(true);
                                etPassword.setEnabled(true);
                                etUserName.setEnabled(true);
                                break;
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.w(TAG, "loadDoctor:onCancelled", databaseError.toException());
                    }
                });
            }
        });

    }
}

