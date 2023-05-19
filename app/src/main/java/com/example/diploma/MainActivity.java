package com.example.diploma;
import android.app.KeyguardManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.content.Context;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Button InternetCheckBut;
    private Button IsRootedBut;
    private Button PasswordCheck;
    private Button FirewallCheckerBut;
    private Button AntivirusButton;
    private Button MicCamPermBut;
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
                    String result = "Проверка подключения к интернету." +
                            "\nРезультат: Подключение к интернету есть" +
                            "\nВремя: " + getCurrentTime();
                    ResultList.add(result);
                } else {
                    Toast.makeText(MainActivity.this, "Нет подключения", Toast.LENGTH_SHORT).show();
                    String result = "Проверка подключения к интернету." +
                            "\nРезультат: Подключение к интернету отсутствует" +
                            "\nВремя: " + getCurrentTime();
                    ResultList.add(result);
                }
               // updateResultsText(ResultList);
            }

        });
        MicCamPermBut = findViewById(R.id.button2);
        MicCamPermBut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                List<String> appsWithPermissions = AppPermissionsChecker.getAppsUsingMicrophoneAndCamera(MainActivity.this);
                StringBuilder stringBuilder = new StringBuilder();
                for (String appName : appsWithPermissions) {
                    stringBuilder.append(appName).append("\n");
                }
                Toast.makeText(MainActivity.this, "Результаты будут доступны в отчете", Toast.LENGTH_SHORT).show();
                String result = "Проверка доступа приложений к камере и микрофону"  +
                        "\nРезультат: Список приложений с доступом к камере и микрофону:\n"
                        +stringBuilder.toString()+
                        "\nВремя: " + getCurrentTime();
                ResultList.add(result);


            }
        });
        IsRootedBut = findViewById(R.id.button5);
        IsRootedBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (RootedOrNot.isRooted(MainActivity.this)) {
                    Toast.makeText(MainActivity.this, "Права суперпользователя есть", Toast.LENGTH_SHORT).show();
                    String result = "Проверка наличия прав суперпользователя." +
                            "\nРезультат: Права суперпользователя имеются" +
                            "\nВремя: " + getCurrentTime();
                    ResultList.add(result);
                } else {
                    Toast.makeText(MainActivity.this, "Прав суперпользователя нет", Toast.LENGTH_SHORT).show();
                    String result = "Проверка наличия прав суперпользователя." +
                            "\nРезультат: Права суперпользователя отсутствуют" +
                            "\nВремя: " + getCurrentTime();
                    ResultList.add(result);
                }
                // updateResultsText(ResultList);
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
                    String result = "Проверка безопасности экрана блокировки." +
                            "\nРезультат: Телефон защищен" +
                            "\nВремя: " + getCurrentTime();
                    ResultList.add(result);
                } else {
                    Toast.makeText(MainActivity.this, "Экран блокировки не настроен", Toast.LENGTH_SHORT).show();
                    String result = "Проверка безопасности экрана блокировки." +
                            "\nРезультат: Телефон не защищен" +
                            "\nВремя: " + getCurrentTime();
                    ResultList.add(result);
                }

            }
        });
        FirewallCheckerBut = findViewById(R.id.button4);
        FirewallCheckerBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FirewallChecker.isFirewallEnabled(MainActivity.this)) {
                    Toast.makeText(MainActivity.this, "Межсетевой экран работает", Toast.LENGTH_SHORT).show();
                    String result = "Проверка работоспособности МЭ." +
                            "\nРезультат: Межсетевой экран работает" +
                            "\nВремя: " + getCurrentTime();
                    ResultList.add(result);
                } else {
                    Toast.makeText(MainActivity.this, "Межсетевой экран не работает", Toast.LENGTH_SHORT).show();
                    String result = "Проверка работоспособности МЭ." +
                            "\nРезультат: Межсетевой экран не работает" +
                            "\nВремя: " + getCurrentTime();
                    ResultList.add(result);
                }
                //updateResultsText(ResultList);
            }
        });
        AntivirusButton = findViewById(R.id.button3);
            AntivirusButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                try {
                    if (AntivirusChecker.isAntivirusEnabled(MainActivity.this)) {
                        Toast.makeText(MainActivity.this, "Антивирус работает", Toast.LENGTH_SHORT).show();
                        String result = "Проверка работоспособности антивируса." +
                                "\nРезультат: Антивирус работает" +
                                "\nВремя: " + getCurrentTime();
                        ResultList.add(result);
                    } else {
                        Toast.makeText(MainActivity.this, "Антивирус не работает", Toast.LENGTH_SHORT).show();
                        String result = "Проверка работоспособности антивируса." +
                                "\nРезультат: Антивирус не работает" +
                                "\nВремя: " + getCurrentTime();
                        ResultList.add(result);
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                }
                //updateResultsText(ResultList);
            }
            });
    }
        public void ResultsActivity(View v){
            Intent intent = new Intent(this, ActivityResult.class);
            startActivity(intent);
            updateResultsText(ResultList);
        };
    private int resultNumber = 1;

    private int getResultNumber() {
        return resultNumber++;
    }

    private String getCurrentTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss\n----------------------------------", Locale.getDefault());
        Date currentDate = new Date();
        return dateFormat.format(currentDate);
    }
    private void updateResultsText(ArrayList<String> ResultList) {
        Intent intent = new Intent(this, ActivityResult.class);
        intent.putStringArrayListExtra("ResultList", ResultList);
        startActivity(intent);
    }

}
