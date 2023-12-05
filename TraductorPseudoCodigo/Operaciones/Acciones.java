package Operaciones;

import java.util.Scanner;

public class Acciones {
    private Scanner sc = new Scanner(System.in);
    private OperacionesMatematicas op = new OperacionesMatematicas();

    public void accion(String linea){
        String operacion = linea.split(" ")[0];
        String variable ="";

        switch (operacion){
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
                System.out.println(variable+" = "+op.obtenerResultado());
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
