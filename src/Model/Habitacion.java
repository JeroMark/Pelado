package Model;

import java.util.ArrayList;
import Enum.TipoHabitacion;
import Enum.ExtrasHabitacion;
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
    private ArrayList <DetalleReserva> detallesReserva;


    public Habitacion(HabitacionBuilderImpl builder){
        id++;
        this.idHabitacion=id;
        this.tipoDeHabitacion=builder.getTipoHabitacion();
        this.despertador=builder.getDespertador();
        this.tv=builder.getTv();
        this.internet=builder.getInternet();
        this.minibar=builder.getMiniBar();
        this.cantidadDePersonas=builder.getCantidadPersonas();
    }

    public boolean CumpleFiltro(Filtro f){
        boolean fecha=true;
        for(DetalleReserva d:detallesReserva){
            if(!d.cumpleFecha(f.getCheckIn())){
                fecha=false;
            }
        }
        return (f.tieneMinibar()==minibar && f.tieneTv()==tv && f.tieneInternet()==internet &&
                f.tieneDespertador()==despertador && f.getTipoDeHabitacion()==tipoDeHabitacion && f.getCantidadDePersonas()==cantidadDePersonas
                && fecha);
    }
    public int getId(){
        return id;
    }
    public double getPrecioBase(){
        ArrayList<ExtrasHabitacion> extras=new ArrayList<>();
        if(despertador){
            extras.add(ExtrasHabitacion.Despertador);
        }else if(tv){
            extras.add(ExtrasHabitacion.Tv);
        }else if(minibar){
            extras.add(ExtrasHabitacion.Minibar);
        }else if(internet){
            extras.add(ExtrasHabitacion.Internet);
        } else if (TipoHabitacion.Suite==tipoDeHabitacion) {
            extras.add(ExtrasHabitacion.Suite);
        }else extras.add(ExtrasHabitacion.Estandar);
        return Extra.getInstancia().obtenerValores(extras,cantidadDePersonas);
    }
    public HabitacionView habitacionToView(){
        return new HabitacionView();
    }
}
