package ru.job4j.ood.isp.menu;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.*;

class SimpleMenuPrinterTest {
    public static final ActionDelegate STUB_ACTION = System.out::println;

    @Test
    void whenPrint() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        MenuPrinter printer = new SimpleMenuPrinter();
        String expected = String.join(System.lineSeparator(),
                "1.Сходить в магазин",
                "----1.1.Купить продукты",
                "--------1.1.1.Купить хлеб",
                "--------1.1.2.Купить молоко",
                "2.Покормить собаку"
                        + System.lineSeparator());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        printer.print(menu);
        String rsl = outputStream.toString();
        System.setOut(System.out);
        assertThat(expected).isEqualTo(rsl);
    }

    @Test
    void whenPrintEmpty() {
        Menu menu = new SimpleMenu();
        MenuPrinter printer = new SimpleMenuPrinter();
        String expected = "";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);
        printer.print(menu);
        String rsl = outputStream.toString();
        System.setOut(System.out);
        assertThat(expected).isEqualTo(rsl);
    }
}