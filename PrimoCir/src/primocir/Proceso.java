/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primocir;
import java.util.ArrayList;

/**
 *
 * @author jesus
 */
public class Proceso implements Runnable{
    private ArrayList arrayPrimos;
    private final Numero primo;
    
    public Proceso(int posicion,ArrayList <Numero> arrayPrimos){
        this.arrayPrimos = new ArrayList<>();
        this.arrayPrimos=arrayPrimos;
        this.primo= arrayPrimos.get(posicion);
    }
    
    @Override
    public void run(){
        boolean esPrimo=false;
        boolean esPrimoCircular=false;
        
        if (this.primo.getNum()>9) { //si el numero es mayor que 9 entonces recorremos sus circulares
            String cadena=Integer.toString(this.primo.getNum());
            if (!cadena.contains("5") && !cadena.contains("0") && !cadena.contains("2") && !cadena.contains("4") && !cadena.contains("6") && !cadena.contains("8")) {    
                if (primo.getCirculares().size()>0) { //tiene circulares       
                    for (Integer circular : primo.getCirculares()) { //recorremos el array de numeros circulares del numero
                        //buscamos el circular en el array de numeros primos
                        for (Object arrayPrimo : this.arrayPrimos) {
                            Numero num = (Numero) arrayPrimo;
                            if (circular==num.getNum()) {
                                esPrimo=true; //el circular es primo tambien
                                break;
                            }else{
                                esPrimo=false;
                            }
                        }
                        if (esPrimo==false) {
                            esPrimoCircular=false;
                            break;
                        }else{
                            esPrimoCircular=true;
                        }
                    }
                    //si el numero es un primo circular, lo agregamos array de Primos Circulares
                    if (esPrimoCircular==true) {
                        if (!Main.buscarArrayPrimosCirculares(this.primo.getNum())) { //solo lo agregamos si no existe en el array
                            Main.agregarArrayPrimosCirculares(this.primo.getNum());
                        }
                        for (Integer circular : primo.getCirculares()){ //solo lo agregamos si no existe en el array
                            if (!Main.buscarArrayPrimosCirculares(circular)) {
                                Main.agregarArrayPrimosCirculares(circular);
                            }
                        }
                    } 
                }else{ //si el numero no tiene circulares, es porque el circular es el mismo numero
                    Main.agregarArrayPrimosCirculares(this.primo.getNum());
                }
            }
        }else{ //el numero es menor de 10
            Main.agregarArrayPrimosCirculares(this.primo.getNum());
        }
    }
}
