package Model;

import java.util.Date;
import Interfaz.StateReserva;

public class Reserva {
    private MedioDePago medioPago;
    private EstadoReserva estadoReserva;
    private StateReserva stateReserva;
    private Date fechaCreacionReserva;
    private float precioBase;
    private observers?;

    public Reserva(MedioDePago medioPago, EstadoReserva estadoReserva, StateReserva stateReserva, Date fechaCreacionReserva, float precioBase){
        this.medioPago = medioPago;
        this.estadoReserva = estadoReserva;
        this.stateReserva = stateReserva;
        this.fechaCreacionReserva = fechaCreacionReserva;
        this.precioBase = precioBase;
    }
    public float calcularPrecioFinal(){

    }
    private float calcularPrecioBase(){

    }
    private void setStateReserva(StateReserva stateReserva){
        this.stateReserva=stateReserva;
    }
    public void setEstadoReserva(EstadoReserva estadoReserva){
        this.estadoReserva=estadoReserva;
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
    public float calcularPrecioFinalReserva() {
        return stateReserva.calcularPrecioFinalReserva(precioBase);
    }

    //Métodos para cambiar el estado de la reserva

    public void aplicarDescuento() {
        this.setStateReserva(new StatePrecioDescuento());
    }

    public void aplicarPrecioBase() {
        this.setStateReserva(new StatePrecioBase());
    }

    public void aplicarAumento() {
        this.setStateReserva(new StatePrecioAumento());
    }
}
