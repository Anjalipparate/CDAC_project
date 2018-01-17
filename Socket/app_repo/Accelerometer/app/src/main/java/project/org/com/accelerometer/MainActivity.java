package project.org.com.accelerometer;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    SensorManager sm=null;
    TextView tv=null;
    List list;

    SensorEventListener sel = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float[] values = event.values;
            tv.setText("X:"+values[0]+"\nY:"+values[1]+"\nZ:"+values[2]);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sm = (SensorManager)getSystemService(SENSOR_SERVICE);
        tv = (TextView)findViewById(R.id.text);
        list = sm.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if(list.size()>0)
        {
            sm.registerListener(sel,(Sensor)list.get(0),SensorManager.SENSOR_DELAY_NORMAL);
        }
        else {
            Toast.makeText(getBaseContext(),"Error: No accelerometer",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onStop() {

        if(list.size()>0)
        {
            sm.unregisterListener(sel);
        }
        super.onStop();
    }
}
