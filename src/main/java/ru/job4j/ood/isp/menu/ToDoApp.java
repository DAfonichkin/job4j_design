package ru.job4j.ood.isp.menu;

import java.time.LocalTime;
import java.util.*;

public class ToDoApp {

    private final Menu menu = new SimpleMenu();
    public static final ActionDelegate STUB_ACTION = System.out::println;
    public static final int ADD_TO_ROOT = 1;
    public static final int ADD_TO_PARENT = 2;
    public static final int CALL_ACTION = 3;
    public static final int PRINT = 4;
    public static final String NAME = "    Введите имя элемента";
    public static final String PARENT_NAME = "    Введите имя родительского элемента";
    public static final String MENU = """
                Введите 1, для добавления в корень меню.
                Введите 2, для добавления к родительскому элементу.
                Введите 3, для вызова действия, привязанного к пункту меню.
                Введите 4, для вывода меню в консоль.
                Введите любое другое число для выхода.
            """;

    public void run() {
        boolean run = true;
        Scanner scanner = new Scanner(System.in);
        while (run) {
            System.out.println(MENU);
            int userChoice = Integer.parseInt(scanner.nextLine());
            System.out.println(userChoice);
            if (ADD_TO_ROOT == userChoice) {
                System.out.println(NAME);
                menu.add(Menu.ROOT, scanner.nextLine(), STUB_ACTION);
            } else if (ADD_TO_PARENT == userChoice) {
                System.out.println(NAME);
                String name = scanner.nextLine();
                System.out.println(PARENT_NAME);
                String parentName = scanner.nextLine();
                if (!menu.add(parentName, name, STUB_ACTION)) {
                    System.out.println("Не удалось добавить элемент");
                }
            } else if (CALL_ACTION == userChoice) {
                System.out.println(NAME);
                String name = scanner.nextLine();
                Optional<Menu.MenuItemInfo> selected = menu.select(name);
                if (selected.isEmpty()) {
                    System.out.println("Не найден элемент " + name);
                    continue;
                }
                selected.get().getActionDelegate().delegate();
            } else if (PRINT == userChoice) {
                MenuPrinter simplePrinter = new SimpleMenuPrinter();
                simplePrinter.print(menu);
            } else {
                run = false;
                System.out.println("Конец работы");
            }
        }
    }

    public static void main(String[] args) {
        ToDoApp app = new ToDoApp();
        app.run();
    }
}
