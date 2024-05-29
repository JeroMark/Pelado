package Model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import Enum.MedioDePago;

public class Factura {
    private Cliente cliente;
    private MedioDePago medioDePago;
    private Date fecha;
    private double total;
    private int idReserva;

    public Factura(Cliente cliente, MedioDePago medioDePago,double total,int idReserva) {
        this.cliente=cliente;
        this.medioDePago = medioDePago;
        this.fecha = getFechaActual();
        this.total = total;
        this.idReserva=idReserva;
    }
    public String toString(){
        return ("Reserva: "+idReserva+" a nombre de: "+ cliente.getNombre()+""+cliente.getApellido()+". Pagado con: "+
        medioDePago+" el dia: "+fecha+" .Con un total de $"+total+" pesos.");
    }
    private Date getFechaActual(){
        LocalDateTime localDateTime = LocalDateTime.now();
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        return date;
    }
}
