import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Nombres de ciudades
        String[] nombresCiudades = {
                "MADRID", "PARIS", "AMSTERDAM", "ROMA", "ATHENS", "KIEV", "BRUGES", "LONDON", "LISBON", "BERLIN", "MOSCOW", "WARSAW"
        };

        // Coordenadas de ciudades
        ArrayList<Coordenada> coordenadasCiudades = new ArrayList<>();
        coordenadasCiudades.add(new Coordenada("MADRID", 40.4165, -3.70256));
        coordenadasCiudades.add(new Coordenada("PARIS", 48.8566, 2.35222));
        coordenadasCiudades.add(new Coordenada("AMSTERDAM", 52.3702, 4.89517));
        coordenadasCiudades.add(new Coordenada("ROMA", 41.9028, 12.4964));
        coordenadasCiudades.add(new Coordenada("ATHENS", 37.9838, 23.7275));
        coordenadasCiudades.add(new Coordenada("KIEV", 50.4501, 30.5234));
        coordenadasCiudades.add(new Coordenada("BRUGES", 51.2093, 3.2247));
        coordenadasCiudades.add(new Coordenada("LONDON", 51.5074, -0.1278));
        coordenadasCiudades.add(new Coordenada("LISBON", 38.7223, -9.13934));
        coordenadasCiudades.add(new Coordenada("BERLIN", 52.5200, 13.4050));
        coordenadasCiudades.add(new Coordenada("MOSCOW", 55.7558, 37.6173));
        coordenadasCiudades.add(new Coordenada("WARSAW", 52.2297, 21.0122));

        int[][] distancias = {
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

        // París, Roma, Varsovia, Berlín, Ámsterdam, Brujas, Londres, París
        ArrayList<Integer> ciudades = new ArrayList<>();
        ciudades.add(1);
        ciudades.add(3);
        ciudades.add(11);
        ciudades.add(9);
        ciudades.add(2);
        ciudades.add(6);
        ciudades.add(7);
        ciudades.add(1);

        ArrayList<Integer> rutaOptima = FuerzaBruta.fuerzaBruta(ciudades, distancias);
        System.out.println("Ruta óptima: " + obtenerNombresCiudades(rutaOptima, nombresCiudades));
        System.out.println("Longitud óptima: " + FuerzaBruta.calcularLongitudRuta(rutaOptima, distancias));
        generarHTMLRutaOptima(rutaOptima, nombresCiudades, coordenadasCiudades);
    }

    // Método para obtener los nombres de las ciudades dada una ruta
    private static String obtenerNombresCiudades(ArrayList<Integer> ruta, String[] nombresCiudades) {
        StringBuilder nombres = new StringBuilder();
        for (int i = 0; i < ruta.size(); i++) {
            nombres.append(nombresCiudades[ruta.get(i)]);
            if (i < ruta.size() - 1) {
                nombres.append(" -> ");
            }
        }
        return nombres.toString();
    }

    private static void generarHTMLRutaOptima(ArrayList<Integer> ruta, String[] nombresCiudades, ArrayList<Coordenada> coordenadasCiudades) {
        try {
            FileWriter writer = new FileWriter("ruta_optima.html");
            writer.write("<!DOCTYPE html>\n");
            writer.write("<html>\n");
            writer.write("<head>\n");
            writer.write("  <title>Ruta Óptima</title>\n");
            writer.write("</head>\n");
            writer.write("<body>\n");

            // Agregar el script para cargar la API de Google Maps
            writer.write("  <script async defer src=\"https://maps.googleapis.com/maps/api/js?key=AIzaSyBJAyvtJjf7rIuTZ7IsQU-Qf5lmKro60AE&callback=inicializarMapa\"></script>\n");

            // Definir el script JavaScript para inicializar el mapa y mostrar la ruta
            writer.write("  <script>\n");
            writer.write("    function inicializarMapa() {\n");
            writer.write("      var mapa = new google.maps.Map(document.getElementById('mapa'), {\n");
            writer.write("        zoom: 6,\n");
            writer.write("        center: { lat: 40.4168, lng: -3.7038 } // Centrar en Madrid o ajustar según necesidad\n");
            writer.write("      });\n");

            // Añadir marcadores para cada ciudad en la ruta
            for (int ciudadIndex : ruta) {
                String nombreCiudad = nombresCiudades[ciudadIndex];
                Coordenada coordenada = obtenerCoordenadasCiudad(nombreCiudad, coordenadasCiudades);
                writer.write("      new google.maps.Marker({\n");
                writer.write("        position: { lat: " + coordenada.getLatitud() + ", lng: " + coordenada.getLongitud() + " },\n");
                writer.write("        map: mapa,\n");
                writer.write("        title: '" + nombreCiudad + "'\n");
                writer.write("      });\n");
            }

            writer.write("    }\n");
            writer.write("  </script>\n");

            // Agregar un contenedor para el mapa
            writer.write("  <div id=\"mapa\" style=\"height: 500px;\"></div>\n");

            writer.write("</body>\n");
            writer.write("</html>\n");

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Coordenada obtenerCoordenadasCiudad(String nombreCiudad, ArrayList<Coordenada> coordenadasCiudades) {
        for (Coordenada coordenada : coordenadasCiudades) {
            if (coordenada.getNombre().equalsIgnoreCase(nombreCiudad)) {
                return coordenada;
            }
        }
        return new Coordenada("Desconocida", 0.0, 0.0);
    }

    public static class Coordenada {
        private final String nombre;
        private final double latitud;
        private final double longitud;

        public Coordenada(String nombre, double latitud, double longitud) {
            this.nombre = nombre;
            this.latitud = latitud;
            this.longitud = longitud;
        }

        public String getNombre() {
            return nombre;
        }

        public double getLatitud() {
            return latitud;
        }

        public double getLongitud() {
            return longitud;
        }
    }
}
