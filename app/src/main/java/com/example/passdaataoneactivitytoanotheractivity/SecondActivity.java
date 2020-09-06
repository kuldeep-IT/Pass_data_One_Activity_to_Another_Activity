package com.example.passdaataoneactivitytoanotheractivity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    ImageView receivedImage;
    TextView name,pass,email,phone,gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        receivedImage = findViewById(R.id.imageView2);
        name  = findViewById(R.id.name);
        pass = findViewById(R.id.pass);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        gender = findViewById(R.id.gender);

        Bundle extras = getIntent().getExtras();

        String name1 = extras.getString("NAME");
        String email1 = extras.getString("EMAIL");
        String pass1 = extras.getString("PASS");
        String phone1 = extras.getString("PHONE");
        String gender1 = extras.getString("GENDER");

        name.setText("Name:" +name1);
        pass.setText("Pass :"+pass1);
        email.setText("Email :"+email1);
        phone.setText("Phone :"+phone1);
        gender.setText("Gender :"+gender1);

        byte[] byteArray = extras.getByteArray("PICTURE");

        Bitmap bitmap =BitmapFactory.decodeByteArray(byteArray,0,byteArray.length);
        receivedImage.setImageBitmap(bitmap);




    }
}
