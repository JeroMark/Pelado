package Model;

import Interfaz.StateReserva;

public class StatePrecioDescuento implements StateReserva {
    private double porcentaje;
    public StatePrecioDescuento(){
        this.porcentaje=0.85;
    }

    public double calcularPrecioFinalReserva(double precioBase) {
        return precioBase * porcentaje;
    }

    @Override
    public void cambiarValor(double nuevoValor) {
        this.porcentaje=nuevoValor;
    }
}




