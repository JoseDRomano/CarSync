package model;

import java.util.List;

public class Task implements Comparable<Task> {

    private int taskID;
    private TaskType taskType;
    private String taskStatus;
    private String taskDate;
    private int nif;
    private String values;

    public Task(int taskID, TaskType taskType, String taskStatus, String taskDate, int nif, String values) {
        this.taskID = taskID;
        this.taskType = taskType;
        this.taskStatus = taskStatus;
        this.taskDate = taskDate;
        this.nif = nif;
        this.values = values;
    }

    public Task(int taskID, TaskType taskType, String taskDate, int nif, String values) {
        new Task(taskID, taskType, "Open", taskDate, nif, values);
    }

    @Override
    public String toString() {
        String s = """
                Task ID: %d
                    Type: %s
                    Status: %s
                    Date: %s
                    NIF: %d
                    Values: %s
                
                """;
        return String.format(s, taskID, TaskType.getDescription(taskType), taskStatus, taskDate, nif, values);
    }


    @Override
    public int compareTo(Task o) {
        return taskID - o.taskID;
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus.equals("Closed") ? "Closed" : "Open";
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }
}
