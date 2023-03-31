import java.util.Scanner;

public class Start {
    public static void main(String[] args) {
        SimpsonMethod simpsonMethod = new SimpsonMethod();
        System.out.println("""
                Выберите уравнение
                1)1/x
                2)x^2 - x + 1
                3)sin(x)/x
                4)cos(x)""");
        Scanner scanner = new Scanner(System.in);
        int variant = scanner.nextInt();
        System.out.println("Введите границы интегрирования");
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        simpsonMethod.setVariant(variant);
        try {
            Double answer = simpsonMethod.integrate(a, b);
            System.out.println(answer);
        } catch (MyException e){
            System.out.println("Вы чаво? Мы не можем это интегрировать(\n" +
                    "Это разрыв второго рода...");
        }catch (MyExceptionODZ r){
            System.out.println("ОДЗ не соблюдается");
        }
    }
}
