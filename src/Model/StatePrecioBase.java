package Model;

import Model.Interfaz.StateReserva;

public class StatePrecioBase implements StateReserva {
    private double porcentaje;

    public StatePrecioBase() {
        porcentaje = 1;
    }

    public double calcularPrecioFinalReserva(double precioBase) {
        return precioBase * porcentaje;
    }

    @Override
    public void cambiarValor(double nuevoValor) {
        porcentaje = nuevoValor;
    }
}
