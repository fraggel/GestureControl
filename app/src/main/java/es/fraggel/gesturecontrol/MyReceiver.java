package es.fraggel.gesturecontrol;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.PowerManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }
    SharedPreferences ajustes=null;
    @Override
    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent(context, MyService.class));
        ajustes=context.getSharedPreferences("gestureAjustes", Context.MODE_PRIVATE);
        if(ajustes.getBoolean("masterGestureOff",false)){
            PowerManager powermanager;
            powermanager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            while (!powermanager.isScreenOn())
            {
            }
            writeFile(1);
        }else{
            writeFile(0);
        }

    }
    private void writeFile (int value) {
        try {
            File file = new File("/sys/devices/platform/mt-i2c.0/i2c-0/0-0038/gesture");
            if (!file.exists()) {
                file = new File("/sys/devices/platform/mt-i2c.0/i2c-0/0-005d/gesture");
            }
            if (!file.exists()) {
                file = new File("/sys/devices/platform/mt-i2c.1/i2c-1/1-005d/gesture");
            }
            if (!file.exists()) {
                file = new File("/sys/devices/platform/mt-i2c.0/i2c-0/0-0020/gesture");
            }
            if (!file.exists()) {
                file = new File("/sys/devices/platform/mt-i2c.0/i2c-0/0-004b/gesture");
            }
            if (!file.exists()) {
                file = new File("/sys/devices/bus.2/11007000.I2C0/i2c-0/0-0020/gesture");
            }

            if (file.exists()) {
                FileWriter mWriter = new FileWriter(file);
                mWriter.write(String.valueOf(value));
                mWriter.flush();
                mWriter.close();
                mWriter = null;
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't open gesture device", e);
        }
    }

}
