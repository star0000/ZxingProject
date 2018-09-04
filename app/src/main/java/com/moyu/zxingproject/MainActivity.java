package com.moyu.zxingproject;

import android.Manifest;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.tbruyelle.rxpermissions.RxPermissions;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

public class MainActivity extends BaseActivity {

    private int REQUEST_CODE = 5;
    private Button btnZxing;
    //private RxPermissions rxPermissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnZxing = (Button)findViewById(R.id.btn_zxing);

       // rxPermissions = new RxPermissions(this);

        btnZxing.setOnClickListener(new View.OnClickListener() {

            private Intent intent;

            @Override
            public void onClick(View v) {

                camera();

//                rxPermissions.request(Manifest.permission.CAMERA)
//                             .subscribe(granted -> {
//                            if (granted) { // Always true pre-M
//                                //如果已经授权就直接跳转到二维码扫面界面
//                                Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
//                                startActivityForResult(intent, REQUEST_CODE);
//                                Toast.makeText(MainActivity.this, "扫一扫", Toast.LENGTH_SHORT).show();
//                            } else { // Oups permission denied
//                                Toast.makeText(MainActivity.this, "相机权限被拒绝，无法扫描二维码", Toast.LENGTH_SHORT).show();
//                                return;
//                            }
//                        });
            }
        });

    }

    private void camera() {

        if(hasPremission(Manifest.permission.CAMERA)){
            requestPremission(Constant.CAMERA,Manifest.permission.CAMERA);
        }else{
            doCamera();
        }
    }

    public void doCamera(){

        Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
        Toast.makeText(MainActivity.this, "扫一扫", Toast.LENGTH_SHORT).show();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE){
            if(null != data){
                Bundle bundle = data.getExtras();
                if(bundle == null){
                   return;
                }
                if(bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS){
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                }else if(bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED){
                    Toast.makeText(MainActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }

    }











}
