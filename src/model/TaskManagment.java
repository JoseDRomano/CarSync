package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TaskManagment {

    private List<Task> taskList;
    private final static Path filePath = Path.of("Tasks.txt");
    private Scanner scanner;

    public TaskManagment() {
        taskList = new ArrayList<>();
        readTasksFromFile();
    }

    public void printList() {
        taskList.forEach(task -> System.out.println(task.toString()));
    }

    private void readTasksFromFile() {
        try {
            scanner = new Scanner(filePath);
            while (scanner.hasNextLine()) {

                int taskID = getID(scanner.nextLine());
                String taskType = taskType(scanner.nextLine());
                String taskStatus = taskStatus(scanner.nextLine());
                String taskDate = taskDate(scanner.nextLine()).toString();
                int nif = getNIF(scanner.nextLine());
                String taskInfo = taskInfo(scanner.nextLine());

                if(taskID == -1 || taskType == null || taskStatus == null ||
                        taskDate == null || nif == -1 || taskInfo == null){
                    System.out.println("File is empty");
                    return;
                }

                taskList.add(new Task(taskID, taskType, taskStatus,
                        taskDate.toString(),
                        nif,
                        taskInfo));

                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }

            }

        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
            throw new RuntimeException(e);
        } finally {
            scanner.close();
        }
        taskList.sort(Task::compareTo);
    }

    private int getNewID() {
        return taskList.isEmpty() ? 1 : taskList.get(taskList.size() - 1).getTaskID() + 1;
    }


    public boolean writeTaskToFile(String type, String status, String date, int nif, String info) {
        Task task = new Task(getNewID(), type, status, date, nif, info);
        boolean result = false;
        try {
            Files.write(filePath, task.toString().getBytes(), StandardOpenOption.CREATE,
                    StandardOpenOption.WRITE, StandardOpenOption.APPEND);
            result = true;
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
            e.printStackTrace();
        }
        taskList.add(task);
        return result;
    }

    public boolean deleteTaskFromFile(int taskID) {
        boolean result = false;
        taskList.removeIf(task -> task.getTaskID() == taskID);
        taskList.sort(Task::compareTo);
        for(int i = 0; i < taskList.size(); i++) {
            taskList.get(i).setTaskID(i + 1);
        }
        try {
            for (Task task : taskList) {
                Files.write(filePath, task.toString().getBytes(), StandardOpenOption.CREATE,
                        StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
            }
            result = true;
        } catch (IOException e) {
            System.out.println("Error deleting file: " + e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public boolean updateTaskStatus(String status, int taskID) {
        Path filePath = Path.of("tasks.txt");
        boolean result = false;
        for (Task task : taskList) {
            if (task.getTaskID() == taskID) {
                task.setTaskStatus(status);
            }
        }
        taskList.sort(Task::compareTo);
        try {
            for (Task task : taskList) {
                Files.write(filePath, task.toString().getBytes(), StandardOpenOption.CREATE,
                        StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
            }
            result = true;
        } catch (IOException e) {
            System.out.println("Error updating file: " + e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    private static int getID(String s) {
        if(s.isEmpty() || s.isBlank()) {
            return -1;
        }
        String[] svalues = s.split(":");
        return Integer.parseInt(svalues[1].trim());
    }

    private static String taskType(String s) {
        if(s.isEmpty() || s.isBlank()) {
            return null;
        }
        String[] svalues = s.split(":");
        String type = svalues[1];
        return type.trim();
    }

    private static String taskStatus(String s) {
        if(s.isEmpty() || s.isBlank()) {
            return null;
        }
        String[] svalues = s.split(":");
        String status = svalues[1];
        return status.trim();
    }

    private static java.sql.Date taskDate(String s) {
        if(s.isEmpty() || s.isBlank()) {
            return null;
        }
        String[] svalues = s.split(":");
        java.sql.Date date = java.sql.Date.valueOf(svalues[1].trim());
        return date;
    }

    private static int getNIF(String s) {
        if(s.isEmpty() || s.isBlank()) {
            return -1;
        }
        String[] svalues = s.split(":");
        int nif = Integer.parseInt(svalues[1].trim());
        return nif;
    }

    private static String taskInfo(String s) {
        if(s.isEmpty() || s.isBlank()) {
            return null;
        }
        String[] svalues = s.split(":");
        String info = svalues[1].trim();
        return info;
    }
}
