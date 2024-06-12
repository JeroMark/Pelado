package Model;

import java.util.ArrayList;

import Model.Enum.EstadoReserva;
import Model.Enum.TipoContacto;
import Model.Interfaz.Observer;
import Model.Interfaz.ServicioMensajeria;

public class Cliente extends Usuario implements Observer {
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
            notificador.notificarCambioDeEstado(estadoReserva, idReserva, mail);
        } else if (tipoContacto == TipoContacto.WhatsApp) {
            ServicioMensajeria notificador = new MensajeWPP();
            notificador.notificarCambioDeEstado(estadoReserva, idReserva, telefono);
        } else {
            ServicioMensajeria notificador = new MensajeSms();
            notificador.notificarCambioDeEstado(estadoReserva, idReserva, telefono);
        }
    }
}
