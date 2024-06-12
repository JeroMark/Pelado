package Model.Interfaz;

import Model.Enum.EstadoReserva;

public interface Observer {
    void notificarObserver(int idReserva, EstadoReserva estadoReserva);
}
