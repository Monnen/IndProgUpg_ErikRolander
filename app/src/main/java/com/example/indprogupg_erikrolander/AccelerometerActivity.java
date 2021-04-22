package com.example.indprogupg_erikrolander;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.TextView;

public class AccelerometerActivity extends AppCompatActivity implements SensorEventListener {

    TextView XTV;
    TextView YTV;
    TextView ZTV;
    TextView MXTV;
    TextView MYTV;
    TextView MZTV;
    private float mx = 0;
    private float my = 0;
    private float mz = 0;
    private SensorManager sensorManager;
    private Sensor accelerometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);

        XTV = (TextView) findViewById(R.id.textView2);
        YTV = (TextView) findViewById(R.id.textView3);
        ZTV = (TextView) findViewById(R.id.textView4);
        MXTV = (TextView) findViewById(R.id.textView6);
        MYTV = (TextView) findViewById(R.id.textView7);
        MZTV = (TextView) findViewById(R.id.textView8);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // get angle around the z-axis rotated
        float x = Math.round(event.values[0]);
        float y = Math.round(event.values[1]);
        float z = Math.round(event.values[2]);
        if(x > mx) mx = x;
        if(y > my) my = y;
        if(z > mz) mz = z;
        XTV.setText("X: " + Float.toString(x) );
        YTV.setText("Y: " + Float.toString(y) );
        ZTV.setText("X: " + Float.toString(z) );
        MXTV.setText("MAX X: " + Float.toString(mx) );
        MYTV.setText("MAX Y: " + Float.toString(my) );
        MZTV.setText("MAX Z: " + Float.toString(mz) );

    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    //onResume() register the accelerometer for listening the events
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    //onPause() unregister the accelerometer for stop listening the events
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}