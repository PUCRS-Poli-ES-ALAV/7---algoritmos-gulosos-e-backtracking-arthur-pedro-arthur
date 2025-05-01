import java.util.*;

enum moeda {
    cem, vinteecinco, dez, cinco, um
}

public class troco {
    static int its = 0;
    static List<moeda> trocoM(float valor) {
        var result = new ArrayList<moeda>();

        while (valor >= 0.01) {
            its++;
            if (valor >= 1) {
                result.add(moeda.cem);
                valor -= 1;
                continue;
            } else if (valor >= 0.25) {
                result.add(moeda.vinteecinco);
                valor -= 0.25;
                continue;
            } else if (valor >= 0.1) {
                result.add(moeda.dez);
                valor -= 0.1;
                continue;
            } else if (valor >= 0.05) {
                result.add(moeda.cinco);
                valor -= 0.05;
                continue;
            } else {
                result.add(moeda.um);
                valor -= 0.01;
                continue;
            }
        }

        return result;
    }

    static void printMoedas(List<moeda> l) {
        int m100 = 0, m25 = 0, m10 = 0, m5 = 0, m1 = 0;
        for (var m : l) {
            switch (m) {
                case cem:
                    m100++;
                    break;
                case vinteecinco:
                    m25++;
                    break;
                case dez:
                    m10++;
                    break;
                case cinco:
                    m5++;
                    break;
                case um:
                    m1++;
                    break;
            }
        }
        System.out.printf("%d de 100, %d de 25, %d de 10, %d de 5, %d de 1\n", m100, m25, m10, m5, m1);
    }

    public static void main(String[] args) {
        printMoedas(trocoM(2.89f));
        System.out.println(its);
    }
}