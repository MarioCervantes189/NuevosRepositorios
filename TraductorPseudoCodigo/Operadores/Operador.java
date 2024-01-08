package Operadores;
public enum Operador{
    PRIMERO(new String[]{"^"}, 3),
    SEGUNDO(new String[]{"*", "/"}, 2),
    TERCERO(new String[]{"+", "-"}, 1),
    PARENTESIS(new String[]{"(", ")"}, 0);


    public String[] operadores;
    public int prioridad;

    Operador(String[] operadores, int prioridad){
        this.operadores = operadores;
        this.prioridad = prioridad;
    }

    public static String getParentesisAbierto(){
        return PARENTESIS.operadores[0];
    }

    public static String getParentesisCerrado(){
        return PARENTESIS.operadores[1];
    } 

    public static int getPrioridad(String operador){
        for(Operador op : Operador.values()){
            for(String o : op.operadores){
                if(o.equals(operador)){
                    return op.prioridad;
                }
            }
        }
        return 0;
    }

    public static boolean esOperador(String operador){
        for(Operador op : Operador.values()){
            for(String o : op.operadores){
                if(o.equals(operador)){
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean isMayor(String op1, String op2){
        return getPrioridad(op1) > getPrioridad(op2);
    }


    
}