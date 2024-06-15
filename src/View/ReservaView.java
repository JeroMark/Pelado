package View;

import Model.Cliente;
import Model.Enum.EstadoReserva;
import Model.Enum.MedioDePago;
import Model.Interfaz.StateReserva;

import java.util.Date;

public class ReservaView {
    private int id;
    private MedioDePago medioPago;
    private EstadoReserva estadoReserva;
    private StateReserva stateReserva;
    private final Date fechaCreacionReserva;
    private final Cliente cliente;

    public ReservaView(int id,Date fechaCreacionReserva, Cliente cliente, EstadoReserva estadoReserva, MedioDePago medioPago) {
        this.id=id;
        this.fechaCreacionReserva = fechaCreacionReserva;
        this.cliente = cliente;
        this.estadoReserva = estadoReserva;
        this.stateReserva = stateReserva;
        this.medioPago = medioPago;
    }

    @Override
    public String toString() {
        return "Reserva " +id+
                " medioPago: " + medioPago +
                ", estadoReserva: " + estadoReserva +
                ", fechaCreacionReserva: " + fechaCreacionReserva +
                ", cliente: " + cliente.getNombre()+", " +cliente.getApellido();

    }
}
