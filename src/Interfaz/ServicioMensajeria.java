package Interfaz;

import Model.Factura;
import Enum.EstadoReserva;
import java.util.ArrayList;

public interface ServicioMensajeria {
    void enviarFactura(Factura f,String contacto);
    void notificarCambioDeEstado(EstadoReserva estadoReserva,int idReserva, String contacto);
    void enviarPromocion(ArrayList<String> contacto);
}
