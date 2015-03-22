package es.fraggel.gesturecontrol;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class screenOffActivity extends ActionBarActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {
    Switch masterGestureOff=null;
    Switch drawDC=null;
    Switch drawUpU=null;
    Switch drawDownM=null;
    Switch drawRightM=null;
    Switch drawLeftM=null;
    Switch drawC=null;
    Switch drawE=null;
    Switch drawW=null;
    Switch drawM=null;
    Switch drawO=null;
    Switch drawRightBt=null;
    Switch drawLeftBt=null;
    TextView drawDCT=null;
    TextView drawUpUT=null;
    TextView drawDownMT=null;
    TextView drawRightMT=null;
    TextView drawLeftMT=null;
    TextView drawCT=null;
    TextView drawET=null;
    TextView drawWT=null;
    TextView drawMT=null;
    TextView drawOT=null;
    TextView drawRightBtT=null;
    TextView drawLeftBtT=null;

    SharedPreferences ajustes=null;
    SharedPreferences.Editor editorAjustes=null;
    String theme=null;
    int resultado=1;
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
        setContentView(R.layout.activity_screen_off);
        ajustes=getSharedPreferences("gestureAjustes",Context.MODE_PRIVATE);
        editorAjustes=ajustes.edit();
        masterGestureOff=(Switch)findViewById(R.id.masterGestureOff);
        masterGestureOff.setOnCheckedChangeListener(this);
        drawDC=(Switch)findViewById(R.id.drawDC);
        drawUpU=(Switch)findViewById(R.id.drawUpU);
        drawDownM=(Switch)findViewById(R.id.drawDownM);
        drawRightM=(Switch)findViewById(R.id.drawRightM);
        drawLeftM=(Switch)findViewById(R.id.drawLeftM);
        drawC=(Switch)findViewById(R.id.drawC);
        drawE=(Switch)findViewById(R.id.drawE);
        drawW=(Switch)findViewById(R.id.drawW);
        drawM=(Switch)findViewById(R.id.drawM);
        drawO=(Switch)findViewById(R.id.drawO);
        //drawRightBt=(Switch)findViewById(R.id.drawRightBt);
        //drawLeftBt=(Switch)findViewById(R.id.drawLeftBt);


        drawDCT=(TextView)findViewById(R.id.drawDCT);
        drawUpUT=(TextView)findViewById(R.id.drawUpUT);
        drawDownMT=(TextView)findViewById(R.id.drawDownMT);
        drawRightMT=(TextView)findViewById(R.id.drawRightMT);
        drawLeftMT=(TextView)findViewById(R.id.drawLeftMT);
        drawCT=(TextView)findViewById(R.id.drawCT);
        drawET=(TextView)findViewById(R.id.drawET);
        drawWT=(TextView)findViewById(R.id.drawWT);
        drawMT=(TextView)findViewById(R.id.drawMT);
        drawOT=(TextView)findViewById(R.id.drawOT);
        //drawRightBtT=(TextView)findViewById(R.id.drawRightBtT);
        //drawLeftBtT=(TextView)findViewById(R.id.drawLeftBtT);

        asignarListeners();
        asignarSeleccion();
        setAppNames();
    }
    public void asignarSeleccion(){
        masterGestureOff.setChecked(ajustes.getBoolean("masterGestureOff", false));
        cambiarEnabled(ajustes.getBoolean("masterGestureOff", false));
        drawDC.setChecked(ajustes.getBoolean("drawDC",false));
        drawUpU.setChecked(ajustes.getBoolean("drawUpU",false));
        drawDownM.setChecked(ajustes.getBoolean("drawDownM",false));
        drawRightM.setChecked(ajustes.getBoolean("drawRightM",false));
        drawLeftM.setChecked(ajustes.getBoolean("drawLeftM",false));
        drawC.setChecked(ajustes.getBoolean("drawC",false));
        drawE.setChecked(ajustes.getBoolean("drawE",false));
        drawW.setChecked(ajustes.getBoolean("drawW",false));
        drawM.setChecked(ajustes.getBoolean("drawM",false));
        drawO.setChecked(ajustes.getBoolean("drawO",false));
        drawC.setChecked(ajustes.getBoolean("drawC",false));

        //drawRightBt.setChecked(ajustes.getBoolean("drawRightBt",false));
        //drawLeftBt.setChecked(ajustes.getBoolean("drawLeftBt",false));
    }
    public void cambiarEnabled(boolean enabled){
        drawDC.setEnabled(enabled);
        drawUpU.setEnabled(enabled);
        drawDownM.setEnabled(enabled);
        drawRightM.setEnabled(enabled);
        drawLeftM.setEnabled(enabled);
        drawC.setEnabled(enabled);
        drawE.setEnabled(enabled);
        drawW.setEnabled(enabled);
        drawM.setEnabled(enabled);
        drawO.setEnabled(enabled);
        //drawRightBt.setEnabled(enabled);
        //drawLeftBt.setEnabled(enabled);

        drawDCT.setEnabled(enabled);
        drawUpUT.setEnabled(enabled);
        drawDownMT.setEnabled(enabled);
        drawRightMT.setEnabled(enabled);
        drawLeftMT.setEnabled(enabled);
        drawCT.setEnabled(enabled);
        drawET.setEnabled(enabled);
        drawWT.setEnabled(enabled);
        drawMT.setEnabled(enabled);
        drawOT.setEnabled(enabled);
        //drawRightBtT.setEnabled(enabled);
        //drawLeftBtT.setEnabled(enabled);
    }
    public void asignarListeners(){
        masterGestureOff.setOnCheckedChangeListener(this);
        drawDC.setOnCheckedChangeListener(this);
        drawUpU.setOnCheckedChangeListener(this);
        drawDownM.setOnCheckedChangeListener(this);
        drawRightM.setOnCheckedChangeListener(this);
        drawLeftM.setOnCheckedChangeListener(this);
        drawC.setOnCheckedChangeListener(this);
        drawE.setOnCheckedChangeListener(this);
        drawW.setOnCheckedChangeListener(this);
        drawM.setOnCheckedChangeListener(this);
        drawO.setOnCheckedChangeListener(this);
        //drawRightBt.setOnCheckedChangeListener(this);
        //drawLeftBt.setOnCheckedChangeListener(this);

        drawCT.setOnClickListener(this);
        drawET.setOnClickListener(this);
        drawWT.setOnClickListener(this);
        drawMT.setOnClickListener(this);
        drawOT.setOnClickListener(this);
        drawCT.setOnClickListener(this);
        //drawRightBtT.setOnClickListener(this);
        //drawLeftBtT.setOnClickListener(this);
    }
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(buttonView.getId()==R.id.masterGestureOff) {
            if (isChecked) {
                editorAjustes.putBoolean("masterGestureOff", true);
                editorAjustes.commit();
                cambiarEnabled(true);
                setCheckBoxEnabled(isChecked);
                writeFile(1);
            } else {
                editorAjustes.putBoolean("masterGestureOff",false);
                editorAjustes.commit();
                cambiarEnabled(false);
                setCheckBoxEnabled(isChecked);
                writeFile(0);

            }
        }else if(buttonView.getId()==R.id.drawDC) {
            if (isChecked) {
                editorAjustes.putBoolean("drawDC", true);
                editorAjustes.commit();
            } else {
                editorAjustes.putBoolean("drawDC",false);
                editorAjustes.commit();
            }
        }else if(buttonView.getId()==R.id.drawUpU) {
            if (isChecked) {
                editorAjustes.putBoolean("drawUpU", true);
                editorAjustes.commit();
            } else {
                editorAjustes.putBoolean("drawUpU",false);
                editorAjustes.commit();
            }
        }else if(buttonView.getId()==R.id.drawDownM) {
            if (isChecked) {
                editorAjustes.putBoolean("drawDownM", true);
                editorAjustes.commit();
            } else {
                editorAjustes.putBoolean("drawDownM",false);
                editorAjustes.commit();
            }
        }else if(buttonView.getId()==R.id.drawRightM) {
            if (isChecked) {
                editorAjustes.putBoolean("drawRightM", true);
                editorAjustes.commit();
            } else {
                editorAjustes.putBoolean("drawRightM",false);
                editorAjustes.commit();
            }
        }else if(buttonView.getId()==R.id.drawLeftM) {
            if (isChecked) {
                editorAjustes.putBoolean("drawLeftM", true);
                editorAjustes.commit();
            } else {
                editorAjustes.putBoolean("drawLeftM",false);
                editorAjustes.commit();
            }
        }else if(buttonView.getId()==R.id.drawC) {
            if (isChecked) {
                editorAjustes.putBoolean("drawC", true);
                editorAjustes.commit();
            } else {
                editorAjustes.putBoolean("drawC",false);
                editorAjustes.commit();
            }
        }else if(buttonView.getId()==R.id.drawE) {
            if (isChecked) {
                editorAjustes.putBoolean("drawE", true);
                editorAjustes.commit();
            } else {
                editorAjustes.putBoolean("drawE",false);
                editorAjustes.commit();
            }
        }else if(buttonView.getId()==R.id.drawW) {
            if (isChecked) {
                editorAjustes.putBoolean("drawW", true);
                editorAjustes.commit();
            } else {
                editorAjustes.putBoolean("drawW",false);
                editorAjustes.commit();
            }
        }else if(buttonView.getId()==R.id.drawM) {
            if (isChecked) {
                editorAjustes.putBoolean("drawM", true);
                editorAjustes.commit();
            } else {
                editorAjustes.putBoolean("drawM",false);
                editorAjustes.commit();
            }
        }else if(buttonView.getId()==R.id.drawO) {
            if (isChecked) {
                editorAjustes.putBoolean("drawO", true);
                editorAjustes.commit();
            } else {
                editorAjustes.putBoolean("drawO",false);
                editorAjustes.commit();
            }
        }/*else if(buttonView.getId()==R.id.drawRightBt) {
            if (isChecked) {
                editorAjustes.putBoolean("drawRightBt", true);
                editorAjustes.commit();
            } else {
                editorAjustes.putBoolean("drawRightBt",false);
                editorAjustes.commit();
            }
        }else if(buttonView.getId()==R.id.drawLeftBt) {
            if (isChecked) {
                editorAjustes.putBoolean("drawLeftBt", true);
                editorAjustes.commit();
            } else {
                editorAjustes.putBoolean("drawLeftBt",false);
                editorAjustes.commit();
            }
        }*/
    }

    private void writeFile (int value) {
        try {
            PowerManager powermanager;
            powermanager = (PowerManager) getSystemService(Context.POWER_SERVICE);
            while (!powermanager.isScreenOn())
            {
            }
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
    private void setCheckBoxEnabled(boolean isEnabled) {
        drawDC.setEnabled(isEnabled);
        drawUpU.setEnabled(isEnabled);
        drawDownM.setEnabled(isEnabled);
        drawRightM.setEnabled(isEnabled);
        drawLeftM.setEnabled(isEnabled);
        drawC.setEnabled(isEnabled);
        drawE.setEnabled(isEnabled);
        drawW.setEnabled(isEnabled);
        drawM.setEnabled(isEnabled);
        drawO.setEnabled(isEnabled);
        //drawLeftBt.setEnabled(isEnabled);
        //drawRightBt.setEnabled(isEnabled);
    }
    private void setAppNames(){
        drawCT.setText(getResources().getString(R.string.drawC)+" "+ajustes.getString("persist.sys.off_c_def_appName",getResources().getString(R.string.drawCDF)));
        drawET.setText(getResources().getString(R.string.drawE)+" "+ajustes.getString("persist.sys.off_e_def_appName",getResources().getString(R.string.drawEDF)));
        drawWT.setText(getResources().getString(R.string.drawW)+" "+ajustes.getString("persist.sys.off_w_def_appName",getResources().getString(R.string.drawWDF)));
        drawMT.setText(getResources().getString(R.string.drawM)+" "+ajustes.getString("persist.sys.off_m_def_appName",getResources().getString(R.string.drawMDF)));
        drawOT.setText(getResources().getString(R.string.drawO)+" "+ajustes.getString("persist.sys.off_o_def_appName",getResources().getString(R.string.drawODF)));
        //drawLeftBtT.setText(getResources().getString(R.string.drawO)+" "+ajustes.getString("persist.sys.off_lbt_def_appName",getResources().getString(R.string.drawLeftBtF)));
        //drawRightBtT.setText(getResources().getString(R.string.drawO)+" "+ajustes.getString("persist.sys.off_rbt_def_appName",getResources().getString(R.string.drawRightBtF)));
    }
    @Override
    public void onClick(View view) {
        int id = view.getId();
        Intent it=new Intent(this,appSelect.class);
        if(id==R.id.drawCT){
            it.putExtra("gesture", 31);
            startActivityForResult(it, resultado);
        }else if(id==R.id.drawET){
            it.putExtra("gesture", 33);
            startActivityForResult(it, resultado);
        }else if(id==R.id.drawWT){
            it.putExtra("gesture", 51);
            startActivityForResult(it, resultado);
        }else if(id==R.id.drawMT){
            it.putExtra("gesture", 41);
            startActivityForResult(it, resultado);
        }else if(id==R.id.drawOT){
            it.putExtra("gesture", 43);
            startActivityForResult(it, resultado);
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == resultado) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                String appSelected=data.getStringExtra("appSelected");
                String appName=data.getStringExtra("appName");
                int idSelected=data.getIntExtra("idSelected",-1);

                if(idSelected==31){
                    editorAjustes.putString("persist.sys.off_c_def_name", appSelected);
                    editorAjustes.putString("persist.sys.off_c_def_appName",appName);
                }else if(idSelected==33){
                    editorAjustes.putString("persist.sys.off_e_def_name",appSelected);
                    editorAjustes.putString("persist.sys.off_e_def_appName",appName);
                }else if(idSelected==51){
                    editorAjustes.putString("persist.sys.off_w_def_name",appSelected);
                    editorAjustes.putString("persist.sys.off_w_def_appName",appName);
                }else if(idSelected==41){
                    editorAjustes.putString("persist.sys.off_m_def_name",appSelected);
                    editorAjustes.putString("persist.sys.off_m_def_appName",appName);
                }else if(idSelected==43){
                    editorAjustes.putString("persist.sys.off_o_def_name",appSelected);
                    editorAjustes.putString("persist.sys.off_o_def_appName",appName);
                }
                editorAjustes.commit();
                setAppNames();
            }
        }
    }
}
