package com.sandes.gerenciadordetarefas20;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_EDIT_TASK = 1002;
    private static final int REQUEST_CODE_ADD_TASK = 1002;
    private List<Task> taskList;
    private TaskAdapter taskAdapter;
    private ListView taskListView;
    private Button addTaskButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskListView = findViewById(R.id.taskListView);
        addTaskButton = findViewById(R.id.addTaskButton);

        taskList = new ArrayList<>();

        taskList.add(new Task("Tarefa 1", "Descrição da Tarefa 1", "17-05-2024"));
        taskList.add(new Task("Tarefa 2", "Descrição da Tarefa 2", "18-06-2024"));
        taskList.add(new Task("Tarefa 3", "Descrição da Tarefa 3", "19-04-2024"));

        taskAdapter = new TaskAdapter(this, taskList);
        taskListView.setAdapter(taskAdapter);

        taskListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Task task = taskList.get(position);
                Intent intent = new Intent(MainActivity.this, TaskDetailActivity.class);
                intent.putExtra("task", task);
                startActivityForResult(intent, REQUEST_CODE_EDIT_TASK);
            }
        });



        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TaskDetailActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADD_TASK);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD_TASK && resultCode == Activity.RESULT_OK) {
            Task newTask = data.getParcelableExtra("newTask");
            addTask(newTask);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Ordena a lista de tarefas pela data
        Collections.sort(taskList, new TaskComparator());
        taskAdapter.notifyDataSetChanged();
    }


    public void addTask(Task newTask) {
        taskList.add(newTask);
        taskAdapter.notifyDataSetChanged();
    }

    public void deleteTask(int position) {
        taskList.remove(position);
        taskAdapter.notifyDataSetChanged();
    }
}
