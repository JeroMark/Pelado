package Model;

import Model.Enum.TipoHabitacion;
import Model.Interfaz.HabitacionBuilder;

public class HabitacionBuilderImpl implements HabitacionBuilder {
    private boolean despertador;
    private boolean tv;
    private boolean internet;
    private boolean minibar;
    private int cantidadDePersonas;
    private TipoHabitacion tipoHabitacion;

    public HabitacionBuilderImpl(int cantidadDePersonas, TipoHabitacion tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
        this.cantidadDePersonas = cantidadDePersonas;
        this.tv = false;
        this.despertador = false;
        this.minibar = false;
        this.internet = false;
    }

    @Override
    public void conDespertador() {
        this.despertador = true;
    }

    @Override
    public void conTv() {
        this.tv = true;
    }

    @Override
    public void conInternet() {
        this.internet = true;
    }

    @Override
    public void conMiniBar() {
        this.minibar = true;
    }

    @Override
    public boolean getTv() {
        return tv;
    }

    @Override
    public boolean getInternet() {
        return internet;
    }

    @Override
    public boolean getMiniBar() {
        return minibar;
    }

    @Override
    public boolean getDespertador() {
        return despertador;
    }

    @Override
    public TipoHabitacion getTipoHabitacion() {
        return tipoHabitacion;
    }

    @Override
    public int getCantidadPersonas() {
        return cantidadDePersonas;
    }

    public Habitacion build() {
        return new Habitacion(this);
    }
}
