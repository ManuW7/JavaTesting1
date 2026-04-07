public class SinCalc {

    public static double sin(double x, double eps) {
        // Добавили после ошибки на больших значениях
        x = x % (2 * Math.PI);
        if (x > Math.PI) {
            x -= 2 * Math.PI;
        } else if (x < -Math.PI) {
            x += 2 * Math.PI;
        }

        double term = x;
        double sum = x;
        int n = 1;

        while (Math.abs(term) > eps) {
            term *= -1 * x * x / ((2 * n) * (2 * n + 1));
            sum += term;
            n++;
        }

        return sum;
    }
}