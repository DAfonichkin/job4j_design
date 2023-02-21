package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class JSONReportTest {
    @Test
    void whenJSONReportGenerate() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Petr", now, now, 200);
        String date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(now.getTime());
        store.add(worker);
        store.add(worker2);
        Report jsonReport = new JSONReport(store);
        String result = jsonReport.generate((e) -> true);
        String expected = "[{\"name\":\"Ivan\","
                + "\"hired\":\"%s\","
                + "\"fired\":\"%s\","
                + "\"salary\":100.0},"
                + "{\"name\":\"Petr\","
                + "\"hired\":\"%s\","
                + "\"fired\":\"%s\","
                + "\"salary\":200.0}]";
        expected = expected.formatted(date, date, date, date);
        assertThat(result).isEqualTo(expected);
    }

}