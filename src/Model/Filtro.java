package Model;

import java.util.Date;

import Model.Enum.TipoHabitacion;
import Model.Interfaz.HabitacionBuilder;

public class Filtro {
    private TipoHabitacion tipoDeHabitacion;
    private boolean despertador;
    private boolean tv;
    private boolean internet;
    private boolean minibar;
    private int cantidadDePersonas;
    private Date checkIn;
    private Date checkOut;

    public Filtro() {
        this.tipoDeHabitacion = null;
        this.despertador = false;
        this.tv = false;
        this.internet = false;
        this.minibar = false;
        this.cantidadDePersonas = 0;
        this.checkIn = null;
        this.checkOut = null;
    }

    public TipoHabitacion getTipoDeHabitacion() {
        return tipoDeHabitacion;
    }

    public boolean tieneDespertador() {
        return despertador;
    }

    public boolean tieneTv() {
        return tv;
    }

    public boolean tieneInternet() {
        return internet;
    }

    public boolean tieneMinibar() {
        return minibar;
    }

    public int getCantidadDePersonas() {
        return cantidadDePersonas;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setDespertador() {
        this.despertador = true;
    }

    public void setTipoDeHabitacion(TipoHabitacion tipoDeHabitacion) {
        this.tipoDeHabitacion = tipoDeHabitacion;
    }

    public void setTv() {
        this.tv = true;
    }

    public void setInternet() {
        this.internet = true;
    }

    public void setMinibar() {
        this.minibar = true;
    }

    public void setCantidadDePersonas(int cantidadDePersonas) {
        this.cantidadDePersonas = cantidadDePersonas;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }
}
