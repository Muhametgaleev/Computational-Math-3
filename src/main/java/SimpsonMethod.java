public class SimpsonMethod {
    int variant = 1;

    public void setVariant(int variant) {
        this.variant = variant;
    }

    public double integrate(double a, double b) throws MyException, MyExceptionODZ {
        int n = 1000000;
        int i,z;
        double h,s;
        double e = 0.0000001;
        n=2*n; // количество шагов
        s = f(a)*f(b); // итоговая сумма
        h = (b-a)/n;
        z = 4;

        // если начинаем интегрировать с точки разрыва, то s становится NaN и все ломает
        if(Double.isNaN(s)){
//            s = f(a+h-e)*f(b+h-e);
//            System.out.println("Разрыв устранен.\n" +
//                    "Взято среднее от значений от двух соседних точках разрыва в точке 0.");
            throw new MyExceptionODZ("ОДЗ");
        }

        if (variant == 1 & ((a<b & b<0) | (b>0 & a>b))){
            for(i = 1; i<n; i++){
                double perem = f(a+i*h);
                s = s + z * perem;
                z = 6 - z;
            }
            return (s * h)/3;
        }else if(variant != 1){
            for(i = 1; i<n; i++){
                double perem = f(a+i*h);
                if(variant == 3 & (perem == Double.POSITIVE_INFINITY | perem == Double.NEGATIVE_INFINITY | Double.isNaN(perem))){
                    if (a<=0 & b>=0){
                        System.out.println("Разрыв устранен.\n" +
                                "Взято среднее от значений от двух соседних точках разрыва в точке 0.");
                        double nextperem = f(a+i*h + e);
                        double privperem = f(a+i*h - e);
                        perem = (nextperem+privperem)/2;
                    }
                }
                s = s + z * perem;
                z = 6 - z;
            }
            return (s * h)/3;
        }else{
            throw new MyException("Вы чаво? Мы не можем это интегрировать(\n" +
                    "Это разрыв второго рода...");
        }
    }

    private double f(double x) {
        // здесь нужно указать функцию, которую мы интегрируем
        if(variant == 1){
            return 1/x;
        }else if(variant == 2){
            return x*x - x + 1;
        }else if(variant == 3){
            return Math.sin(x)/x;
        } else{
            return Math.cos(x);
        }
    }
}