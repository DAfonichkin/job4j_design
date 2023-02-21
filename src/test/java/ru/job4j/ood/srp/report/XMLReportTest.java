package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class XMLReportTest {

    @Test
    void whenXMLReportGenerate() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Petr", now, now, 200);
        String date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(now.getTime());
        store.add(worker);
        store.add(worker2);
        Report xmlReport = new XMLReport(store);
        String result = xmlReport.generate((e) -> true);
        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                + "<employees>\n"
                + "    <employeeList>\n"
                + "        <fired>%s</fired>\n"
                + "        <hired>%s</hired>\n"
                + "        <name>Ivan</name>\n"
                + "        <salary>100.0</salary>\n"
                + "    </employeeList>\n"
                + "    <employeeList>\n"
                + "        <fired>%s</fired>\n"
                + "        <hired>%s</hired>\n"
                + "        <name>Petr</name>\n"
                + "        <salary>200.0</salary>\n"
                + "    </employeeList>\n"
                + "</employees>\n";
        expected = expected.formatted(date, date, date, date);
        assertThat(result).isEqualTo(expected);
    }
}