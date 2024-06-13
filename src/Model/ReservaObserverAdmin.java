package Model;

import Model.Enum.EstadoReserva;
import Model.Externos.AreaContable;
import Model.Externos.AreaMarketing;
import Model.Interfaz.ObserverReserva;

import java.util.ArrayList;

public class ReservaObserverAdmin {
    ArrayList<ObserverReserva> observers;
    public ReservaObserverAdmin(){
        observers=new ArrayList<>();
        observers.add(AreaMarketing.getInstancia());
        observers.add(AreaContable.getInstancia());
    }
    public void agregarObserver(ObserverReserva obv){
        observers.add(obv);
    }
    public void eliminarObserver(ObserverReserva obv){
        observers.remove(obv);
    }
    public void informarObservers(int idReserva, EstadoReserva estado){
        for (ObserverReserva j:observers){
            j.notificarObserver(idReserva,estado);
        }
    }
}
