import java.util.ArrayList;

public class FuerzaBruta {

    public static int calcularLongitudRuta(ArrayList<Integer> ruta, int[][] matriz) {
        int longitud = 0;
        for (int i = 0; i < ruta.size() - 1; i++) {
            int ciudadActual = ruta.get(i);
            int ciudadSiguiente = ruta.get(i + 1);
            longitud += matriz[ciudadActual][ciudadSiguiente];
        }

        longitud += matriz[ruta.get(ruta.size() - 1)][ruta.get(0)];
        return longitud;
    }

    private static boolean permutacion(ArrayList <Integer> array){
        int i = array.size() - 1;
        while (i > 0 && array.get(i - 1) >= array.get(i)) {
            i--;
        }
        if (i <= 0) {
            return false;
        }

        int j = array.size() - 1;
        while (array.get(j) <= array.get(i - 1)) {
            j--;
        }

        int temp = array.get(i - 1);
        array.set(i - 1, array.get(j));
        array.set(j, temp);

        j = array.size() - 1;
        while (i < j) {
            temp = array.get(i);
            array.set(i, array.get(j));
            array.set(j, temp);
            i++;
            j--;
        }
        return true;
    }

    public static ArrayList<Integer> fuerzaBruta(ArrayList<Integer> ciudades, int[][]distancias){
        ArrayList<Integer> rutaOptima = new ArrayList<>(ciudades);
        int longitudOptima = calcularLongitudRuta(rutaOptima, distancias);

        ArrayList<Integer>  rutaPermutada = new ArrayList<>(ciudades);
        while (permutacion(rutaPermutada)) {
            int longitudPermutada = calcularLongitudRuta(rutaPermutada, distancias);
            if (longitudPermutada < longitudOptima) {
                rutaOptima = new ArrayList<>(rutaPermutada);
                longitudOptima = longitudPermutada;
            }
        }
        return rutaOptima;
    }
    
}