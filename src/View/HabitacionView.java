package View;

import Model.Enum.TipoHabitacion;
public class HabitacionView {
    private int idHabitacion;
    private TipoHabitacion tipoDeHabitacion;
    private boolean despertador;
    private boolean tv;
    private boolean internet;
    private boolean minibar;
    private int cantidadDePersonas;

    public HabitacionView(int idHabitacion, TipoHabitacion tipoDeHabitacion, boolean despertador, boolean tv, boolean internet, boolean minibar, int cantidadDePersonas) {
        this.idHabitacion = idHabitacion;
        this.tipoDeHabitacion = tipoDeHabitacion;
        this.despertador = despertador;
        this.tv = tv;
        this.internet = internet;
        this.minibar = minibar;
        this.cantidadDePersonas = cantidadDePersonas;
    }


    @Override
    public String toString() {

        return "Habitacion " +
                "idHabitacion: " + idHabitacion +
                ", tipoDeHabitacion: " + tipoDeHabitacion +
                ", cantidadDePersonas: " + cantidadDePersonas + (tv ? " cuenta con tv":"")+(internet ? "Cuenta con internet":"")+
                (despertador ? " cuenta con despertador":"")+(minibar ? "cuenta con minibar":"");

    }
}
