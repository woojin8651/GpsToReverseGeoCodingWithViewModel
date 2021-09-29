package com.example.testapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.testapp.Util.Permission;
import com.example.testapp.ViewModel.MainViewModel;

@RequiresApi(api = Build.VERSION_CODES.N)
public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    MainViewModel viewModel;
    Permission permission;

    TextView tv_address;
    Button bt_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setViewToVM();


        //화면을 실행시키면 permission 확인

    }
    private void init(){
        permission = new Permission(this,this);
        permission.init();

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.init();

        tv_address = findViewById(R.id.tv_address);
        bt_address = findViewById(R.id.bt_addr);
        bt_address.setOnClickListener(this);
    }
    private void setViewToVM(){
        viewModel.getLiveDTO().observe(this, dto -> {
            tv_address.setText(dto.getAddr().get(0));
            // viewlogic
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permission.requestResult(requestCode,permissions,grantResults);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_addr:
                viewModel.updateCord(this);
                break;
            default:
                break;
        }
    }
}