package Model;

import Model.Enum.EstadoReserva;
import Model.Interfaz.ServicioMensajeria;

import java.util.ArrayList;

public class MensajeMail implements ServicioMensajeria {
    @Override
    public void enviarFactura(Factura f, String contacto) {
        System.out.println("**ENVIANDO POR MAIL**");
        System.out.println("Factura");
        System.out.println(f.toString());
        System.out.println("------------------------------------------------------------------------------------");
    }

    @Override
    public void notificarCambioDeEstado(String aquien,EstadoReserva estadoReserva, int idReserva, String contacto) {
        System.out.println("**ENVIANDO POR MAIL**");
        System.out.println("Enviando a: "+aquien);
        System.out.println( "La reserva: " + idReserva + " cambio a: " + estadoReserva);
        System.out.println("------------------------------------------------------------------------------------");
    }

    @Override
    public void enviarPromocion(ArrayList<String> contacto,String mensaje) {

    }
}
