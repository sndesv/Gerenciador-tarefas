package com.sandes.gerenciadordetarefas20;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class TaskAdapter extends ArrayAdapter<Task> {
    public TaskAdapter(Context context, List<Task> tasks) {
        super(context, 0, tasks);
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Task task = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_task,
                    parent, false);
        }
        TextView titleTextView = convertView.findViewById(R.id.titleTextView);
        TextView dateTextView = convertView.findViewById(R.id.dateTextView);
        ImageView deleteImageView = convertView.findViewById(R.id.deleteImageView);

        titleTextView.setText(task.getTitle());
        dateTextView.setText(task.getDate());

        // adiciona um Listener de clique ao botão de excluir
        deleteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getContext()).deleteTask(position);
            }
        });

        return convertView;
    }
}