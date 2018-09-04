package com.moyu.zxingproject;

import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    public boolean hasPremission(String...premissions){

        for(String premission : premissions){
            if(ContextCompat.checkSelfPermission(this,premission) != PackageManager.PERMISSION_GRANTED){
                   return true;
            }
        }
        return false;
    }

    public void requestPremission(int code,String...premissions){
        ActivityCompat.requestPermissions(this,premissions,code);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case Constant.CAMERA:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    doCamera();
                }else{
                    Toast.makeText(this,"权限未授予!",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    public void doCamera() {

    }

}
