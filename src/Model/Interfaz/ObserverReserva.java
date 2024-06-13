package Model.Interfaz;

import Model.Enum.EstadoReserva;

public interface ObserverReserva {
    void notificarObserver(int idReserva, EstadoReserva estadoReserva);
}
