package com.example_csv_to_sd.anjali.csv_to_sd;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.*;
public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button button_save,button_read;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       editText = (EditText) findViewById(R.id.editText);
       // button_read = (Button) findViewById(R.id.button);
        button_save = (Button) findViewById(R.id.button2);
        final File file = new File(getFilesDir(),"Test.csv");
        button_save.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String Message = editText.getText().toString();


                FileOutputStream outputStream;
                try {
                    outputStream = openFileOutput("Test.csv", Context.MODE_APPEND);
                    outputStream.write(Message.getBytes());
                    outputStream.close();
                    Toast.makeText(getApplicationContext(),"Message saved",Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }


                Toast.makeText(MainActivity.this,file.toString(),Toast.LENGTH_LONG).show();


            }
        });
    }



    }



