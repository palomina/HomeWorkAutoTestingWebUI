package triangle;

public class Triangle {

    public static double square(double a, double b, double c) throws IllegalArgumentException {
        if (a <= 0 || b <= 0 || c <= 0) {
            throw new IllegalArgumentException("Неверно заданы стороны треугольника!");
        }

        if (a + b <= c || a + c <= b || b + c <= a) {
            throw new IllegalArgumentException("Треугольник с такими сторонами не существует!");
        }

        double p = (a + b + c) / 2.0;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }
}
