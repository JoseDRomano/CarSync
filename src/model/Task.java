package model;

import java.util.List;

public class Task implements Comparable<Task> {

    private int taskID;
    private String taskType;
    private String taskStatus;
    private String taskDate;
    private int nif;
    private String values;

    public Task(int taskID, String taskType, String taskStatus, String taskDate, int nif, String values) {
        this.taskID = taskID;
        this.taskType = taskType;
        this.taskStatus = taskStatus;
        this.taskDate = taskDate;
        this.nif = nif;
        this.values = values;
    }

    private enum TaskType {
        CUSTOMER_REGISTRATION, VEHICLE_REGISTRATION,
        INSURANCE_REGISTRATION, TICKET_REGISTRATION, EMPLOYEE_REGISTRATION,
        CUSTOMER_REMOVAL, VEHICLE_REMOVAL,
        INSURANCE_REMOVAL, TICKET_REMOVAL, EMPLOYEE_REMOVAL,
        CUSTOMER_DEACTIVATION, VEHICLE_DEACTIVATION,
        INSURANCE_DEACTIVATION, TICKET_DEACTIVATION, EMPLOYEE_DEACTIVATION,
        CUSTOMER_UPDATE, VEHICLE_UPDATE,
        INSURANCE_UPDATE, TICKET_UPDATE, EMPLOYEE_UPDATE;
    }

    public Task(int taskID, String taskType, String taskDate, int nif, String values) {
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
        return String.format(s, taskID, taskType, taskStatus, taskDate, nif, values);
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
