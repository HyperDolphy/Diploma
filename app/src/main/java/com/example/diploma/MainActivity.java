package com.example.diploma;
import android.app.KeyguardManager;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.content.Context;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button InternetCheckBut;
    private Button IsRootedBut;
    private Button PasswordCheck;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    @Override
    protected void onStart() {
        super.onStart();
        InternetCheckBut = findViewById(R.id.button);
        InternetCheckBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (InternetConnection.checkConnection(MainActivity.this)) {
                    Toast.makeText(MainActivity.this, "Подключение есть", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Нет подключения", Toast.LENGTH_SHORT).show();
                }
            }
        });
        IsRootedBut = findViewById(R.id.button5);
        IsRootedBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (RootedOrNot.isRooted(MainActivity.this)) {
                    Toast.makeText(MainActivity.this, "Права суперпользователя есть", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Прав суперпользователя нет", Toast.LENGTH_SHORT).show();
                }
            }
        });

        PasswordCheck = findViewById(R.id.button6);
        PasswordCheck.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            public void onClick(View v) {

                KeyguardManager keyguardManager = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);

                // проверяем, настроен ли экран блокировки на устройстве
                boolean isSecure = keyguardManager.isDeviceSecure();

                // выводим результат в виде уведомления типа toast
                if (isSecure) {
                    Toast.makeText(MainActivity.this, "Экран блокировки настроен", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Экран блокировки не настроен", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
