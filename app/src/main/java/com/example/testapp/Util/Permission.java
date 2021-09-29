package com.example.testapp.Util;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Permission {

    private final Context mContext;
    private final Activity mActivity;

    private final String TAG = "Permission";
    private final String[] Required_Permissions = {Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION};

    public static int PERMISSION_REQUEST_CODE = 1111;
    public static int GPS_ENABLE_REQUEST_CODE = 2222;

    public Permission(Context mContext,Activity mActivity) {
        this.mContext = mContext;
        this.mActivity = mActivity;
    }

    public void init(){
        if(checkLocationServiceStatus()){
            checkRunTimePermission();
        }
        else{
            showDialogForLocationServiceSetting();
        }
    }
    public void requestResult(int permsRequestCode,
                              @NonNull String[] permissions,
                              @NonNull int[] grandResults){
        if(permsRequestCode == PERMISSION_REQUEST_CODE && grandResults.length == Required_Permissions.length){
            // 기본적인 확인 절차
            boolean available = true; // 사용가능한가?

            for(int result:grandResults){
                if(result != PackageManager.PERMISSION_GRANTED){ //허용되지 않은게 하나라도 있으면 종료
                    available = false;
                    break;
                }
            }
            if(available){
                Log.d(TAG,"All Permission Granted");
            }
            else{
                if(ActivityCompat.shouldShowRequestPermissionRationale(mActivity,Required_Permissions[0])||
                        ActivityCompat.shouldShowRequestPermissionRationale(mActivity,Required_Permissions[1])){
                    Toast.makeText(mContext,"퍼미션 거부! 앱을 다시 실행해주세요",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(mContext,"퍼미션 거부! 설정에서 퍼미션을 허용해야 합니다.",Toast.LENGTH_LONG).show();
                }
            }
        }

    }
    public void checkRunTimePermission(){
        int hasFineLocationPermission = ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION);
        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION);

        if(hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
        hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED){
            Log.d(TAG,"Permission Granted");
        }
        else{
            Log.d(TAG,"Permission not Granted So request");
            ActivityCompat.requestPermissions(mActivity,Required_Permissions,PERMISSION_REQUEST_CODE);
        }
    }
    public void showDialogForLocationServiceSetting(){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("위치 서비스 비활성화");
        builder.setMessage("앱을 사용하기 위해선 위치서비스가 필요합니다.\n");
        builder.setCancelable(true);
        builder.setPositiveButton("설정", (dialog, which) -> {
            Intent callGPSSettingIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            mActivity.startActivityForResult(callGPSSettingIntent,GPS_ENABLE_REQUEST_CODE);
        });
        builder.setNegativeButton("취소", (dialog, which) -> dialog.cancel());
        builder.show();
    }
    public boolean checkLocationServiceStatus(){
        LocationManager locationManager = (LocationManager)mContext.getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }
}
