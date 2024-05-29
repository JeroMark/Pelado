package Interfaz;
import Enum.EstadoReserva;
public interface Observer {
    void notificarObserver(int idReserva, EstadoReserva estadoReserva);
}
