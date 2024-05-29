package Model;

import java.util.Date;
import Enum.TipoHabitacion;

public class Filtro {
    private TipoHabitacion tipoDeHabitacion;
    private boolean despertador;
    private boolean tv;
    private boolean internet;
    private boolean minibar;
    private int cantidadDePersonas;
    private Date checkIn;
    private Date checkOut;
    public Filtro(FiltroBuilderImpl filtroBuilder){
      this.tipoDeHabitacion=filtroBuilder.getTipoHabitacion();
      this.despertador= filtroBuilder.getDespertador();
      this.tv= filtroBuilder.getTv();
      this.internet= filtroBuilder.getInternet();
      this.minibar= filtroBuilder.getMiniBar();
      this.cantidadDePersonas= filtroBuilder.getCantidadPersonas();
      this.checkIn=filtroBuilder.getCheckIn();
      this.checkOut=filtroBuilder.getCheckOut();
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
}
