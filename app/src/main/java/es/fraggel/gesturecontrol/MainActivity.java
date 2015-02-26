package es.fraggel.gesturecontrol;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    Button screenOff=null;
    Button screenOn=null;
    Button screenAir=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screenOff=(Button)findViewById(R.id.screenOff);
        screenOn=(Button)findViewById(R.id.screenOn);
        screenAir=(Button)findViewById(R.id.screenAir);

        //get propiedades para ver en que estado están

        screenOff.setOnClickListener(this);
        screenOn.setOnClickListener(this);
        screenAir.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.screenOff){
            Intent act=new Intent(this,screenOffActivity.class);
            startActivity(act);
        }else if(view.getId()==R.id.screenOn){
            Intent act=new Intent(this,screenOnActivity.class);
            startActivity(act);
        }else if(view.getId()==R.id.screenAir){
            Intent act=new Intent(this,AirGestureActivity.class);
            startActivity(act);
        }
    }
}
