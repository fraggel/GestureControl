package es.fraggel.gesturecontrol;


import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;
import android.widget.Toast;


public class gestureReceiver extends BroadcastReceiver{
    public gestureReceiver() {
    }
    SharedPreferences ajustes=null;
    Context mContext=null;
    static KeyguardManager.KeyguardLock kl=null;
    static KeyguardManager km=null;
    static int pulsado=0;
    @Override
    public void onReceive(Context context, Intent intent) {
        ajustes = context.getSharedPreferences("gestureAjustes", Context.MODE_PRIVATE);
        String action = intent.getAction();
        mContext = context;
        km = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
        if(kl==null) {
            kl = km.newKeyguardLock(String.valueOf("MyKey"));

        }

        //reenableKeyguard();
        //kl.disableKeyguard();
        if (action.equals(Intent.ACTION_SCREEN_OFF)) {
            reenableKeyguard();
        } else if (action.equals(Intent.ACTION_SCREEN_ON)) {
        } else if (action.equals("android.intent.action.GESTURE")) {
            Bundle extras = intent.getExtras();
            int key_code=-1;
            key_code=intent.getIntExtra("key_code",-1);
            if(key_code!=-1) {

                ComponentName componetName = null;
                if(key_code==19 && ajustes.getBoolean("drawUpU",false)){
                    pulsado=pulsado+1;
                    if(pulsado==2) {
                        unlockScreen(context,"Arriba","Desbloquear");
                        pulsado = 0;
                    }
                }else if(key_code==20 && ajustes.getBoolean("drawDownM",false)){
                    pulsado=pulsado+1;
                    if(pulsado==2) {
                        context.sendBroadcast(new Intent("com.android.music.musicservicecommand.togglepause"), null);
                        pulsado = 0;
                    }
                }else if(key_code==21 && ajustes.getBoolean("drawLeftM",false)){
                    pulsado=pulsado+1;
                        if(pulsado==2) {
                            context.sendBroadcast(new Intent("com.android.music.musicservicecommand.previous"), null);
                            pulsado = 0;
                        }
                }else if(key_code==22 && ajustes.getBoolean("drawRightM",false)){
                    pulsado=pulsado+1;
                            if(pulsado==2) {
                                context.sendBroadcast(new Intent("com.android.music.musicservicecommand.next"), null);
                                pulsado = 0;
                            }
                }else if(key_code==31 && ajustes.getBoolean("drawC",false)){
                    pulsado=pulsado+1;
                                if(pulsado==2) {
                                    componetName = getGestureAppName(31, context);
                                    if (isApkExist(context, componetName.getPackageName())) {
                                        launchGestureApp(componetName, context);
                                    }
                                    unlockScreen(context,"c",ajustes.getString("persist.sys.off_c_def_appName",""));
                                    pulsado = 0;
                                }
                }else if(key_code==33 && ajustes.getBoolean("drawE",false)){
                    pulsado=pulsado+1;
                                    if(pulsado==2) {
                                        componetName = getGestureAppName(33, context);
                                        if (isApkExist(context, componetName.getPackageName())) {
                                            launchGestureApp(componetName, context);
                                        }
                                        unlockScreen(context,"e",ajustes.getString("persist.sys.off_e_def_appName",""));
                                        pulsado = 0;
                                    }
                }else if(key_code==41 && ajustes.getBoolean("drawM",false)){
                    pulsado=pulsado+1;
                                        if(pulsado==2) {
                                            componetName = getGestureAppName(41, context);
                                            if (isApkExist(context, componetName.getPackageName())) {
                                                launchGestureApp(componetName, context);
                                            }
                                            unlockScreen(context,"m",ajustes.getString("persist.sys.off_m_def_appName",""));
                                            pulsado = 0;
                                        }
                }else if(key_code==43 && ajustes.getBoolean("drawO",false)){
                    pulsado=pulsado+1;
                                            if(pulsado==2) {
                                                componetName = getGestureAppName(43, context);
                                                if (isApkExist(context, componetName.getPackageName())) {
                                                    launchGestureApp(componetName, context);
                                                }
                                                unlockScreen(context,"o",ajustes.getString("persist.sys.off_o_def_appName",""));
                                                pulsado = 0;
                                            }
                }else if(key_code==49 && ajustes.getBoolean("drawDC",false)){
                    pulsado=pulsado+1;
                                                if(pulsado==2) {
                                                    unlockScreen(context,"Doble Click","Desbloquear");
                                                    pulsado = 0;
                                                }
                }else if(key_code==51 && ajustes.getBoolean("drawW",false)){
                    pulsado=pulsado+1;
                    if(pulsado==2) {
                        componetName = getGestureAppName(51, context);
                        if (isApkExist(context, componetName.getPackageName())) {
                            launchGestureApp(componetName, context);
                        }
                        unlockScreen(context,"w",ajustes.getString("persist.sys.off_w_def_appName",""));
                        pulsado = 0;
                    }
                }else if(key_code==26){
                    reenableKeyguard();
                    pulsado=0;
                }else if(key_code==26){
                    kl.disableKeyguard();
                    pulsado=0;
                }else if(key_code==24||key_code==25||key_code==4||key_code==3||key_code==82){
                    kl.disableKeyguard();
                    pulsado=0;
                }else{
                    reenableKeyguard();
                }/*else if(key_code==3 && ajustes.getBoolean("doubleLock",false) && pulsado==4){/*else if(key_code==3 && ajustes.getBoolean("doubleLock",false) && pulsado==4){

                    pulsado=0;
                }*/

            }
            //26 apagado
            //19 desliza arriba
            //20 desliza abajo
            //21 deslizar izq
            //22 desliza a derecha
            //31 c
            //41 m
            //43 o
            //33 e
            //49 doble click
            //51 w
        }else{
            reenableKeyguard();
            pulsado=0;
        }

    }
    private void reenableKeyguard(){
        kl.reenableKeyguard();
    }
    private boolean isApkExist(Context context, String packageName){
        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }

