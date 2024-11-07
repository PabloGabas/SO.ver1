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
public class RoundRobin {
    
  public static void roundRobin(ArrayList<String> processNames, ArrayList<Integer> arrivalTimes, ArrayList<Integer> burstTimes, int quantum) {  
        int n = processNames.size();
        int[] remainingTimes = new int[n];
        for (int i = 0; i < n; i++) {
            remainingTimes[i] = burstTimes.get(i); // Inicializa el tiempo restante
        }

        int currentTime = 0;
        int completedProcesses = 0;

        while (completedProcesses < n) {
            boolean executed = false;

            for (int i = 0; i < n; i++) {
                // Solo ejecutar si el proceso ha llegado y aún tiene tiempo restante
                if (arrivalTimes.get(i) <= currentTime && remainingTimes[i] > 0) {
                    executed = true;

                    if (remainingTimes[i] > quantum) {
                        currentTime += quantum;
                        remainingTimes[i] -= quantum;
                    } else {
                        currentTime += remainingTimes[i];
                        System.out.println("Proceso " + processNames.get(i) + " completado en tiempo " + currentTime);
                        remainingTimes[i] = 0;
                        completedProcesses++;
                    }
                }
            }

            // Si no se ejecutó ningún proceso, avanzar el tiempo
            if (!executed) {
                currentTime++;
            }
        }

        System.out.println("Todos los procesos han sido completados.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Ingrese el número de procesos a ordenar: ");
        int numRegistros = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Ingrese el quantum que tendrá: ");
        int quantum = scanner.nextInt();
        scanner.nextLine();

        ArrayList<String> procesos = new ArrayList<>(numRegistros);
        ArrayList<Integer> tiempoLlegada = new ArrayList<>(numRegistros);
        ArrayList<Integer> tiempoCPU = new ArrayList<>(numRegistros);
        
        for (int i = 0; i < numRegistros; i++) {
            System.out.print("Ingrese el nombre del proceso número " + (i + 1) + ": ");
            String nombreProceso = scanner.nextLine();
            procesos.add(nombreProceso);
            
            System.out.print("Ingrese el tiempo de llegada para el proceso " + nombreProceso + ": ");
            tiempoLlegada.add(scanner.nextInt());
            scanner.nextLine(); 

            System.out.print("Ingrese el tiempo de CPU para el proceso " + nombreProceso + ": ");
            tiempoCPU.add(scanner.nextInt());
            scanner.nextLine(); 
        }
        
        roundRobin(procesos, tiempoLlegada, tiempoCPU, quantum);
        
        
        ArrayList<Integer> tiempoCPUAcumulado = new ArrayList<>();
tiempoCPUAcumulado.add(0); 

for (int i = 0; i < tiempoCPU.size(); i++) {
    int acumulado = tiempoCPUAcumulado.get(i) + tiempoCPU.get(i);
    tiempoCPUAcumulado.add(acumulado);
} 



        // Imprimir los datos ordenados
        System.out.println("Procesos: " + procesos);
        System.out.println("Tiempo de Llegada: " + tiempoLlegada);
        System.out.println("Tiempo de CPU: " + tiempoCPU);
        System.out.println("Diagrama de Gantt " + tiempoCPUAcumulado);
    }
    
    
    
    
    
    
    
    
}
