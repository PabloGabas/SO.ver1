/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author metal
 */
public class Prioridad {
    
   public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.err.println("Este codigo tiene errores");

        System.out.print("Ingrese el numero de procesos a ordenar: ");
        int numRegistros = scanner.nextInt();
        scanner.nextLine();

        ArrayList<String> procesos = new ArrayList<>(numRegistros);
        ArrayList<Integer> tiempoLlegada = new ArrayList<>(numRegistros);
        ArrayList<Integer> tiempoCPU = new ArrayList<>(numRegistros);
        ArrayList<Integer> Prioridad = new ArrayList<>(numRegistros);

        
        for (int i = 0; i < numRegistros; i++) {
            System.out.print("Ingrese el nombre del proceso numero " + (i + 1) + ": ");
            String nombreProceso = scanner.nextLine();
            procesos.add(nombreProceso);

            System.out.print("Ingrese el tiempo de llegada para el proceso " + nombreProceso + ": ");
            tiempoLlegada.add(scanner.nextInt());
            scanner.nextLine();

            System.out.print("Ingrese el tiempo de CPU para el proceso " + nombreProceso + ": ");
            tiempoCPU.add(scanner.nextInt());
            scanner.nextLine();

            System.out.print("Ingrese la prioridad para el proceso " + nombreProceso + ": ");
            Prioridad.add(scanner.nextInt());
            scanner.nextLine();
        }

        
        ordenarProcesos(procesos, tiempoLlegada, tiempoCPU, Prioridad);

    
        ArrayList<Integer> tiempoCPUAcumulado2 = new ArrayList<>();
        tiempoCPUAcumulado2.add(0);
        int acumulado2 = 0;

        for (int tiempo : tiempoCPU) {
            acumulado2 += tiempo;
            tiempoCPUAcumulado2.add(acumulado2);
        }

        ArrayList<Integer> tiempoCalculo2 = new ArrayList<>();
        for (int i = 0; i < numRegistros; i++) {
            int acumulado4 = tiempoCPUAcumulado2.get(i) - tiempoLlegada.get(i);
            tiempoCalculo2.add(acumulado4);
        }

    
        ArrayList<Integer> tiempoCPUAcumulado = new ArrayList<>();
        tiempoCPUAcumulado.add(0);

        for (int i = 0; i < tiempoCPU.size(); i++) {
            int acumulado = tiempoCPUAcumulado.get(i) + tiempoCPU.get(i);
            tiempoCPUAcumulado.add(acumulado);
        }

        
        System.out.println("Procesos: " + procesos);
        System.out.println("Tiempo de Llegada: " + tiempoLlegada);
        System.out.println("Tiempo de CPU: " + tiempoCPU);
        System.out.println("Diagrama de Gantt: " + tiempoCPUAcumulado);
        System.out.println("Prioridad: " + Prioridad);
        System.err.println("Este codigo tiene errores");

        
        double promedio2 = 0;
        for (double tiempo : tiempoCalculo2) {
            promedio2 += tiempo;
        }
        double promedio3 = promedio2 / numRegistros;

        // Tabla 1
        System.out.println("Tabla 1");
        System.out.println("Proceso | Tiempo de inicio GANTT | Tiempo de llegada | Tiempo (-)");
        for (int i = 0; i < procesos.size(); i++) {
            System.out.printf("%s | %d | %d | %d%n",
                    procesos.get(i),
                    tiempoCPUAcumulado2.get(i),
                    tiempoLlegada.get(i),
                    tiempoCalculo2.get(i));
        }
        System.out.println("Promedio: " + promedio3);

        // Tabla 2
        ArrayList<Integer> array2 = new ArrayList<>(tiempoCPUAcumulado);
        array2.remove(0);

        ArrayList<Integer> tiempoCalculo = new ArrayList<>();
        for (int i = 0; i < numRegistros; i++) {
            int acumulado7 = array2.get(i) - tiempoLlegada.get(i);
            tiempoCalculo.add(acumulado7);
        }

        double promedio = 0;
        for (double tiempo : tiempoCalculo) {
            promedio += tiempo;
        }
        double promedio1 = promedio / numRegistros;

        System.out.println("Tabla 2");
        System.out.println("Proceso | Tiempo de FIN de GANTT | Tiempo de llegada | Tiempo (-)");
        for (int i = 0; i < procesos.size(); i++) {
            System.out.printf("%s | %d | %d | %d%n",
                    procesos.get(i),
                    array2.get(i),
                    tiempoLlegada.get(i),
                    tiempoCalculo.get(i));
        }
        System.out.println("Promedio: " + promedio1);
        System.err.println("Este código tiene errores");

        scanner.close();
    }

  
    private static void ordenarProcesos(ArrayList<String> procesos, ArrayList<Integer> tiempoLlegada,
                                        ArrayList<Integer> tiempoCPU, ArrayList<Integer> Prioridad) {
        for (int j = 1; j < Prioridad.size(); j++) {
            for (int i = 0; i < Prioridad.size() - 1; i++) {
                
                if (Prioridad.get(i) > Prioridad.get(i + 1)) {
                    intercambiar(i, i + 1, procesos, tiempoLlegada, tiempoCPU, Prioridad);
                }
                
                else if (Prioridad.get(i).equals(Prioridad.get(i + 1))) {
                    if (tiempoLlegada.get(i) > tiempoLlegada.get(i + 1)) {
                        intercambiar(i, i + 1, procesos, tiempoLlegada, tiempoCPU, Prioridad);
                    }
                } 
            }
        }
    }
    
    private static void intercambiar(int index1, int index2,
                                      ArrayList<String> procesos,
                                      ArrayList<Integer> tiempoLlegada,
                                      ArrayList<Integer> tiempoCPU,
                                      ArrayList<Integer> Prioridad) {
    
        int tmp = tiempoLlegada.get(index1);
        tiempoLlegada.set(index1, tiempoLlegada.get(index2));
        tiempoLlegada.set(index2, tmp);

        
        tmp = tiempoCPU.get(index1);
        tiempoCPU.set(index1, tiempoCPU.get(index2));
        tiempoCPU.set(index2, tmp);

    
        String tmpProceso = procesos.get(index1);
        procesos.set(index1, procesos.get(index2));
        procesos.set(index2, tmpProceso);

   
        tmp = Prioridad.get(index1);
        Prioridad.set(index1, Prioridad.get(index2));
        Prioridad.set(index2, tmp);
    }  
    
    
}
