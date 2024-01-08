import java.util.*;

public class AStarAlgorithm {

    public static void main(String[] args) {
        String[] ciudades = {"MADRID", "PARIS", "AMSTERDAM", "ROMA", "ATHENS", "KIEV", "BRUGES", "LONDON", "LISBON", "BERLIN", "MOSCOW", "WARSAW"};

        int[][] matrizHeuristica = { 
          {0, 1051, 1480, 1364, 2368, 2861, 1312, 1261, 502, 1869,3440,2293},
          {1051, 0, 430, 1051, 2095, 2023, 268, 344, 1453, 878, 2480, 1370},
          {1480, 430, 0, 1480, 2164, 1780, 172, 359, 1863, 577, 2147, 1098},
          {1364, 1107, 1298, 0, 1048, 1675, 1253, 1436, 1863, 1184, 2375, 1318},
          {2368, 2095, 2164, 1048, 0, 1487, 2176, 2393,2850, 1803, 2231, 1598},
          {2861, 2023, 1780, 1675, 1487, 0, 1908, 2136, 3351, 1203, 754, 685},
          {1312, 268, 172, 1253, 2176, 1908, 0, 237, 1090, 713, 2305, 1231},
          {1261, 344, 359, 1436, 2393, 2136, 237, 0, 1583, 934, 2503, 1455},
          {502, 1453, 1863, 1863, 2850, 3351, 1090, 1583, 0, 2312, 3905, 2763},
          {1869, 878, 577, 1184, 1803, 1203, 713, 934, 2312, 0, 1608, 1520},
          {3440, 2486, 2147, 2375, 2231, 754, 2305, 2503, 3905, 1608, 0, 1146},
          {2293, 1370, 1098, 1318, 1598, 685, 1231, 1455, 2763, 520, 1146, 0} };
        int[][] matrizAdyacencia = { 
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
            {0,0,0,0,0,0,0,0,0,574,1261,0} };

            int inicio = 0; // Nodo de inicio
            int ciudadesPorVisitar = 8;
    
            List<Integer> mejorRuta = encontrarMejorRuta(matrizHeuristica, matrizAdyacencia, ciudadesPorVisitar, inicio);
    
            System.out.println("Mejor Ruta:");
            if (!mejorRuta.isEmpty()) {
                for (int ciudad : mejorRuta) {
                    System.out.print(ciudades[ciudad] + " ");
                }
            } else {
                System.out.println("No se encontró una ruta.");
            }
        }
    
        public static List<Integer> encontrarMejorRuta(int[][] heuristica, int[][] adyacencia, int ciudadesPorVisitar, int inicio) {
            PriorityQueue<Nodo> colaPrioridad = new PriorityQueue<>(Comparator.comparingInt(n -> n.f));
    
            Set<Integer> nodosVisitados = new HashSet<>();
            colaPrioridad.add(new Nodo(inicio, 0, heuristica[inicio][inicio], null, ciudadesPorVisitar - 1));
    
            while (!colaPrioridad.isEmpty()) {
                Nodo actual = colaPrioridad.poll();
    
                if (actual.ciudadesRestantes == 0 && adyacencia[actual.nodo][inicio] != 0) {
                    // Se han visitado todas las ciudades requeridas y hay una arista de regreso al punto de inicio
                    List<Integer> ruta = new ArrayList<>();
                    while (actual != null) {
                        ruta.add(actual.nodo);
                        actual = actual.padre;
                    }
                    Collections.reverse(ruta);
                    return ruta;
                }
    
                nodosVisitados.add(actual.nodo);
    
                for (int vecino = 0; vecino < adyacencia.length; vecino++) {
                    if (adyacencia[actual.nodo][vecino] != 0 && !nodosVisitados.contains(vecino)) {
                        int g = actual.g + adyacencia[actual.nodo][vecino];
                        int h = heuristica[vecino][inicio]; // Heurística ajustada para regresar al punto de inicio
                        int f = g + h;
                        colaPrioridad.add(new Nodo(vecino, g, h, actual, actual.ciudadesRestantes - 1));
                    }
                }
            }
    
            return Collections.emptyList(); // No se encontró una ruta
        }
    
        static class Nodo {
            int nodo;
            int g; // Costo real desde el nodo inicial hasta este nodo
            int h; // Estimación heurística desde este nodo hasta el nodo de inicio
            int f; // Función de evaluación: f(n) = g(n) + h(n)
            Nodo padre;
            int ciudadesRestantes; // Número de ciudades que faltan por visitar
    
            Nodo(int nodo, int g, int h, Nodo padre, int ciudadesRestantes) {
                this.nodo = nodo;
                this.g = g;
                this.h = h;
                this.f = g + h;
                this.padre = padre;
                this.ciudadesRestantes = ciudadesRestantes;
            }
        }
}


