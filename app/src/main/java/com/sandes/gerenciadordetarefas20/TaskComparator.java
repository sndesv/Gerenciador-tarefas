package com.sandes.gerenciadordetarefas20;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

public class TaskComparator implements Comparator<Task> {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy",
            Locale.getDefault());

    @Override
    public int compare(Task task1, Task task2) {
        try {
            Date date1 = dateFormat.parse(task1.getDate());
            Date date2 = dateFormat.parse(task2.getDate());
            // Ordena as tarefas pela data, da mais próxima para a mais distante
            return date1.compareTo(date2);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }
}

