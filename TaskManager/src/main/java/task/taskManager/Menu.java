package task.taskManager;

import java.io.IOException;
import java.util.*;

/**
 * Класс меню
 */
public class Menu {
    /**
     * менеджер задач
     */
    private TaskManager taskManager;
    /**
     * сканнер
     */
    private final Scanner scanner = new Scanner(System.in);
    /**
     * флаг выхода из меню
     */
    private boolean flag = false;

    /**
     * конструктор класса, инициализация менджера задач
     */
    public Menu() throws IOException {
        taskManager = new TaskManager();
    }

    /**
     * start запускат меню
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void start() throws IOException, ClassNotFoundException {
        do {
            System.out.println(Titles.strMenu.getDesc() + "\n" + Titles.strChoose.getDesc());

            switch (checkIntInput()) {
                case 1: {
                    System.out.println("Введите наименование задачи:");
                    String name = scanner.nextLine();
                    System.out.println("Введите описание задачи:");
                    String desc = scanner.nextLine();
                    System.out.println("Выберите исполнителя к задаче:");
                    taskManager.newTask(name, desc);
                    continue;
                }
                case 2: {
                    if (!taskManager.getTasks().isEmpty()) {
                        taskManager.getSoutTasks();
                        System.out.println("Введите задачу, которую нужно изменить:");
                        int numOfChangeTask = checkIntInput();
                        //выводится список изменений, то есть что именно в задаче нужно поменять
                        System.out.println(Titles.strChange.getDesc());
                        //выбираем нужное изменение
                        int numOfChangeVariant = checkIntInput();
                        switch (numOfChangeVariant) {
                            case 1: {
                                System.out.println("Введите новое наименование:");
                                String title = scanner.nextLine();
                                taskManager.changeTaskTitle(numOfChangeTask, title);
                                break;
                            }
                            case 2: {
                                System.out.println("Введите новое описание:");
                                String desc = scanner.nextLine();
                                taskManager.changeTaskDesc(numOfChangeTask, desc);
                                break;
                            }
                            //изменение сотрудника
                            case 3: {
                                if (!taskManager.getEmployees().isEmpty()) {
                                    System.out.println("Выберите сотрудника из списка:");
                                    taskManager.getSoutEmployees();
                                    int numOfEmployee = checkIntInput();
                                    System.out.println(Titles.strChangeEmp.getDesc());
                                    //что именно менять: назначить нового сотрудника или снять старого?
                                    int createNewOrDeleteOld = checkIntInput();
                                    switch (createNewOrDeleteOld) {
                                        case 1: {
                                            taskManager.changeTaskNewEmployee(numOfChangeTask, numOfEmployee);
                                            break;
                                        }
                                        case 2: {
                                            taskManager.changeTaskDeleteEmployee(numOfChangeTask);
                                            break;
                                        }
                                    }
                                } else {
                                    System.out.println("Список сотрудников пуст");
                                }
                                break;
                            }
                            case 4: {
                                System.out.println("Введите новый статус из списка:");
                                taskManager.getStatus();
                                int status = checkIntInput();
                                taskManager.changeTaskStatus(numOfChangeTask, status);
                                break;
                            }
                        }
                    } else {
                        System.out.println("Список задач пуст");
                    }
                    continue;
                }
                case 3: {
                    if (!taskManager.getTasks().isEmpty()) {
                        taskManager.getSoutTasks();
                    } else {
                        System.out.println("Список задач пуст");
                    }
                    continue;
                }
                case 4: {
                    if (!taskManager.getTasks().isEmpty()) {
                        System.out.println("Выберите задачу, которую следует cохранить:");
                        taskManager.getSoutTasks();
                        int chooseSaveTask = checkIntInput();
                        taskManager.saveTask(chooseSaveTask);
                        System.out.println("Задача успешно сохранена");
                    } else {
                        System.out.println("Список задач пуст");
                    }
                    continue;
                }
                case 5: {
                    if (!taskManager.getTasks().isEmpty()) {
                        System.out.println("Выберите задачу, которую следует удалить:");
                        taskManager.getSoutTasks();
                        int chooseDeleteTask = checkIntInput();
                        taskManager.deleteTask(chooseDeleteTask);
                        System.out.println("Задача успешно удалена");
                    } else {
                        System.out.println("Список задач пуст");
                    }
                    continue;
                }
                case 6: {
                    taskManager.recoverTask();
                    System.out.println("Задачи успешно восстановлены");
                    continue;
                }
                case 7: {
                    System.out.println("Введите имя сотрудника:");
                    String name = scanner.nextLine();
                    System.out.println("Введите фамилию сотрудника:");
                    String lastName = scanner.nextLine();
                    taskManager.newEmployee(name, lastName);
                    continue;
                }
                //изменение сотрудника
                case 8: {
                    if (!taskManager.getEmployees().isEmpty()) {
                        System.out.println("Выберите сотрудника:");
                        taskManager.getSoutEmployees();
                        int chooseChangeEmployee = checkIntInput();
                        System.out.println("Изменить:\n1. Имя\n2. Фамилию");
                        switch (checkIntInput()) {
                            case 1: {
                                System.out.println("Введите имя:");
                                String name = scanner.nextLine();
                                taskManager.changeNameOfEmployee(chooseChangeEmployee, name);
                                break;
                            }
                            case 2: {
                                System.out.println("Введите фамилию:");
                                String lastName = scanner.nextLine();
                                taskManager.changeLastNameOfEmployee(chooseChangeEmployee, lastName);
                                break;
                            }
                        }
                    } else {
                        System.out.println("Список сотрудников пуст");
                    }
                    continue;
                }
                case 9: {
                    if (!taskManager.getEmployees().isEmpty()) {
                        taskManager.getSoutEmployees();
                    } else {
                        System.out.println("Список сотрудников пуст");
                    }
                    continue;
                }
                case 10: {
                    if (!taskManager.getEmployees().isEmpty()) {
                        System.out.println("Выберите сотрудника, которого следует удалить:");
                        taskManager.getSoutEmployees();
                        int chooseChangeEmployee = checkIntInput();
                        taskManager.deleteEmployee(chooseChangeEmployee);
                    } else {
                        System.out.println("Список сотрудников пуст");
                    }
                    continue;
                }
                case 0: {
                    scanner.close();
                    taskManager.closeThread();
                    flag = true;
                    break;
                }
            }
        } while (!flag);
    }

    /**
     * checkIntInput - статические метод, проверяющий ввода целого значения
     *
     * @return
     */
    public static int checkIntInput() {
        int read;
        Scanner sca = new Scanner(System.in);
        while (!sca.hasNextInt()) {
            System.out.println(Titles.strChoose.getDesc());
            sca.next();
        }
        read = sca.nextInt();
        return read;
    }

}
