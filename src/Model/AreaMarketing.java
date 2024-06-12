package Model;

import Model.Enum.EstadoReserva;
import Model.Interfaz.Observer;
import Model.Interfaz.ServicioMensajeria;

public class AreaMarketing implements Observer {
    private String mail;
    private static AreaMarketing instancia;

    private AreaMarketing() {
    }

    public static AreaMarketing getInstancia() {
        if (instancia == null) {
            instancia = new AreaMarketing();
        }
        return instancia;
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public void notificarObserver(int idReserva, EstadoReserva estadoReserva) {
        ServicioMensajeria notificador = new MensajeMail();
        notificador.notificarCambioDeEstado(estadoReserva, idReserva, mail);
    }
}
