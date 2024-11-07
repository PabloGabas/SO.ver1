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
public class FIFO {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        
        System.out.print("Ingrese el numero de procesos a ordenar: ");
        int numRegistros = scanner.nextInt();
        scanner.nextLine();

        ArrayList<String> procesos = new ArrayList<>(numRegistros);
        ArrayList<Integer> tiempoLlegada = new ArrayList<>(numRegistros);
        ArrayList<Integer> tiempoCPU = new ArrayList<>(numRegistros);

       
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
        }

  
        for (int j = 1; j < tiempoLlegada.size(); j++) {
            for (int i = 0; i < tiempoLlegada.size() - 1; i++) {
                if (tiempoLlegada.get(i) > tiempoLlegada.get(i + 1)) {
                 
                    int tmp = tiempoLlegada.get(i);
                    tiempoLlegada.set(i, tiempoLlegada.get(i + 1));
                    tiempoLlegada.set(i + 1, tmp);
                    
                  
                    tmp = tiempoCPU.get(i);
                    tiempoCPU.set(i, tiempoCPU.get(i + 1));
                    tiempoCPU.set(i + 1, tmp);
                    
                    
                    String tmpProceso = procesos.get(i);
                    procesos.set(i, procesos.get(i + 1));
                    procesos.set(i + 1, tmpProceso);
                    
                    
  //(logica utilizada por DevJoel(2017) : https://es.stackoverflow.com/questions/67883/algoritmo-de-planificacion-fifo )//
                }
            }
        } 
        

        ArrayList<Integer> tiempoCPUAcumulado2 = new ArrayList<>();
         tiempoCPUAcumulado2.add(0);
        int acumulado2 = 0; // Inicializar en 0

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



        // Imprimir los datos ordenados
        System.out.println("Procesos: " + procesos);
        System.out.println("Tiempo de Llegada: " + tiempoLlegada);
        System.out.println("Tiempo de CPU: " + tiempoCPU);
        System.out.println("Diagrama de Gantt " + tiempoCPUAcumulado);
        
        
  double promedio2 = 0;
for (double tiempo : tiempoCalculo2) {
    promedio2 += tiempo;
}
double promedio3 = promedio2 / numRegistros;       
        

System.out.println("Tabla 1");
System.out.println("Proceso | Tiempo de inicio GANTT | Tiempo de llegada | Tiempo (-)");
for (int i = 0; i < procesos.size(); i++) {
    System.out.printf("%s | %d | %d | %d%n", 
        procesos.get(i), 
        tiempoCPUAcumulado2.get(i),
        tiempoLlegada.get(i),
        tiempoCalculo2.get(i));
}
        System.out.println("Promedio " + promedio3);
    
    
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




// Imprimir la Tabla 2

System.out.println("Tabla 2");
System.out.println("Proceso | Tiempo de FIN de GANTT | Tiempo de llegada | Tiempo (-)");
for (int i = 0; i < procesos.size(); i++) {
    System.out.printf("%s | %d | %d | %d%n", 
        procesos.get(i), 
        array2.get(i),
        tiempoLlegada.get(i),
        tiempoCalculo.get(i)); 
}
        System.out.println("Pormedio " + promedio1 );




        scanner.close();
    }
    
    
    
    
    
    
    
}
