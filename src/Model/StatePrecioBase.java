package Model;

import Interfaz.StateReserva;

public class StatePrecioBase implements StateReserva {

    public float calcularPrecioFinalReserva(float precioBase) {
        return precioBase;
    }
}
