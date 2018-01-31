package com.example.asim.gesticulate;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String filename;
    SensorManager sm = null;
    TextView textView1 = null;
    List list;
    String string;
    String check;
    SensorEventListener sel = new SensorEventListener(){

        public void onAccuracyChanged(Sensor sensor, int accuracy) {}
        public void onSensorChanged(SensorEvent event) {
            float[] values = event.values;
            String string1=null;
            textView1.setText("x: "+values[0]+"\ny: "+values[1]+"\nz: "+values[2]);
            if(check.equals("Test"))
            {
                string1 = String.valueOf(values[0])+" "+String.valueOf(values[1])+" "+String.valueOf(values[2])+" \n";
            }
            else
            {
                string1 = String.valueOf(values[0])+" "+String.valueOf(values[1])+" "+String.valueOf(values[2])+" "+string+" \n";
            }
            FileOutputStream fos = null;
            try {
                fos = openFileOutput(filename, Context.MODE_APPEND);
                fos.write(string1.getBytes());
                fos.close();
            }  catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
            }
        }

    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button start= (Button) findViewById(R.id.button);
        final Button stop= (Button) findViewById(R.id.button2);
        /* Get a SensorManager instance */
        final RadioGroup radioGroup,radioGroup1;
        textView1 = (TextView)findViewById(R.id.textView);
        radioGroup=(RadioGroup)findViewById(R.id.radio);
        radioGroup1=(RadioGroup)findViewById(R.id.radio1);
        sm = (SensorManager)getSystemService(SENSOR_SERVICE);
        list = sm.getSensorList(Sensor.TYPE_ACCELEROMETER);
        /*if(list.size()>0){
            sm.registerListener(sel, (Sensor) list.get(0), SensorManager.SENSOR_DELAY_NORMAL);
        }else{
            Toast.makeText(getBaseContext(), "Error: No Accelerometer.", Toast.LENGTH_LONG).show();
        }*/
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id=radioGroup.getCheckedRadioButtonId();
                int id1=radioGroup1.getCheckedRadioButtonId();
                RadioButton radioButton=(RadioButton)findViewById(id);
                RadioButton radioButton1=(RadioButton)findViewById(id1);
                string=radioButton.getText().toString();
                check=radioButton1.getText().toString();
                Toast.makeText(MainActivity.this,string, Toast.LENGTH_SHORT).show();
                String timeStamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new java.util.Date());
                FileOutputStream fos = null;
                filename=string+"-"+timeStamp+".csv";

                    try {
                        fos = openFileOutput(filename, Context.MODE_PRIVATE);
                        fos.close();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }

                sm.registerListener(sel, (Sensor) list.get(0), SensorManager.SENSOR_DELAY_GAME);
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                sm.unregisterListener(sel);
            }
        });
        Button send=(Button)findViewById(R.id.button3);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               new Client("/data/data/com.example.asim.accelerometer_byasim/files/0-2018-01-29-12-15-45.csv").createConnection();
                Toast.makeText(MainActivity.this, getFilesDir().toString()+"1-2018-01-30-18-01-47.csv", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStop() {
        if(list.size()>0){
            sm.unregisterListener(sel);
        }
        super.onStop();
    }
}
