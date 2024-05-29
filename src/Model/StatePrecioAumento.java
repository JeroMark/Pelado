package Model;

import Interfaz.StateReserva;

public class StatePrecioAumento implements StateReserva {

    public float calcularPrecioFinalReserva(float precioBase) {
        return precioBase * 1.2f;
    }
}
