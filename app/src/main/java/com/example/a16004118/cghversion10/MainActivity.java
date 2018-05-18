package com.example.a16004118.cghversion10;

import android.app.Activity;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

    ImageView ivBackground;
    TextInputLayout tipUsername, tipPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set background to fit parent
        ivBackground = findViewById(R.id.ivBackground);
        ivBackground.setScaleType(ImageView.ScaleType.FIT_XY);

        tipPassword = findViewById(R.id.input_layout_passwrod);
        tipUsername = findViewById(R.id.input_layout_userName);

        tipUsername.setHint("Username/MRC");
        tipPassword.setHint("Password");
    }
}
