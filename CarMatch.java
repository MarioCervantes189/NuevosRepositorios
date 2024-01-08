
public record CarMatch() {
    public static void main(String[] args) {
        // Your code here
        // Crear un objeto de la clase Administrador
        Administrador administrador = new Administrador();
        // Crear un objeto de la clase Conductor
        Conductor conductor = new Conductor();
        // Crear un objeto de la clase Pasajero
        Pasajero pasajero = new Pasajero();
        // Crear un objeto de la clase Controlador
        Controlador controlador = new Controlador();
        // Crear un objeto de la clase Cuenta
        Cuenta cuenta = new Cuenta();
    }
}
}




    // Crear un objeto de la clase Viaje
    Viaje viaje = new Viaje();
    // Crear un objeto de la clase Pago
    Pago pago = new Pago();
    // Asignar el objeto de la clase Cuenta al objeto de la clase Pasajero
    pasajero.setCuenta(cuenta);
    // Asignar el objeto de la clase Viaje al objeto de la clase Pasajero
    pasajero.setViaje(viaje);
    // Asignar el objeto de la clase Pago al objeto de la clase Viaje
    viaje.setPago(pago);
    // Asignar el objeto de la clase Cuenta al objeto de la clase Conductor
    conductor.setCuenta(cuenta);
    // Asignar el objeto de la clase Conductor al objeto de la clase Controlador
    controlador.setConductor(conductor);
    // Asignar el objeto de la clase Pasajero al objeto de la clase Controlador
    controlador.setPasajero(pasajero);
    // Asignar el objeto de la clase Administrador al objeto de la clase Controlador
    controlador.setAdministrador(administrador);
    // Crear un objeto de la clase Scanner
    Scanner sc = new Scanner(System.in);
    // Pedir al usuario que ingrese su nombre
    System.out.println("Por favor, ingrese su nombre");
    String nombre = sc.nextLine();
    // Pedir al usuario que ingrese su correo
    System.out.println("Por favor, ingrese su correo");
    String correo = sc.nextLine();
    // Pedir al usuario que ingrese su contraseña
    System.out.println("Por favor, ingrese su contraseña");
    String contraseña = sc.nextLine();
    // Pedir al usuario que ingrese su ID
// Clase que representa el controlador del sistema
public class Controlador {
    // Atributos que almacenan los objetos de las clases Conductor y Pasajero
    private Conductor conductor;
    private Pasajero pasajero;

    // Método que solicita el pago al pasajero
    public void solicitudPago() {
        // Obtener el monto final del viaje
        int monto = pasajero.getViaje().getPago().getMontoFinal();
        // Mostrar el monto al pasajero y pedirle que lo confirme
        System.out.println("El monto final del viaje es: " + monto);
        System.out.println("Por favor, confirme el pago");
        // Leer la respuesta del pasajero
        Scanner sc = new Scanner(System.in);
        String respuesta = sc.nextLine();
        // Si la respuesta es afirmativa, realizar el pago
        if (respuesta.equalsIgnoreCase("si")) {
            System.out.println("Pago realizado con éxito");
        }
        // Si la respuesta es negativa, mostrar un mensaje de error
        else {
            System.out.println("Pago no realizado. Por favor, intente de nuevo");
        }
    }

    // Método que solicita el viaje al conductor
    public void solicitudViaje() {
        // Obtener el origen y el destino del viaje
        String origen = pasajero.getViaje().getOrigen();
        String destino = pasajero.getViaje().getDestino();
        // Mostrar el origen y el destino al conductor y pedirle que los acepte
        System.out.println("El origen del viaje es: " + origen);
        System.out.println("El destino del viaje es: " + destino);
        System.out.println("Por favor, acepte el viaje");
        // Leer la respuesta del conductor
        Scanner sc = new Scanner(System.in);
        String respuesta = sc.nextLine();
        // Si la respuesta es afirmativa, iniciar el viaje
        if (respuesta.equalsIgnoreCase("si")) {
            System.out.println("Viaje iniciado");
        }
        // Si la respuesta es negativa, mostrar un mensaje de error
        else {
            System.out.println("Viaje no aceptado. Por favor, espere otro viaje");
        }
    }
}

