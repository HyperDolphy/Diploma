package com.example.diploma;

import android.os.Bundle;
import android.widget.TextView;
import java.util.ArrayList;
import androidx.appcompat.app.AppCompatActivity;


public class ActivityResult extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        // Получение ссылки на текстовое поле
        TextView resultsTextView = findViewById(R.id.resultsTextView);

        // Получение результатов проверок
       // Предположим, что у вас есть метод, который возвращает результаты проверок в виде строки

        // Установка результатов в текстовое поле


        // Другой код для сохранения результатов
    }

    ArrayList<String> ResultList = new ArrayList<>();
}
