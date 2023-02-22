package ru.job4j.ood.lsp;

public class MemStore implements Store {

    @Override
    public void load() {

    }

    @Override
    public void save() {

    }

    @Override
    public void createTable() {
        throw new IllegalStateException("Нельзя создать таблицу при хранении в памяти");
    }
}
