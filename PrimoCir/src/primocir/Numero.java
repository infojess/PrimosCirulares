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
public class Numero {
    private int num;
    private boolean esPrimo;
    private ArrayList<Integer> circulares;
    
    //CONSTRUCTOR
    public Numero(int numero) {
        this.circulares = new ArrayList<>();
        this.num=numero;
    }

    //METODOS
    public ArrayList<Integer> getCirculares() {
        return this.circulares;
    }
    public void setCirculares(ArrayList<Integer> circulares) {
        this.circulares = circulares;
    }     
    public boolean getEsPrimo(){
        return this.esPrimo;
    }
    public void setEsPrimo(boolean esprimo){
        this.esPrimo=esprimo;
    }
    public int getNum() {
        return this.num;
    }
    public void setNum(int num) {
        this.num = num;
    }
    
    //Devuelve un arreglo de numeros primos
    public ArrayList<Numero> calcularPrimos(int numerofinal){             
        ArrayList<Numero> numerosPrimos=new ArrayList<Numero>();
        numerosPrimos.clear();
        Numero primoInicial=new Numero(2);
        numerosPrimos.add(0,primoInicial);
        for(int i=3;i<=numerofinal;i+=2){
            Numero numero=new Numero(i); //creamos un objeto numero
            int tamanio=numerosPrimos.size();
                double raiz=Math.sqrt(i);
                if (raiz%1!=0) { //si la raiz cuadrada no es entera, seguimos comprobando si es primo
                    double raiz_redondeada=Math.ceil(raiz);
                    int hasta=(int) raiz_redondeada; 
                    if (tamanio>hasta) {
                        tamanio=hasta; //hasta dónde haremos divisiones
                    }
                    for(int x=0;x<tamanio;x++){ //calculamos si es primo
                        if(i%numerosPrimos.get(x).getNum() != 0){
                            numero.setEsPrimo(true);
                        }else{
                            numero.setEsPrimo(false);
                            break;
                        }
                    }
                    //el numero es primo
                    if (numero.esPrimo==true) {                 
                        numerosPrimos.add(numero); //lo agregamos a la lista de primos
                        calcularCirculares(numero);//obtenemos los circulares del numero
                        for (Integer circular : numero.getCirculares()) {
                            if (circular > numerofinal) {//si encontramos un circular mayor al numero final
                                numerofinal=circular; //aumentaremos el tamaño del array de primos
                            }
                        }
                    }
                }
        }
        return numerosPrimos;
    }

    //Devuelve un array con los numeros circulares de un numero
    public void calcularCirculares(Numero numero){
        int numCircular;          
        String entrada=Integer.toString(numero.getNum());  
        ArrayList<Integer> numerosCirculares=new ArrayList<>();
        for(int i=0;i<=entrada.length()-1;i++){
            entrada=entrada.charAt(entrada.length()-1)+ entrada.substring(0,entrada.length()-1);
            //System.out.println(entrada);
            numCircular=Integer.parseInt(entrada);
            if(numCircular != numero.getNum()){
                numerosCirculares.add(numCircular);  
            }
        }
        numero.setCirculares(numerosCirculares); 
    }
   
}
