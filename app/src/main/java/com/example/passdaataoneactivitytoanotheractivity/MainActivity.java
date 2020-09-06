package com.example.passdaataoneactivitytoanotheractivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {


    EditText name,pass,email,phoone;
    RadioGroup radioGroup;
    RadioButton male,female;
    ImageView imageView;

    Button button;

    Bitmap bitmap;

    String gender_type="";

    final int REQUEST_CODE = 1000;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        pass = findViewById(R.id.pass);
        email  = findViewById(R.id.email);
        phoone = findViewById(R.id.phone);

        button = findViewById(R.id.button);

        radioGroup = findViewById(R.id.grp);
        male = findViewById(R.id.male);
        female  = findViewById(R.id.female);

        imageView = findViewById(R.id.imageView);

        bitmap =BitmapFactory.decodeResource(getResources(),R.drawable.img0);
        imageView.setImageBitmap(bitmap);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(MainActivity.this,SecondActivity.class);

                intent.putExtra("NAME",name.getText().toString());
                intent.putExtra("PASS",pass.getText().toString());
                intent.putExtra("EMAIL",email.getText().toString());
                intent.putExtra("PHONE",phoone.getText().toString());
                intent.putExtra("GENDER",gender_type);


                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
                byte[] byteArray = stream.toByteArray();
                intent.putExtra("PICTURE",byteArray);

                startActivity(intent);

                finish();


            }
        });





        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentImg = new Intent(Intent.ACTION_GET_CONTENT);
                intentImg.setType("*/*");
                startActivityForResult(intentImg,REQUEST_CODE);
            }
        });



        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId)
                {
                    case R.id.male:

                        gender_type = "male";
                        break;

                    case R.id.female:

                        gender_type ="female";
                        break;
                }

            }
        });






    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {       //for the immage sending one activity to another
        super.onActivityResult(requestCode, resultCode, data);

       switch (requestCode)
       {
           case REQUEST_CODE:
               if (resultCode == RESULT_OK)
               {

                   Uri chosenImage = data.getData();

                   try {
                       bitmap =MediaStore.Images.Media.getBitmap(getContentResolver(),chosenImage);
                       imageView.setImageBitmap(bitmap);
                   }
                   catch (IOException e)
                   {
                       e.printStackTrace();
                   }

               }
       }


    }
}
