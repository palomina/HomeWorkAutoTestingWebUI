package triangle;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TriangleTest {

    private static Logger logger;

    @BeforeAll
    public static void setUp() {
        logger = Logger.getLogger(TriangleTest.class);
    }

    @DisplayName("Провекра расчета площади")
//    @ParameterizedTest
    @CsvSource({"6,3,4,5", "12,6,5,5", "12,8,5,5"})
    public void squareTest(String s, String a, String b, String c) {
        logger.info("Data assignment (Задание данных)");
        double s1 = Double.parseDouble(s);
        double a1 = Double.parseDouble(a);
        double b1 = Double.parseDouble(b);
        double c1 = Double.parseDouble(c);

        logger.info("Checking the calculation of the area of a triangle (Проверка расчета площади треугольника)");
        Assertions.assertEquals(s1, Triangle.square(a1, b1, c1));
    }

//    @Test
    @DisplayName("Проверка появления ошибки при задании отрицательных значений для сторон треугольника")
    public void squareExceptionNegativeSideTest() {
        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> Triangle.square(-3, 4, 5));
        logger.info("Checking for the correct message (Проверка появления правильного сообщения)");
        Assertions.assertEquals("Неверно заданы стороны треугольника!", exception.getMessage());
    }

//    @Test
    @DisplayName("Проверка появления ошибки для несуществующего треугольника")
    public void squareExceptionTriangleExistTest() {
        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> Triangle.square(6, 1, 1));
        logger.info("Checking for the correct message (Проверка появления правильного сообщения)");
        Assertions.assertEquals("Треугольник с такими сторонами не существует!", exception.getMessage());
    }

}
