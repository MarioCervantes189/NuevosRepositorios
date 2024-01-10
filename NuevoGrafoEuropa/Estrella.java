import java.util.ArrayList;

public class Estrella{
    private int ciudadDestino;
    private ArrayList<Integer> listaVisitados = new ArrayList<Integer>();


    private int [][] matrizAdyacencia = {
        {0,0,0,1962,0,0,0,0,625,0,0,0},
        {0,0,0,0,0,0,0,470,1733,0,0,0},
        {0,0,0,0,0,0,248,0,0,657,0,0},
        {1962,0,0,0,1299,0,0,0,0,0,0,0},
        {0,0,0,1299,0,2215,0,0,0,0,0,0},
        {0,0,0,0,2215,0,0,0,0,0,880,0},
        {0,0,248,0,0,0,0,285,0,0,0,0},
        {0,470,0,0,0,0,285,0,0,0,0,0},
        {625,1733,0,0,0,0,0,0,0,0,0,0},
        {0,0,657,0,0,0,0,0,0,0,0,574},
        {0,0,0,0,0,880,0,0,0,0,0,1261},
        {0,0,0,0,0,0,0,0,0,574,1261,0}
  };

  private int[][] heuristica = {
    {0, 1051, 1480, 1364, 2368, 2861, 1312, 1261, 502, 1869, 3440, 2293},
    {1051, 0, 430, 1051, 2095, 2023, 268, 344, 1453, 878, 2480, 1370},
    {1480, 430, 0, 1480, 2164, 1780, 172, 359, 1863, 577, 2147, 1098},
    {1364, 1107, 1298, 0, 1048, 1675, 1253, 1436, 1863, 1184, 2375, 1318},
    {2368, 2095, 2164, 1048, 0, 1487, 2176, 2393, 2850, 1803, 2231, 1598},
    {2861, 2023, 1780, 1675, 1487, 0, 1908, 2136, 3351, 1203, 754, 685},
    {1312, 268, 172, 1253, 2176, 1908, 0, 237, 1090, 713, 2305, 1231},
    {1261, 344, 359, 1436, 2393, 2136, 237, 0, 1583, 934, 2503, 1455},
    {502, 1453, 1863, 1863, 2850, 3351, 1090, 1583, 0, 2312, 3905, 2763},
    {1869, 878, 577, 1184, 1803, 1203, 713, 934, 2312, 0, 1608, 1520},
    {3440, 2486, 2147, 2375, 2231, 754, 2305, 2503, 3905, 1608, 0, 1146},
    {2293, 1370, 1098, 1318, 1598, 685, 1231, 1455, 2763, 520, 1146, 0}
 };

public void metodoEstrella(int num) {
    if (num == ciudadDestino) {
        System.out.println("Ciudad destino encontrada");
        return;
    } else {
        listaVisitados.add(num);
        int menor = 999999;
        int ciudadMenor = 0;
        for (int i = 0; i < matrizAdyacencia.length; i++) {
            if (matrizAdyacencia[num][i] != 0 && !listaVisitados.contains(i)) {
                int aux = matrizAdyacencia[num][i] + heuristica[i][ciudadDestino];
                if (aux < menor) {
                    menor = aux;
                    ciudadMenor = i;
                }
            }
        }
        metodoEstrella(ciudadMenor);
    }
}

public void setCiudadDestino(int ciudadDestino) {
    this.ciudadDestino = ciudadDestino;
    
}
public static int calcularDistancia(ArrayList<Integer> listaVisitados, int[][] matrizAdyacencia) {
    int longitud = 0;
    for (int i = 0; i < listaVisitados.size() - 1; i++) {
        int ciudadActual = listaVisitados.get(i);
        int ciudadSiguiente = listaVisitados.get(i + 1);
        longitud += matrizAdyacencia[ciudadActual][ciudadSiguiente];
    }
    longitud += matrizAdyacencia[listaVisitados.get(listaVisitados.size() - 1)][listaVisitados.get(0)];
    return longitud;
}


    
     
public static void main(String[] args) {
    Estrella estrella = new Estrella();
    estrella.setCiudadDestino(4);
    estrella.metodoEstrella(0);
    System.out.println(estrella.listaVisitados);
    System.out.println("Distancia: " + calcularDistancia(estrella.listaVisitados, estrella.matrizAdyacencia));
    }

}



   
