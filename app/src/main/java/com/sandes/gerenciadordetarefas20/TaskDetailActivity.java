package com.sandes.gerenciadordetarefas20;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TaskDetailActivity extends AppCompatActivity {
    private EditText titleEditText;
    private EditText descriptionEditText;
    private EditText dateEditText;

    private Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        titleEditText = findViewById(R.id.titleEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        dateEditText = findViewById(R.id.dateEditText);
        Button saveButton = findViewById(R.id.saveButton);

        task = getIntent().getParcelableExtra("task");
        if (task != null) {
            titleEditText.setText(task.getTitle());
            descriptionEditText.setText(task.getDescription());
            dateEditText.setText(task.getDate());
        }

        saveButton.setOnClickListener(v -> {
            String title = titleEditText.getText().toString();
            String description = descriptionEditText.getText().toString();
            String date = dateEditText.getText().toString();

            if (TextUtils.isEmpty(title)) {
                Toast.makeText(TaskDetailActivity.this,
                        "Por favor, adicione um título", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(date)) {
                Toast.makeText(TaskDetailActivity.this,
                        "Por favor, adicione uma data", Toast.LENGTH_SHORT).show();
                return;
            }

            if (task == null) { // Se não há uma tarefa existente, estamos adicionando uma nova
                Task newTask = new Task(title, description, date);
                Intent resultIntent = new Intent();
                resultIntent.putExtra("newTask", newTask);
                setResult(Activity.RESULT_OK, resultIntent);
            } else { // Se há uma tarefa existente, estamos editando ela
                task.setTitle(title);
                task.setDescription(description);
                task.setDate(date);
                Intent resultIntent = new Intent();
                resultIntent.putExtra("editedTask", task);
                setResult(Activity.RESULT_OK, resultIntent);
            }
            finish();
        });
    }
}



