package Model;

import Interfaz.ServicioMensajeria;
import Enum.EstadoReserva;
import java.util.ArrayList;

public class MensajeMail implements ServicioMensajeria {
    @Override
    public void enviarFactura(Factura f, String contacto) {
        System.out.println("**ENVIANDO POR MAIL**");
        System.out.println("Factura");
        System.out.println(f.toString());
    }


    @Override
    public void notificarCambioDeEstado(EstadoReserva estadoReserva, int idReserva, String contacto) {
        System.out.println("**ENVIANDO POR MAIL**");
        System.out.println("La reserva: "+idReserva+" cambio a: "+estadoReserva);
    }
    @Override
    public void enviarPromocion(ArrayList<String> contacto) {

    }
}
