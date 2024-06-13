package Model.Externos;

import Model.Enum.EstadoReserva;
import Model.Interfaz.ObserverReserva;
import Model.Interfaz.ServicioMensajeria;
import Model.MensajeMail;

public class AreaContable implements ObserverReserva {
    private String mail;
    private static AreaContable instancia;

    private AreaContable() {
    }

    public static AreaContable getInstancia() {
        if (instancia == null) {
            instancia = new AreaContable();
        }
        return instancia;
    }

    public String getMail() {
        return this.mail;
    }

    public void SetMail(String mail) {
        this.mail = mail;
    }

    @Override
    public void notificarObserver(int idReserva, EstadoReserva estadoReserva) {
        ServicioMensajeria notificador = new MensajeMail();
        notificador.notificarCambioDeEstado(estadoReserva, idReserva, mail);
    }
}
