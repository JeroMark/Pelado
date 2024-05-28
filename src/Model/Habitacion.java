package Model;

import java.util.Date;

public class Habitacion {
    private int idHabitacion;
    private TipoHabitacion tipoDeHabitacion;
    private boolean despertador;
    private boolean tv;
    private boolean internet;
    private boolean minibar;
    private int cantidadDePersonas;


    public Habitacion(TipoHabitacion tipoDeHabitacion, int cantidadDePersonas){
        this.tipoDeHabitacion=tipoDeHabitacion;
        this.despertador=false;
        this.tv=false;
        this.internet=false;
        this.minibar=false;
        this.cantidadDePersonas=cantidadDePersonas;
    }

    public boolean CumpleFiltro(Filtro caracteristicasHabitacion){
        return
    }

    public float getPrecioBase(){

    }
    private boolean FechaDisponibles(Date checkIn, Date checkOut){

    }
}
