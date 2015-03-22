package es.fraggel.gesturecontrol;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class appSelect extends ActionBarActivity implements AdapterView.OnItemClickListener {
    ArrayList listaPackages = new ArrayList();
    ArrayList listaNombres = new ArrayList();
    ArrayList listaIconos = new ArrayList();
    int idSelected=-1;
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
        try {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_app_select);
            Intent intent = getIntent();
            idSelected=intent.getIntExtra("gesture",-1);
            final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
            mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
            List<ResolveInfo> ril = getPackageManager().queryIntentActivities(mainIntent, 0);
            String name = null;

            for (ResolveInfo ri : ril) {
                if (ri.activityInfo != null) {
                    Resources res = getPackageManager().getResourcesForApplication(ri.activityInfo.applicationInfo);
                    if (ri.activityInfo.labelRes != 0) {
                        name = res.getString(ri.activityInfo.labelRes);
                    } else {
                        name = ri.activityInfo.applicationInfo.loadLabel(
                                getPackageManager()).toString();
                    }

                    listaPackages.add(ri.activityInfo.packageName + "&" + ri.activityInfo.name);
                    listaNombres.add(name);
                    listaIconos.add(ri.activityInfo.loadIcon(getPackageManager()));
                }
            }
            ListView viewById = (ListView) findViewById(R.id.listView);
            viewById.setAdapter(new CustomAdapter(this, listaNombres, listaIconos));
            viewById.setOnItemClickListener(this);

        } catch (Exception e) {

        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent();
        intent.putExtra("appSelected",(String)listaPackages.get(i));
        intent.putExtra("appName",(String)listaNombres.get(i));
        intent.putExtra("idSelected",idSelected);
        setResult(-1, intent);
        finish();
    }
}
