package task.structure;

import java.io.Serializable;

/**
 * Класс задачи
 */
public class Task implements Serializable {
    /**
     * id задачи
     */
    private final int id;
    /**
     * наименование задачи
     */
    private String title;
    /**
     * описание задачи
     */
    private String description;
    /**
     * сотрудник, назначенный на данную задачу
     */
    private Employee employee;
    /**
     * статус задачи
     */
    private Status status;

    /**
     * конструктор задачи
     *
     * @param id          - id задачи
     * @param title       - наименование задачи
     * @param description - описание задачи
     * @param employee    - сотрудник, назначенный на данную задачу
     * @param status      - статус задачи
     */
    public Task(int id, String title, String description, Employee employee, Status status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.employee = employee;
        this.status = status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Task" +
                "{id = " + id +
                ", Наименование = '" + title + "'," +
                " Описание = '" + description + "'," +
                " Сотрудник = " + employee + ',' +
                " Статус = " + status.getTitle() +
                '}';
    }
}
