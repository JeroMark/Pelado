package Model.Externos;

import Model.Enum.EstadoReserva;
import Model.Interfaz.ObserverReserva;
import Model.Interfaz.ServicioMensajeria;
import Model.MensajeMail;

import java.util.ArrayList;

public class AreaMarketing implements ObserverReserva {
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
    public void enviarPromociones(ArrayList<String> contactos){
        ServicioMensajeria notificador=new MensajeMail();
        notificador.enviarPromocion(contactos,"promocion");
    }
    @Override
    public void notificarObserver(int idReserva, EstadoReserva estadoReserva) {
        ServicioMensajeria notificador = new MensajeMail();
        notificador.notificarCambioDeEstado("Area de marketing",estadoReserva, idReserva, mail);
    }
}
