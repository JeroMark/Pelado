package Interfaz;

import Enum.TipoHabitacion;

public interface HabitacionBuilder {
    void conDespertador();
    void conTv();
    void conInternet();
    void conMiniBar();
    boolean getTv();
    boolean getInternet();
    boolean getMiniBar();
    boolean getDespertador();
    TipoHabitacion getTipoHabitacion();
    int getCantidadPersonas();
}
