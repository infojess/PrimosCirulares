/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primocir;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author jesus
 */
public class Main{
private static final ArrayList <Integer> arrayPrimosCirculares = new ArrayList<>();
private static ArrayList <Numero> arrayPrimos = new ArrayList<>();

    public synchronized static ArrayList getArrayPrimosCirculares() {
        return arrayPrimosCirculares;
    }
    public synchronized static Boolean buscarArrayPrimosCirculares(int coincidencia) {
        return arrayPrimosCirculares.contains(coincidencia);
    }
    public synchronized static void agregarArrayPrimosCirculares(Integer numero) {
        arrayPrimosCirculares.add(numero);
    }

    //PUNTO DE INICIO DEL PROGRAMA
    public static void main(String[] args) throws IOException {  
        long init = System.currentTimeMillis();
        
        //---------------------------------------------------
        int numfinal =1000000; //numero final que evaluaremos
        //---------------------------------------------------
        
        //obtenemos un array de numeros primos
        Numero num = new Numero(0);
        arrayPrimos=num.calcularPrimos(numfinal);

        //cantidad de procesadores disponibles
        int cantProc = Runtime.getRuntime().availableProcessors();

        // hilos
            ExecutorService executor = Executors.newFixedThreadPool(cantProc*2);
            for (int i = 0; i < arrayPrimos.size(); i++)  {
                Runnable numeroPrimo = new Proceso(i,arrayPrimos);
                executor.execute(numeroPrimo);
            }
            executor.shutdown();
            while (!executor.isTerminated()) {
                    //esperamos a que se terminen de ejecutar todos los procesos
                    //para continuar con el programa
            }
        
        Collections.sort(arrayPrimosCirculares); //ordenamos el array de primos circulares
        
        //mostramos la lista de primos circulares hasta el numero final
        int count=0;
        System.out.println();
        for (Integer primoCir : arrayPrimosCirculares) {
            if (primoCir <= numfinal) {
                System.out.print(primoCir+"  ");
                count++;
            }
        }
        System.out.println();
        System.out.println("Cantidad total de Primos Circulares hasta el número "+numfinal+": "+count);
        long fin = System.currentTimeMillis();
        System.out.println("Tiempo total de procesamiento: "+(fin-init)/1000+" (segundos)");
        System.out.println("Autor: Jesús Arce | Fecha: 21/08/2015 | Evaluación práctica para MercadoLibre.");
        System.out.println("------------------------------------------------------------------------------");
    }
}
