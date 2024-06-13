package Model;

import java.util.ArrayList;
import java.util.Date;

import Model.Enum.ExtrasHabitacion;
import Model.Enum.TipoHabitacion;
import View.HabitacionView;

public class Habitacion {
    private static int id;
    private int idHabitacion;
    private TipoHabitacion tipoDeHabitacion;
    private boolean despertador;
    private boolean tv;
    private boolean internet;
    private boolean minibar;
    private int cantidadDePersonas;
    private ArrayList<DetalleReserva> detallesReserva;

    public Habitacion() {
        id++;
        this.idHabitacion = id;
        this.tipoDeHabitacion = null;
        this.despertador = false;
        this.tv = false;
        this.internet = false;
        this.minibar = false;
        this.cantidadDePersonas = 0;
        detallesReserva=new ArrayList<>();
    }
    public boolean CumpleFiltro(Filtro f) {
        boolean fecha = true;
        for (DetalleReserva d : detallesReserva) {
            if (!d.libreEnRango(f.getCheckIn(),f.getCheckOut())) {
                fecha = false;
            }
        }
        return (f.tieneMinibar() == minibar && f.tieneTv() == tv && f.tieneInternet() == internet &&
                f.tieneDespertador() == despertador && f.getTipoDeHabitacion() == tipoDeHabitacion
                && f.getCantidadDePersonas() == cantidadDePersonas
                && fecha);
    }
    public boolean reporte(Date fechaInicio, Date fechaFin){
        for(DetalleReserva d:detallesReserva) {
            if(!d.libreEnRango(fechaInicio,fechaFin)){
                return false;
            }
        }return true;
    }
    public int getId() {
        return id;
    }
    public double getPrecioBase() {
        ArrayList<ExtrasHabitacion> extras = new ArrayList<>();
        if (despertador) {
            extras.add(ExtrasHabitacion.Despertador);
        } else if (tv) {
            extras.add(ExtrasHabitacion.Tv);
        } else if (minibar) {
            extras.add(ExtrasHabitacion.Minibar);
        } else if (internet) {
            extras.add(ExtrasHabitacion.Internet);
        } else if (TipoHabitacion.Suite == tipoDeHabitacion) {
            extras.add(ExtrasHabitacion.Suite);
        } else
            extras.add(ExtrasHabitacion.Estandar);
        return Extra.getInstancia().obtenerValores(extras, cantidadDePersonas);
    }
    public HabitacionView habitacionToView() {
        return new HabitacionView(this.id,this.tipoDeHabitacion,this.despertador,this.tv,this.internet,this.minibar,this.cantidadDePersonas);
    }

    public void setTipoDeHabitacion(TipoHabitacion tipoDeHabitacion) {
        this.tipoDeHabitacion = tipoDeHabitacion;
    }

    public void setDespertador() {
        this.despertador = true;
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

    public void setDetallesReserva(DetalleReserva detallesReserva) {
        this.detallesReserva.add(detallesReserva);
    }

    public void setCantidadDePersonas(int cantidadDePersonas) {
        this.cantidadDePersonas = cantidadDePersonas;
    }
}
