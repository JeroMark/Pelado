package Model;

import Model.Enum.TipoHabitacion;
import Model.Interfaz.HabitacionBuilder;

public class HabitacionBuilderImpl implements HabitacionBuilder {
    private Habitacion habitacion;

    public HabitacionBuilderImpl() {
        reset();
    }
    public void reset(){
        this.habitacion=new Habitacion();
    }
    @Override
    public void conDespertador() {
        habitacion.setDespertador();
    }

    @Override
    public void cantidadDePersonas(int cantPersonas) {
        habitacion.setCantidadDePersonas(cantPersonas);
    }

    @Override
    public void tipoDeHabitacion(TipoHabitacion tipo) {
        habitacion.setTipoDeHabitacion(tipo);
    }

    @Override
    public void conTv() {
        habitacion.setTv();
    }

    @Override
    public void conInternet() {
       habitacion.setInternet();
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
        Habitacion devolver=this.habitacion;
        reset();
        return devolver;
    }
}
