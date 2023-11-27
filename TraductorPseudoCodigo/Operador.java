package TraductorPseudoCodigo;

public enum Operador{
    PRIMERO(new String[]{"^"}, 3),
    SEGUNDO(new String[]{"*", "/"}, 2),
    TERCERO(new String[]{"+", "-"}, 1);

    public String[] operadores;
    public int prioridad;

    Operador(String[] operadores, int prioridad){
        this.operadores = operadores;
        this.prioridad = prioridad;
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