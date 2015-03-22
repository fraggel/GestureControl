package es.fraggel.gesturecontrol;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;

public class MyService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
    BroadcastReceiver mReceiver=null;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        final IntentFilter filter = new IntentFilter("android.intent.action.GESTURE");
        /*mReceiver = new gestureReceiver();
        registerReceiver(mReceiver, filter);*/
        return super.onStartCommand(intent, flags, startId);
    }
    public class LocalBinder extends Binder {
        MyService getService() {
            return MyService.this;
        }
    }
    @Override
    public void onDestroy(){
        //unregisterReceiver(mReceiver);
    }
}
