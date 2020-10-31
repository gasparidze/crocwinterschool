package task.structure;

import java.io.Serializable;

/**
 * Класс сотрудника
 */
public class Employee implements Serializable {
    /**
     * имя сотрудника
     */
    private String name;
    /**
     * фамилия сотрудника
     */
    private String lastName;

    /**
     * конструктор класса
     *
     * @param name     - имя сотрудника
     * @param lastName - фамилия сотрудника
     */
    public Employee(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Employee" +
                "{Имя=" + name + "," +
                " Фамилия=" + lastName + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee) || o == null) return false;
        Employee newEmployee = (Employee) o;
        return (newEmployee.name == this.name) && (newEmployee.lastName == this.lastName);
    }

}
