package Model.Interfaz;

import Model.Factura;
import Model.Enum.EstadoReserva;

import java.util.ArrayList;

public interface ServicioMensajeria {
    void enviarFactura(Factura f, String contacto);

    void notificarCambioDeEstado(String aquien,EstadoReserva estadoReserva, int idReserva, String contacto);

    void enviarPromocion(ArrayList<String> contacto);
}
