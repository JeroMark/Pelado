package Model;

import Interfaz.StateReserva;

public class StatePrecioDescuento implements StateReserva {

    public float calcularPrecioFinalReserva(float precioBase) {
        return precioBase * 0.85f;
    }
}
