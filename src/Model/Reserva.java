package Model;

import java.util.Date;
import Interfaz.StateReserva;
public class Reserva {
    private MedioDePago medioPago;
    private EstadoReserva estadoReserva;
    private StateReserva stateReserva;
    private Date fechaCreacionReserva;
    private float total;
    private observers?;

    public Reserva(MedioDePago medioPago, EstadoReserva estadoReserva, StateReserva stateReserva, Date fechaCreacionReserva, float total){
        this.medioPago = medioPago;
        this.estadoReserva = estadoReserva;
        this.stateReserva = stateReserva;
        this.fechaCreacionReserva = fechaCreacionReserva;
        this.total = total;
    }
    public float calcularPrecioFinal(){

    }
    private float calcularPrecioBase(){

    }
    private void cambiarState(){

    }
    public void setEstadoReserva(){

    }

    public EstadoReserva getEstadoReserva() {
        return estadoReserva;
    }
    public Date getFechaCreacionReserva(){
        return fechaCreacionReserva;
    }
    private void enviarFactura(){

    }
    private void notificarCambioDeEstado(){

    }
}
