package Model;

import Interfaz.Observer;
import Enum.EstadoReserva;
import Interfaz.ServicioMensajeria;

public class AreaContable implements Observer {
    private String mail;
    private static AreaContable instancia;

    private AreaContable(){
    }

    public static AreaContable getInstance(){
        if(instancia==null){
            instancia=new AreaContable();
        }
        return instancia;
    }
    public String getMail(){
        return this.mail;
    }
    public void SetMail(String mail){
        this.mail=mail;
    }

    @Override
    public void notificarObserver(int idReserva, EstadoReserva estadoReserva) {
        ServicioMensajeria notificador=new MensajeMail();
        notificador.notificarCambioDeEstado(estadoReserva,idReserva,mail);
    }
}
