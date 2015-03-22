package es.fraggel.gesturecontrol;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.CompoundButton;
import android.widget.Switch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


public class screenOnActivity extends ActionBarActivity implements CompoundButton.OnCheckedChangeListener {
    String theme=null;
    Switch doubleLock=null;
    SharedPreferences ajustes=null;
    SharedPreferences.Editor editorAjustes=null;
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
        setContentView(R.layout.activity_screen_on);
        SharedPreferences ajustes = getSharedPreferences("gestureAjustes", Context.MODE_PRIVATE);
        editorAjustes=ajustes.edit();
        doubleLock=(Switch)findViewById(R.id.doubleLock);
        doubleLock.setChecked(ajustes.getBoolean("doubleLock", false));
        doubleLock.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if(compoundButton.getId()==R.id.doubleLock) {
            if (b) {
                editorAjustes.putBoolean("doubleLock", true);
                editorAjustes.commit();
            } else {
                editorAjustes.putBoolean("doubleLock",false);
                editorAjustes.commit();
            }
        }
    }
}
