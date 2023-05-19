package com.example.diploma;
import android.app.KeyguardManager;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.content.Context;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button InternetCheckBut;
    private Button IsRootedBut;
    private Button PasswordCheck;
    private Button NewActivityBut;
    private Button FirewallCheckerBut;
    private TextView resultsTextView;
    ArrayList<String> ResultList = new ArrayList<>();

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
                    ResultList.add("Проверка подключения к интернету. Результат: Подключение к интернету есть");//resultsTextView.setText("Проверка подключения к интернету. Результат: Подключение к интернету есть");
                } else {
                    Toast.makeText(MainActivity.this, "Нет подключения", Toast.LENGTH_SHORT).show();
                    resultsTextView.setText("Проверка подключения к интернету. Результат: Подключение к интернету отсутствует");
                }
            }
        });
        IsRootedBut = findViewById(R.id.button5);
        IsRootedBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (RootedOrNot.isRooted(MainActivity.this)) {
                    Toast.makeText(MainActivity.this, "Права суперпользователя есть", Toast.LENGTH_SHORT).show();
                    resultsTextView.setText("Проверка на права суперпользователя. Результат: Права суперпользователя есть");
                } else {
                    Toast.makeText(MainActivity.this, "Прав суперпользователя нет", Toast.LENGTH_SHORT).show();
                    resultsTextView.setText("Проверка на права суперпользователя. Результат: Прав суперпользователя нет");
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
                    resultsTextView.setText("Проверка настройки экрана блокировки. Результат: Экран блокировки настроен");
                } else {
                    Toast.makeText(MainActivity.this, "Экран блокировки не настроен", Toast.LENGTH_SHORT).show();
                    resultsTextView.setText("Проверка настройки экрана блокировки. Результат: Экран блокировки не настроен");
                }
            }
        });
        FirewallCheckerBut = findViewById(R.id.button4);
        FirewallCheckerBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FirewallChecker.isFirewallEnabled(MainActivity.this)) {
                    Toast.makeText(MainActivity.this, "Межсетевой экран работает", Toast.LENGTH_SHORT).show();
                    resultsTextView.setText("Проверка работоспособности межсетевого экрана. Результат:Межсетевой экран работает");
                } else {
                    Toast.makeText(MainActivity.this, "Межсетевой экран не работает", Toast.LENGTH_SHORT).show();
                    resultsTextView.setText("Проверка работоспособности межсетевого экрана. Результат:Межсетевой экран не работает");
                }
            }
        });
        NewActivityBut = findViewById(R.id.button8);

        NewActivityBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityResult.class);
                startActivity(intent);
            }
        });

    }
}
