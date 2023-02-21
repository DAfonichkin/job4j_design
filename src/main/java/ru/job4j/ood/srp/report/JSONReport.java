package ru.job4j.ood.srp.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.ood.srp.formatter.CalendarSerializer;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.model.Employees;
import ru.job4j.ood.srp.store.Store;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class JSONReport implements Report {

    private final Store store;
    private final Gson lib;

    public JSONReport(Store store) {
        this.store = store;
        this.lib = new GsonBuilder().registerTypeHierarchyAdapter(Calendar.class, new CalendarSerializer()).create();
    }
    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> employees = store.findBy(filter);
        return lib.toJson(employees);
    }


}
