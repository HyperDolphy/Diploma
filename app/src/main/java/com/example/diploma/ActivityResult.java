package com.example.diploma;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityResult extends AppCompatActivity {
    private Button SaveResult;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        // Получение ссылки на текстовое поле
        TextView resultsTextView = findViewById(R.id.resultsTextView);

        Intent intent = getIntent();
        if (intent != null) {
            ArrayList<String> ResultList = intent.getStringArrayListExtra("ResultList");
            if (ResultList != null) {
                StringBuilder stringBuilder = new StringBuilder();
                for (String text : ResultList) {
                    stringBuilder.append(text).append("\n");
                }
                resultsTextView.setText(stringBuilder.toString());
            }
        }
        SaveResult = findViewById(R.id.button7);
        SaveResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = resultsTextView.getText().toString();
                saveResultToFile(result);
            }
        });
    }
    public void saveResultToFile(String result) {
        try {
            // Создание файла с уникальным именем
            String fileName = "result_" + System.currentTimeMillis() + ".txt";

            // Получение пути к папке Documents
            File documentsDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

            // Создание объекта файла в папке Documents
            File file = new File(documentsDirectory, fileName);

            // Создание объекта FileWriter для записи в файл
            FileWriter writer = new FileWriter(file);
            writer.append(result);
            writer.flush();
            writer.close();

            // Оповещение пользователя об успешном сохранении
            Toast.makeText(this, "Результат сохранен в файл: " + fileName, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            // Оповещение пользователя об ошибке сохранения
            Toast.makeText(this, "Ошибка сохранения результата", Toast.LENGTH_SHORT).show();
        }
    }

    public void ReturnBack(View v){
     Intent intent = new Intent(this, MainActivity.class);
     startActivity(intent);
 }
    //ArrayList<String> ResultList = new ArrayList<>();
}
