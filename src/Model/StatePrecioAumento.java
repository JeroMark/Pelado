package Model;

import Interfaz.StateReserva;

public class StatePrecioAumento implements StateReserva {

    private double porcentaje;
    public StatePrecioAumento(){
        this.porcentaje=1.2;
    }
    public double calcularPrecioFinalReserva(double precioBase) {
        return precioBase * porcentaje;
    }

    @Override
    public void cambiarValor(double nuevoValor) {
        this.porcentaje=nuevoValor;
    }
}
