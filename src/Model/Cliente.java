package Model;

import java.util.ArrayList;

import Model.Enum.EstadoReserva;
import Model.Enum.TipoContacto;
import Model.Interfaz.ObserverReserva;
import Model.Interfaz.ServicioMensajeria;

public class Cliente extends Usuario implements ObserverReserva {
    private ArrayList<Tarjeta> tarjetas;

    public Cliente(int dni, String nombre, String apellido, String telefono, String mail, TipoContacto tipoContacto,
            String contrasenia) {
        super(dni, nombre, apellido, telefono, mail, tipoContacto, contrasenia);
    }

    public void agregarTarjeta(Tarjeta tarjeta) {
        tarjetas.add(tarjeta);
    }

    public ArrayList getTarjetas() {
        return tarjetas;
    }

    @Override
    public void notificarObserver(int idReserva, EstadoReserva estadoReserva) {
        if (tipoContacto == TipoContacto.Mail) {
            ServicioMensajeria notificador = new MensajeMail();
            notificador.notificarCambioDeEstado("Cliente",estadoReserva, idReserva, mail);
        } else if (tipoContacto == TipoContacto.WhatsApp) {
            ServicioMensajeria notificador = new MensajeWPP();
            notificador.notificarCambioDeEstado("Cliente",estadoReserva, idReserva, telefono);
        } else {
            ServicioMensajeria notificador = new MensajeSms();
            notificador.notificarCambioDeEstado("Cliente",estadoReserva, idReserva, telefono);
        }
    }
}
