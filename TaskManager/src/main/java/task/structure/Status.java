package task.structure;

import java.util.HashMap;
import java.util.Map;

/**
 * Перечисления, хранящее множество значений статуса
 */
public enum Status {
    New("Новая"),
    Process("Принята к исполнению"),
    Reassigned("Переназначена"),
    Cancelled("Отменена"),
    Completed("Выполнена");

    /**
     * описание статуса
     */
    private String title;

    /**
     * конструктор перечисления
     *
     * @param title
     */
    Status(String title) {
        this.title = title;
    }

    /**
     * getStatuses создает список статусов и возвращает его
     *
     * @return
     */
    public static Map<Integer, Status> getStatuses() {
        Map<Integer, Status> statuses = new HashMap();
        statuses.put(1, New);
        statuses.put(2, Process);
        statuses.put(3, Reassigned);
        statuses.put(4, Cancelled);
        statuses.put(5, Completed);
        return statuses;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Status{" +
                "статус = '" + title + '\'' +
                '}';
    }
}
