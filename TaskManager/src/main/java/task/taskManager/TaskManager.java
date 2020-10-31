package task.taskManager;

import task.structure.Employee;
import task.structure.Status;
import task.structure.Task;

import java.io.*;
import java.util.*;

/**
 * Класс менеджер задач, служит для управления системой ведения задач
 */
public class TaskManager {
    /**
     * номер сотрудника в map
     */
    private int idEmpoyee = 0;
    /**
     * вспомогательная переменная для инкремента
     */
    private int idTask = 0;
    /**
     * Номер задачи в map и id задачи
     */
    private int id = 0;
    /**
     * файл, в котором хранятся сохраненные задачи
     */
    private File file = new File("tasks/tasks.txt");
    /**
     * открывается поток записи байтов в файл
     */
    private FileOutputStream outputStream = new FileOutputStream(file);
    /**
     * для сериализации объектов в поток
     */
    private ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
    /**
     * список задач
     */
    private Map<Integer, Task> tasks = new HashMap<Integer, Task>();
    /**
     * список сотрудников
     */
    private Map<Integer, Employee> employees = new HashMap();
    /**
     * сканнер
     */
    private Scanner scanner = new Scanner(System.in);

    public TaskManager() throws IOException {
    }

    /**
     * newTask создает новую задачу
     *
     * @param name - наименование задачи
     * @param desc - описание задачи
     */
    public void newTask(String name, String desc) {
        // если список сотруников не пуст, то предлагаем пользователю выбрать сотрудника для новой задачи из списка
        if (!employees.isEmpty()) {
            //вывод списка сотрудников
            getSoutEmployees();
            //выбор сотрудника(номер)
            int chooseEmployee = Menu.checkIntInput();
            //id задачи
            id = ++idTask;
            //создаем новую задачу с данным id и записываем ее в наш список всех задач
            tasks.put(id, new Task(id, name, desc, employees.get(chooseEmployee), Status.New));
        } else {
            //если ни одного сотрудника не было создано, то создаем
            System.out.println(Titles.strEmpEmpty.getDesc());
            id = ++idTask;
            String nameSec = scanner.nextLine();
            System.out.println("Введите фамилию:");
            String nameLastSec = scanner.nextLine();
            //добавляем нового сотрудника и добавляем в список всех сотрудников
            employees.put(++idEmpoyee, new Employee(nameSec, nameLastSec));
            //создаем задачу с данным сотрудников и добавляем в список всех задач
            tasks.put(id, new Task(id, name, desc, employees.get(idEmpoyee), Status.New));
        }
    }

    /**
     * changeTaskTitle изменяет наименование, выбранной задачи
     *
     * @param numOfChangeTask - задача, выбранная пользователем
     * @param title           - наименование задачи
     */
    public void changeTaskTitle(int numOfChangeTask, String title) {
        tasks.get(numOfChangeTask).setTitle(title);
    }

    /**
     * changeTaskDesc изменяет описание, выбранной задачи
     *
     * @param numOfChangeTask - задача, выбранная пользователем
     * @param desc            - описание задачи
     */
    public void changeTaskDesc(int numOfChangeTask, String desc) {
        tasks.get(numOfChangeTask).setDescription(desc);
    }

    /**
     * changeTaskNewEmployee переназначает сотрудника к определенной задаче
     *
     * @param numOfChangeTask - задача, выбранная пользователем
     * @param numOfEmployee   - новый (выбранный) сотрудник
     */
    public void changeTaskNewEmployee(int numOfChangeTask, int numOfEmployee) {
        tasks.get(numOfChangeTask).setEmployee(employees.get(numOfEmployee));
    }

    /**
     * changeTaskDeleteEmployee снимает сотрудника с данной задачи
     *
     * @param numOfChangeTask - задача, выбранная пользователем
     */
    public void changeTaskDeleteEmployee(int numOfChangeTask) {
        tasks.get(numOfChangeTask).setEmployee(null);
    }

    /**
     * changeTaskStatus изменяет статус задачи
     *
     * @param numOfChangeTask - задача, выбранная пользователем
     * @param status          - статус
     */
    public void changeTaskStatus(int numOfChangeTask, int status) {
        tasks.get(numOfChangeTask).setStatus(Status.getStatuses().get(status));
    }

    /**
     * newEmployee создает нового сотрудника
     *
     * @param name     - имя
     * @param lastName - фамилия
     */
    public void newEmployee(String name, String lastName) {
        employees.put(++idEmpoyee, new Employee(name, lastName));
    }

    /**
     * changeNameOfEmployee изменяет мя сотрудника
     *
     * @param chooseChangeEmployee - сотрудник, выбранный пользователем
     * @param name                 - имя
     */
    public void changeNameOfEmployee(int chooseChangeEmployee, String name) {
        employees.get(chooseChangeEmployee).setName(name);
    }

    /**
     * changeLastNameOfEmployee изменяет фамилию сотрудника
     *
     * @param chooseChangeEmployee - сотрудник, выбранный пользователем
     * @param lastName             - фамилия
     */
    public void changeLastNameOfEmployee(int chooseChangeEmployee, String lastName) {
        employees.get(chooseChangeEmployee).setLastName(lastName);
    }

    /**
     * deleteEmployee удаляет сотрудника
     *
     * @param chooseChangeEmployee - сотрудник, выбранный пользователем
     */
    public void deleteEmployee(int chooseChangeEmployee) {
        employees.remove(chooseChangeEmployee);
    }

    /**
     * deleteTask удаляет задачу
     *
     * @param chooseDeleteTask - задача, выбранная пользователем
     */
    public void deleteTask(int chooseDeleteTask) {
        tasks.remove(chooseDeleteTask);
    }

    /**
     * saveTask сохраняет, выбранную задачу
     *
     * @param chooseSaveTask - задача, выбранная пользователем
     * @throws IOException
     */
    public void saveTask(int chooseSaveTask) throws IOException {
        objectOutputStream.writeObject(tasks.get(chooseSaveTask));
    }

    /**
     * closeThread() закрывает поток записи в файл
     *
     * @throws IOException
     */
    public void closeThread() throws IOException {
        outputStream.close();
        objectOutputStream.close();
    }

    /**
     * восстанавливает все задачи
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void recoverTask() {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            while (true) {
                try {
                    Task savedTask = (Task) objectInputStream.readObject();
                    tasks.put(savedTask.getId(), savedTask);
                } catch (EOFException e) {
                    fileInputStream.close();
                    objectInputStream.close();
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл отсуствует");
        } catch (EOFException e) {
            System.out.println("Файл пуст");
        } catch (StreamCorruptedException e) {
            System.out.println("Невозможно считать данные, проверьте файл");
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * getSoutEmployees выводит список всех сотрудников
     */
    public void getSoutEmployees() {
        Set<Integer> keys = employees.keySet();
        for (int set : keys) {
            System.out.println(set + ". " + employees.get(set));
        }
    }

    /**
     * getSoutTasks выводит список всех задач
     */
    public void getSoutTasks() {
        Set<Integer> keys = tasks.keySet();
        for (int set : keys) {
            System.out.println(set + ". " + tasks.get(set));
        }
    }

    public void getStatus() {
        Set<Integer> keys = Status.getStatuses().keySet();
        for (int status : keys) {
            System.out.println(status + ". " + Status.getStatuses().get(status).getTitle());
        }
    }

    public Map<Integer, Task> getTasks() {
        return tasks;
    }

    public Map<Integer, Employee> getEmployees() {
        return employees;
    }
}
