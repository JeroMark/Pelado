package Model;

import Model.Enum.EstadoReserva;
import Model.Interfaz.Observer;
import Model.Interfaz.ServicioMensajeria;

public class AreaMarketing implements Observer {
    private String mail;
    private static AreaMarketing intancia;

    private AreaMarketing() {
    }

    public static AreaMarketing getIntancia() {
        if (intancia == null) {
            intancia = new AreaMarketing();
        }
        return intancia;
    }

    public String getMail() {
        return mail;
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
