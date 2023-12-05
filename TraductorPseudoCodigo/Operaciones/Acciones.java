package Operaciones;

import java.util.Scanner;
import Operaciones.OperacionesMatematicas;

public class Acciones {
    private Scanner sc = new Scanner(System.in);
    private OperacionesMatematicas op = new OperacionesMatematicas();

    public void accion(String linea){

        String operacion = linea.split(" ")[0];
        String variable ="";
        boolean saltoLinea = false;
        String restante = "";
        switch (operacion){
            case "Leer":
                variable = linea.split(" ")[1];
                System.out.print("Ingrese el valor de "+variable+": ");
                op.asignarValor(variable, sc.nextDouble());
                break;
            
            case "ImprimeS":
                saltoLinea = true;
            case "Imprime":
                restante = linea.substring(linea.indexOf("(")+1, linea.indexOf(")"));   
                String [] datos = restante.split(",");
                for (int i = 0; i<datos.length; i++){
                    if (op.isVariableExist(datos[i])){
                        System.out.print(op.obtenerValor(datos[i]));
                    }else{
                        System.out.print(datos[i].replace("'", ""));
                    }
                        
                }
                if (saltoLinea){
                    System.out.println();
                }

                break;
                
            case "Entero":
            case "Real":
                variable = linea.split(" ")[1];
                op.inicializarVariables(operacion, variable);
                break;
                
            default:
                variable = linea.split(" ")[0];
                for(int i = 2; i<linea.split(" ").length; i++){
                    if (esNumero(linea.split(" ")[i])){
                        op.colocarDatoEnPila(Double.parseDouble(linea.split(" ")[i]));
                }else{
                        op.colocarDatoEnPila(linea.split(" ")[i]);
                    }                  
                }
                op.vaciarPilaOperador();
                op.asignarValor(variable, op.obtenerResultado());
        }

    }
    public boolean esNumero(String numero){
        try{
            Double.parseDouble(numero);
            return true;
        }catch (Exception e){
            return false;
        }
    }





}
