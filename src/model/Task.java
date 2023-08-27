package model;

import employeeacess.DataSource;

import java.sql.Date;
import java.util.Objects;

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

    public TaskType getTaskType() {
        return taskType;
    }

    public String getTaskStatus() {
        return Objects.equals(taskStatus, "Open") ? "Open" : "Closed";
    }

    public boolean perFormTask(DataSource dataSource) {
        String[] allValues = values.split(",");
        taskStatus = "Closed";
        switch (taskType) {
            case CUSTOMER_REGISTRATION -> {
                return dataSource.insertCustomer(nif, allValues[0], allValues[1],
                        Date.valueOf(allValues[2]), allValues[3], allValues[4],
                        Integer.parseInt(allValues[5]), allValues[6], Date.valueOf(allValues[7]),
                        Date.valueOf(allValues[8]));
            }

            case VEHICLE_REGISTRATION -> {
                return dataSource.insertVehicle(allValues[0], allValues[1], allValues[2],
                        allValues[3], allValues[4], Date.valueOf(allValues[5]),
                        allValues[6], nif);
            }

            case INSURANCE_REGISTRATION -> {
                return dataSource.insertInsurance(Integer.parseInt(allValues[0]), allValues[1],
                        Date.valueOf(allValues[2]), allValues[3], Date.valueOf(allValues[4]), allValues[5]);
            }

            case TICKET_REGISTRATION -> {
                return dataSource.insertTicket(Integer.parseInt(allValues[0]), allValues[1],
                        Date.valueOf(allValues[2]), allValues[3], Double.parseDouble(allValues[4]), Date.valueOf(allValues[5]));
            }

            case CUSTOMER_DEACTIVATION -> {
                return dataSource.deactivateCustomer(nif);
            }

            case VEHICLE_DEACTIVATION -> {
                return dataSource.deactivateVehicle(allValues[0]);
            }

            case INSURANCE_DEACTIVATION -> {
                return dataSource.deactivateInsurance(Integer.parseInt(allValues[0]));
            }

            case TICKET_DEACTIVATION -> {
                return dataSource.deactivateTicket(Integer.parseInt(allValues[0]));
            }

            case EMPLOYEE_DEACTIVATION -> {
                return dataSource.deactivateEmployee(nif);
            }

            case CUSTOMER_UPDATE_ADDRESS -> {
                return dataSource.updatePersonAddress(nif, allValues[0]);
            }

            case CUSTOMER_UPDATE_EMAIL -> {
                return dataSource.updatePersonEmail(nif, allValues[0]);
            }

            case CUSTOMER_UPDATE_PASSWORD -> {
                return dataSource.updatePersonPassword(nif, allValues[0]);
            }

            case VEHICLE_UPDATE_COLOR -> {
                return dataSource.updateVehicleColor(allValues[0], allValues[1]);
            }

            case TICKET_UPDATE_PAY -> {
                return dataSource.payTicket(Integer.parseInt(allValues[0]), Double.parseDouble(allValues[1]));
            }

            case EMPLOYEE_UPDATE_ACCESS_LEVEL -> {
                return dataSource.updateEmployeeAccessLevel(nif, Integer.parseInt(allValues[0]));
            }
        }
        return false;
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

    public String showTask() {
        String s = """
                Task ID: %d
                    Type: %s
                    Status: %s
                    Date: %s            
                """;
        return String.format(s, taskID, TaskType.getDescription(taskType), taskStatus, taskDate);
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

    public Date getTaskDate() {
        return Date.valueOf(taskDate);
    }

    public String getTaskInfo() {
        return values;
    }
}
