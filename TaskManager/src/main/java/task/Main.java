package task;

import task.taskManager.Menu;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        /**Инициализация меню**/
        Menu menu = new Menu();
        //запускаем меню
        menu.start();
    }
}
