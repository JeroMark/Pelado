package Model;

import Model.Enum.EstadoReserva;
import Model.Interfaz.ServicioMensajeria;

import java.util.ArrayList;

public class MensajeWPP implements ServicioMensajeria {
    @Override
    public void enviarFactura(Factura f, String contacto) {
        System.out.println("**ENVIANDO POR WHATSAPP**");
        System.out.println("Factura");
        System.out.println(f.toString());
    }

    @Override
    public void notificarCambioDeEstado(EstadoReserva estadoReserva, int idReserva, String contacto) {
        System.out.println("**ENVIANDO POR WHATSAPP**");
        System.out.println("La reserva: " + idReserva + " cambio a: " + estadoReserva);
    }

    @Override
    public void enviarPromocion(ArrayList<String> contacto) {

    }
}