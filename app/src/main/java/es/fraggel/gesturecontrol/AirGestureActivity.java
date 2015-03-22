package es.fraggel.gesturecontrol;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


public class AirGestureActivity extends ActionBarActivity {
    String theme=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        BufferedReader br=null;
        try {
            br = new BufferedReader(new FileReader(new File("/system/build.prop")));
            String cadenaLeida = br.readLine();
            while (cadenaLeida != null) {

                if (cadenaLeida.trim().indexOf("ro.tfota.theme") != -1) {
                    theme = cadenaLeida.trim().replaceAll(" ", "").replaceAll("ro.tfota.theme=", "");
                }
                cadenaLeida = br.readLine();
            }
            if ("black".equals(theme)) {
                setTheme(R.style.AppThemeBlack);
            } else if ("white".equals(theme)) {
                setTheme(R.style.AppThemeWhite);

            }
        }catch(Exception e){

        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air_gesture);
    }
}
