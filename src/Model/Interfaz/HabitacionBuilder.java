package Model.Interfaz;

import Model.Enum.TipoHabitacion;

public interface HabitacionBuilder {
    void conDespertador();

    void cantidadDePersonas(int cantPersonas);

    void tipoDeHabitacion(TipoHabitacion tipo);

    void conTv();

    void conInternet();

    void conMiniBar();

}