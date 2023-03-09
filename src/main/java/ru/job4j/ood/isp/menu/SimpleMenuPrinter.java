package ru.job4j.ood.isp.menu;

import java.util.Iterator;

public class SimpleMenuPrinter implements MenuPrinter {
    @Override
    public void print(Menu menu) {
        for (Menu.MenuItemInfo itemInfo : menu) {
            String indent = "----".repeat(Math.max(0, itemInfo.getNumber().split("\\.").length - 1));
            System.out.println(indent + itemInfo.getNumber() + itemInfo.getName());
        }
    }
}
