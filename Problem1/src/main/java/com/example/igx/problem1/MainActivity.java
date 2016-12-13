package com.example.igx.problem1;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.location.*;

import static android.Manifest.permission_group.SENSORS;
import static android.content.Context.SENSOR_SERVICE;


public class MainActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager sm ;

    Sensor GyroS ;
    Sensor AccS ;
    Sensor TemS ;
    Sensor MagS ;
    Intent msg_intent;

    float Gyro_X ;
    float Gyro_Y ;
    float Gyro_Z ;

    float Acc_X ;
    float Acc_Y ;
    float Acc_Z ;

    float temperature;

    float Mag_X ;
    float Mag_Y ;
    float Mag_Z ;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_getLocation = (Button) findViewById(R.id.btn_getLocation);
        Button btn_getSensors = (Button) findViewById(R.id.btn_getSensors);
        final Button btn_sendMessage = (Button) findViewById(R.id.btn_sendMessage);





        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        GyroS = sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE); // x,y,z
        AccS = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER); // x,y,z
        TemS = sm.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE); // 0
        MagS = sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD); // x,y,z

        final TextView text_selectedData = (TextView) findViewById(R.id.text_selectedData);
        final TextView text_selectedType = (TextView) findViewById(R.id.text_selectedType);
        final EditText edit_phoneNumber = (EditText) findViewById(R.id.edit_phoneNumber);

        btn_getLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                text_selectedType.setText(String.valueOf("LOCATION"));

            }
        });

        btn_getSensors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                text_selectedType.setText(String.valueOf("SENSORS"));

            }
        });

        btn_sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(Intent.ACTION_SENDTO(savedInstanceState));

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        sm.registerListener(this, AccS, SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(this, GyroS, SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(this, TemS, SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(this, MagS, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();

        sm.unregisterListener(this);

    }

    public void onAccuracyChanged(Sensor sensor, int Accuracy) {

    };

    public void onSensorChanged(SensorEvent event) {
        switch (event.sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                Acc_X = event.values[0];
                Acc_Y = event.values[1];
                Acc_Z = event.values[2];
                break;
            case Sensor.TYPE_GYROSCOPE:
                Gyro_X = event.values[0];
                Gyro_Y = event.values[1];
                Gyro_Z = event.values[2];
                break;
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                temperature = event.values[0];
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                Mag_X = event.values[0];
                Mag_Y = event.values[1];
                Mag_Z = event.values[2];
                break;

        }
    }
}



