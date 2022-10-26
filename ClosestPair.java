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
 * Realizar un algoritmo que cuente la cantidad de duplicados que hay en un texto
 */
public class ClosestPair {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int n;
        n = 21; // Numero de puntos
        int[][] coord = new int[n][2]; // matriz principal con
        Random rand = new Random();
        double save = 99999;
        for (int i = 0; i < n; ++i) {
            coord[i][0] = Math.abs((100) + rand.nextInt(100));
            coord[i][1] = Math.abs((50) + rand.nextInt(50));
        }
        // Metodo de ordenamiento
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
        // toma la mitad de los n puntos
        int mid = (n - 1) / 2;
        // distancia de el punto medio
        int dis;
        dis = coord[mid][0];
        // imprime las coordenadas en orden respecto a x
        for (int i = 0; i < n; i++) {
            System.out.println(coord[i][0] + " " + coord[i][1]);
        }
        double dis1 = Bruteforce(coord, 0, mid);
        double dis2 = Bruteforce(coord, mid, n);
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
        System.out.println(dis1);
        System.out.println(dis2);
        double dis3 = Bruteforce(coord, liminf, limsup);
        System.out.println(dis3);
        if (add < dis3){
            System.out.println("La distancia minima es: "+add);
        } else {
            System.out.println("La distancia minima es: "+dis3);
        }
        /*coord[0][0] = 10;
        coord[0][1] = 5;
        coord[1][0] = 13;
        coord[1][1] = 9;
        coord[2][0] = 15;
        coord[2][1] = 5;
        coord[3][0] = 16;
        coord[3][1] = 7;
        coord[4][0] = 18;
        coord[4][1] = 10;
        coord[5][0] = 22;
        coord[5][1] = 7;
         */
        // Algoritmo de fuerza bruta
        for (int i = 0; i < n - 1; ++i) {
            int x1 = coord[i][0];
            int y1 = coord[i][1];
            for (int j = i + 1; j < n; ++j) {
                int x2 = coord[j][0];
                int y2 = coord[j][1];
                double dist = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
                if (save > dist) {
                    save = dist;
                }
            }
        }

        System.out.println("La distancia minima es: " + save);
    }

    public static double Bruteforce(int[][] coord, int x, int n) {
        double save = 9999;
        for (int i = x; i < n; i++) {
            int x1 = coord[i][0];
            int y1 = coord[i][1];
            for (int j = i + 1; j < n; ++j) {
                int x2 = coord[j][0];
                int y2 = coord[j][1];
                double dist = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
                if (save > dist) {
                    save = dist;
                }
            }
        }
        return save;
    }

}
