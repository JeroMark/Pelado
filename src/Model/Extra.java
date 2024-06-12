package Model;

import java.util.ArrayList;
import java.util.HashMap;

import Model.Enum.ExtrasHabitacion;

public class Extra {
    private static Extra instancia;
    private HashMap<ExtrasHabitacion, Double> valores = new HashMap<>();

    private Extra() {
        valores.put(ExtrasHabitacion.Tv, 2000.0);
        valores.put(ExtrasHabitacion.Despertador, 1000.0);
        valores.put(ExtrasHabitacion.Internet, 2000.0);
        valores.put(ExtrasHabitacion.Minibar, 4000.0);
        valores.put(ExtrasHabitacion.Suite, 80000.0);
        valores.put(ExtrasHabitacion.Estandar, 50000.0);
        valores.put(ExtrasHabitacion.PrecioPorPersona, 0.05);
    }

    public static Extra getInstancia() {
        if (instancia == null) {
            instancia = new Extra();
        }
        return instancia;
    }

    public void actualizaValor(ExtrasHabitacion extra, double nuevoValor) {
        valores.put(extra, nuevoValor);
    }

    public double obtenerValor(ExtrasHabitacion extra) {
        return valores.get(extra);
    }

    public double obtenerValores(ArrayList<ExtrasHabitacion> extras, int cantPersonas) {
        double valor = 0;
        for (ExtrasHabitacion e : extras) {
            valor += obtenerValor(e);
        }
        valor *= 1 + (cantPersonas * obtenerValor(ExtrasHabitacion.PrecioPorPersona));
        return valor;
    }
}
