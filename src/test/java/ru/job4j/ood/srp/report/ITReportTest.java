package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class ITReportTest {

    @Test
    public void whenITDepartmentGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        String del = ";";
        Report engine = new ITReport(store, parser, del);
        StringBuilder expect = new StringBuilder()
                .append("Name").append(del)
                .append("Hired").append(del)
                .append("Fired").append(del)
                .append("Salary").append(del)
                .append(System.lineSeparator())
                .append(worker.getName()).append(del)
                .append(parser.parse(worker.getHired())).append(del)
                .append(parser.parse(worker.getFired())).append(del)
                .append(worker.getSalary())
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

}