// Clase que representa al conductor del sistema
public class Conductor {
    // Atributo que almacena el objeto de la clase Cuenta
    private Cuenta cuenta;

    // Método que finaliza el viaje
    public void finViaje() {
        // Mostrar un mensaje de agradecimiento al pasajero
        System.out.println("Gracias por viajar con nosotros");
        // Calificar al pasajero
        System.out.println("Por favor, califique al pasajero del 1 al 5");
        // Leer la calificación del conductor
        Scanner sc = new Scanner(System.in);
        int calificacion = sc.nextInt();
        // Mostrar la calificación al pasajero
        System.out.println("El conductor le ha dado una calificación de: " + calificacion);
    }
}

// Clase que representa al administrador del sistema
public class Administrador {
    // Método que crea el pago del viaje
    public void crearPago() {
        // Crear un objeto de la clase Pago
        Pago pago = new Pago();
        // Asignar el pago al viaje del pasajero
        pasajero.setViaje().setPago(pago);
    }

    // Método que crea la solicitud del viaje
    public void crearSolicitudViaje() {
        // Crear un objeto de la clase Viaje
        Viaje viaje = new Viaje();
        // Pedir al pasajero que ingrese el origen y el destino del viaje
        System.out.println("Por favor, ingrese el origen del viaje");
        Scanner sc = new Scanner(System.in);
        String origen = sc.nextLine();
        System.out.println("Por favor, ingrese el destino del viaje");
        String destino = sc.nextLine();
        // Asignar el origen y el destino al viaje
        viaje.setOrigen(origen);
        viaje.setDestino(destino);
        // Asignar el viaje al pasajero
        pasajero.setViaje(viaje);
    }
}

// Clase que representa la cuenta del usuario
public class Cuenta {
    // Atributos que almacenan la contraseña, el nombre, el correo y el ID del usuario
    private String contraseña;
    private String nombre;
    private String correo;
    private int ID;

    // Método que devuelve la cuenta del usuario
    public Cuenta getCuenta() {
        return this;
    }
}

// Clase que representa el viaje del pasajero
public class Viaje {
    // Atributos que almacenan el destino, el origen y el objeto de la clase Pago del viaje
    private String destino;
    private String origen;
    private Pago pago;

    // Método que devuelve la cantidad de pasajeros del viaje
    public int getCantidad(int cantidad) {
        return cantidad;
    }

    // Método que devuelve el destino y el origen del viaje
    public String getDestinoOrigen(String destino, String origen) {
        return destino + " - " + origen;
    }

    // Método que devuelve el pago del viaje
    public Pago getPago() {
        return pago;
    }

    // Método que asigna el pago al viaje
    public void setPago(Pago pago) {
        this.pago = pago;
    }

    // Método que devuelve el destino del viaje
    public String getDestino() {
        return destino;
    }

    // Método que asigna el destino al viaje
    public void setDestino(String destino) {
        this.destino = destino;
    }

    // Método que devuelve el origen del viaje
    public String getOrigen() {
        return origen;
    }

    // Método que asigna el origen al viaje
    public void setOrigen(String origen) {
        this.origen = origen;
    }
}

// Clase que representa el pago del viaje
public class Pago {
    // Atributo que almacena el monto final del viaje
    private int montoFinal;

    // Método que calcula el monto del viaje en base al origen y el destino
    public void calcularMonto(String origen, String destino) {
        // Asignar un valor arbitrario al monto final
        montoFinal = 100;
    }

    // Método que devuelve el monto final del viaje
    public int getMontoFinal() {
        return montoFinal;
    }
}
}
}