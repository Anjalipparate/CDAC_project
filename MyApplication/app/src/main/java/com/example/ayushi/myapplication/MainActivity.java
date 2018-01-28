package com.example.ayushi.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = (Button) findViewById(R.id.button1);
        final TextView tv = (TextView) findViewById(R.id.textView1);
        final TextView tv2= (TextView) findViewById(R.id.textView2);


        button1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                new Client().doInBackground();
            }

        });

    }


}