        return true;
    }
    private void launchGestureApp(ComponentName componetName,Context context) {
        Intent intent = new Intent();
        intent.setComponent(componetName);
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
    private ComponentName getGestureAppName (int gesture,Context context) {
        String packageName = null;
        String activityName = null;
        String defaultAppName = null;
        switch (gesture) {
            case 31:
                defaultAppName = ajustes.getString("persist.sys.off_c_def_name", "com.android.gallery3d&com.android.camera.CameraLauncher");
                packageName = ajustes.getString("persist.sys.off_c_pkg", defaultAppName.split("&")[0]);
                activityName = ajustes.getString("persist.sys.off_c_act", defaultAppName.split("&")[1]);
                break;
            case 33:
                defaultAppName = ajustes.getString("persist.sys.off_e_def_name", "com.android.browser&com.android.browser.BrowserActivity");
                packageName = ajustes.getString("persist.sys.off_e_pkg", defaultAppName.split("&")[0]);
                activityName = ajustes.getString("persist.sys.off_e_act", defaultAppName.split("&")[1]);
                break;
            case 41:
                if (isApkExist(context, "com.android.music")) {
                    defaultAppName = ajustes.getString("persist.sys.off_m_def_name", "com.android.music&com.android.music.MusicBrowserActivity");
                } else {
                    defaultAppName = ajustes.getString("persist.sys.off_m_def_name", "com.v5music&com.v5music.view.activity.SplashActivity");
                }
                packageName = ajustes.getString("persist.sys.off_m_pkg", defaultAppName.split("&")[0]);
                activityName = ajustes.getString("persist.sys.off_m_act", defaultAppName.split("&")[1]);
                break;

            case 43:
                defaultAppName = ajustes.getString("persist.sys.off_o_def_name", "com.android.dialer&com.android.dialer.DialtactsActivity");
                packageName = ajustes.getString("persist.sys.off_o_pkg", defaultAppName.split("&")[0]);
                activityName = ajustes.getString("persist.sys.off_o_act", defaultAppName.split("&")[1]);
                break;
            case 51:
                defaultAppName = ajustes.getString("persist.sys.off_w_def_name", "com.mediatek.filemanager&com.mediatek.filemanager.FileManagerOperationActivity");
                packageName = ajustes.getString("persist.sys.off_w_pkg", defaultAppName.split("&")[0]);
                activityName = ajustes.getString("persist.sys.off_w_act", defaultAppName.split("&")[1]);
                break;
            default:
                break;
        }
        ComponentName componetName = new ComponentName(packageName, activityName);
        return componetName;
    }

    private void unlockScreen(Context context,String letra,String app) {
        PowerManager powermanager=  ((PowerManager)context.getSystemService(Context.POWER_SERVICE));
        PowerManager.WakeLock tag = powermanager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "tag");
        tag.acquire(100);

        /*Intent goHome = new Intent();
        goHome.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        goHome.setAction("android.intent.action.MAIN");
        goHome.addCategory("android.intent.category.HOME");
        context.startActivity(goHome);
*/
        if(letra!=null && !"".equals(letra)){
            //Toast.makeText(context,letra+" "+app,Toast.LENGTH_SHORT).show();

        }
        kl.disableKeyguard();
        /*Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                kl.reenableKeyguard();
            }
        }, 300);
*/
    }
    protected ServiceConnection mServerConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}
