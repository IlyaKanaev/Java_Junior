import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Program {
    public static void main(String[] args) {
        List<ToDo> tasks;
        File f = new File(ToDoListApp.FILE_JSON);
        if (f.exists() && !f.isDirectory())
            tasks = ToDoListApp.loadTasksFromFile(ToDoListApp.FILE_JSON);
        else
            tasks = prepareTasks();
        ToDoListApp.saveTasksToFile(ToDoListApp.FILE_JSON, tasks);
        ToDoListApp.saveTasksToFile(ToDoListApp.FILE_BIN, tasks);
        ToDoListApp.saveTasksToFile(ToDoListApp.FILE_XML, tasks);

        ToDoListApp.displayTasks(tasks);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Выберите действие: ");
            System.out.println("1. Добавить новую задачу");
            System.out.println("2. Отметить задачу как выполненную");
            System.out.println("3. Выйти");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    ToDoListApp.addNewTask(scanner, tasks);
                    break;
                case "2":
                    ToDoListApp.markTasksAsDone(scanner, tasks);
                    break;
                case "3":
                    ToDoListApp.saveTasksToFile(ToDoListApp.FILE_JSON, tasks);
                    ToDoListApp.saveTasksToFile(ToDoListApp.FILE_BIN, tasks);
                    ToDoListApp.saveTasksToFile(ToDoListApp.FILE_XML, tasks);
                    System.out.println("Список задач сохранен.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Некорректный выбор. Попробуйте снова");
                    break;
            }
            ToDoListApp.displayTasks(tasks);
        }
    }

    static List<ToDo> prepareTasks() {
        ArrayList<ToDo> list = new ArrayList<>();
        list.add(new ToDo("Сходить в магазин за продуктами"));
        list.add(new ToDo("Погулять с собакой"));
        list.add(new ToDo("Заменить лампочку"));
        return list;
    }
}
