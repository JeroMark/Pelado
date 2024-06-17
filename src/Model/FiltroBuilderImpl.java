package Model;

import Model.Enum.TipoHabitacion;
import Model.Interfaz.HabitacionBuilder;

import java.util.Date;

public class FiltroBuilderImpl implements HabitacionBuilder {
    private Filtro filtro;

    public FiltroBuilderImpl() {
        reset();
    }
    private void reset(){
        this.filtro=new Filtro();
    }

    @Override
    public void conDespertador() {
        filtro.setDespertador();
    }

    @Override
    public void cantidadDePersonas(int cantPersonas) {
        filtro.setCantidadDePersonas(cantPersonas);
    }

    @Override
    public void tipoDeHabitacion(TipoHabitacion tipo) {
        filtro.setTipoDeHabitacion(tipo);
    }

    @Override
    public void conTv() {
        filtro.setTv();
    }

    @Override
    public void conInternet() {
        filtro.setInternet();
    }

    @Override
    public void conMiniBar() {
        filtro.setMinibar();
    }
    public void setCheckIn(Date checkIn) {
        filtro.setCheckIn(checkIn);
    }

    public void setCheckOut(Date checkOut) {
        filtro.setCheckOut(checkOut);
    }
    public Filtro build(){
        Filtro devolver=this.filtro;
        reset();
        return devolver;
    }
}
