/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package closest.pair;

import java.util.Random;

/* Materia: Algoritmo y Complejidad
 * Curso: 3265
 * Nombre: Esteban Quintero Gonzalez
 * Codigo Estudiantil: 200158282
 * Workshop Closest Pair
 * 07-09-2022
 * Realizar un algoritmo que mida la menor distancia de un grupo de puntos
 */
public class ClosestPair {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int n; // Numero de puntos
        int inp; // Numero de inputs
        inp = 0;
        long time, time2, totalt; // Tiempo de ejecución
        int comparisons; // Numero de comparaciones
        for (n = 11; n < 3000; n = n + 10) {
            inp++;
            comparisons = 0;
            time = 0;
            time2 = 0;
            totalt = 0;
            int[][] coord = new int[n][2]; // matriz principal con n+1 puntos
            Random rand = new Random();
            double mindist;
            mindist = Double.POSITIVE_INFINITY;
            for (int i = 0; i < n; ++i) {
                coord[i][0] = rand.nextInt(n) + rand.nextInt(n); // para mas variabilidad en los numeros toma valores de 0 a 2n
                coord[i][1] = rand.nextInt(4);
            }
            Sort(coord, n);
            // toma la mitad de los n puntos
            int mid = (n - 1) / 2;
            // distancia de el punto medio
            int dis;
            dis = coord[mid][0];
            // imprime las coordenadas en orden respecto a x
            /*for (int i = 0; i < n; i++) {
                System.out.println(coord[i][0] + " " + coord[i][1]);
            }*/
            time = System.nanoTime();
            double dis1 = Bruteforce(coord, 0, mid, comparisons);
            double dis2 = Bruteforce(coord, mid, n, comparisons);
            time = System.nanoTime() - time;
            // valor a sumar y restar al punto medio
            double add;
            //Toma la distancia mas corta
            if (dis1 < dis2) {
                add = dis1;
            } else {
                add = dis2;
            }
            boolean found;
            found = false;
            //Le resta la distancia mas corta al punto medio
            int liminf = 0;
            for (int i = 0; found != true; i++) {
                if (coord[i][0] >= (dis - add)) {
                    liminf = i;
                    found = true;
                }
            }
            found = false;
            //Le suma la distancia mas corta al punto medio
            int limsup = 0;
            for (int i = 0; found != true; i++) {
                if (coord[i][0] > (dis + add)) {
                    found = true;
                    limsup = i;
                }
            }
            time2 = System.nanoTime();
            double dis3 = Bruteforce(coord, liminf, limsup, comparisons);
            time2 = System.nanoTime() - time2;
            totalt = time + time2;
            if (add < dis3) {
                // System.out.println("La distancia minima es: " + add);
            } else {
                // System.out.println("La distancia minima es: " + dis3);
            }
            System.out.println(inp + " " + comparisons + " " + totalt);
            // Algoritmo de fuerza bruta
            for (int i = 0; i < n - 1; ++i) {
                int x1 = coord[i][0];
                int y1 = coord[i][1];
                for (int j = i + 1; j < n; ++j) {
                    int x2 = coord[j][0];
                    int y2 = coord[j][1];
                    double dist = ((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1));
                    if (mindist > dist) {
                        mindist = dist;
                    }
                }
            }
            mindist = Math.sqrt(mindist);
            // System.out.println("La distancia minima es: " + mindist);
        }
    }

    // Metodo de ordenamiento
    public static void Sort(int[][] coord, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (coord[j][0] > coord[j + 1][0]) {
                    Integer tempx = coord[j][0];
                    Integer tempy = coord[j][1];
                    coord[j][0] = coord[j + 1][0];
                    coord[j][1] = coord[j + 1][1];
                    coord[j + 1][0] = tempx;
                    coord[j + 1][1] = tempy;
                }
            }
        }
    }

    // 
    public static double Bruteforce(int[][] coord, int x, int n, int comp) {
        double mindist;
        mindist = Double.POSITIVE_INFINITY;
        for (int i = x; i < n; i++) {
            comp = comp + 1;
            int x1 = coord[i][0];
            int y1 = coord[i][1];
            for (int j = i + 1; j < n; ++j) {
                comp = comp + 1;
                int x2 = coord[j][0];
                int y2 = coord[j][1];
                double dist = ((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1));
                if (mindist > dist) {
                    comp = comp + 1;
                    mindist = dist;
                }
            }
        }
        mindist = Math.sqrt(mindist);
        return mindist;
    }
}
