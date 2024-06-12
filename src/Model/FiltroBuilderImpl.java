package Model;

import Model.Enum.TipoHabitacion;
import Model.Interfaz.HabitacionBuilder;

import java.util.Date;

public class FiltroBuilderImpl implements HabitacionBuilder {
    private TipoHabitacion tipoDeHabitacion;
    private boolean despertador;
    private boolean tv;
    private boolean internet;
    private boolean minibar;
    private int cantidadDePersonas;
    private Date checkIn;
    private Date checkOut;

    public FiltroBuilderImpl(int cantidadDePersonas, TipoHabitacion tipoHabitacion, Date checkIn, Date checkOut) {
        this.cantidadDePersonas = cantidadDePersonas;
        this.tipoDeHabitacion = tipoHabitacion;
        this.tv = false;
        this.despertador = false;
        this.minibar = false;
        this.internet = false;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
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
        return tipoDeHabitacion;
    }

    @Override
    public int getCantidadPersonas() {
        return cantidadDePersonas;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public Filtro build() {
        return new Filtro(this);
    }
}
