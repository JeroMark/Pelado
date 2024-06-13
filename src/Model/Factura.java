package Model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import Model.Enum.MedioDePago;
import Model.Enum.TipoContacto;
import Model.Interfaz.ServicioMensajeria;

public class Factura {
    private final Cliente cliente;
    private final MedioDePago medioDePago;
    private final Date fecha;
    private final double total;
    private final int idReserva;

    public Factura(Cliente cliente, MedioDePago medioDePago, double total, int idReserva) {
        this.cliente = cliente;
        this.medioDePago = medioDePago;
        this.fecha = getFechaActual();
        this.total = total;
        this.idReserva = idReserva;
    }

    public String toString() {
        return ("Reserva: " + idReserva + " a nombre de: " + cliente.getNombre() + "" + cliente.getApellido()
                + ". Pagado con: " +
                medioDePago + " el dia: " + fecha + " .Con un total de $" + total + " pesos.");
    }
    private Date getFechaActual() {
        LocalDateTime localDateTime = LocalDateTime.now();
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        return date;
    }
    public void enviarFacturaCliente() {
        if (cliente.getTipoContacto() == TipoContacto.Mail) {
            ServicioMensajeria notificador = new MensajeMail();
            notificador.enviarFactura(this, cliente.getMail());
        } else if (cliente.getTipoContacto() == TipoContacto.WhatsApp) {
            ServicioMensajeria notificador = new MensajeWPP();
            notificador.enviarFactura(this, cliente.getTelefono());
        } else {
            ServicioMensajeria notifiacor = new MensajeSms();
            notifiacor.enviarFactura(this, cliente.getTelefono());
        }
    }
}
