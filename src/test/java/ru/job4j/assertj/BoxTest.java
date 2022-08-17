package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisUnknown() {
        Box box = new Box(7, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object");
    }

    @Test
    void whenVertexIs6ThenNumberOfVertices6() {
        Box box = new Box(6, 10);
        int vertices = box.getNumberOfVertices();
        assertThat(vertices).isEqualTo(6);
    }

    @Test
    void whenVertexIs7ThenNumberOfVerticesIsNegative() {
        Box box = new Box(7, 10);
        int vertices = box.getNumberOfVertices();
        assertThat(vertices).isNegative();
    }

    @Test
    void isThisExist() {
        Box box = new Box(6, 10);
        boolean isExist = box.isExist();
        assertThat(isExist).isTrue();
    }

    @Test
    void isThisNotExist() {
        Box box = new Box(7, 10);
        boolean isExist = box.isExist();
        assertThat(isExist).isFalse();
    }

    @Test
    void whenVertexIs7ThenAreaIs0() {
        Box box = new Box(7, 10);
        double area = box.getArea();
        assertThat(area).isEqualTo(0, withPrecision(0.001d));
    }

    @Test
    void whenVertexIs0ThenAreaIs1256() {
        Box box = new Box(0, 10);
        double area = box.getArea();
        assertThat(area).isCloseTo(1256.63d, withPrecision(0.01d));
    }

}