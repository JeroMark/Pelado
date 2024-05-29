package Model;

import java.util.Date;

public class Factura {
    private Cliente cliente;
    private MedioDePago medioDePago;
    private Date fecha;
    private double total;
    private int idReserva;

    public Factura(String apellidoCliente, String nombreCliente, MedioDePago medioDePago, Date fecha, int dniCliente, float total) {
        this.apellidoCliente = apellidoCliente;
        this.nombreCliente = nombreCliente;
        this.medioDePago = medioDePago;
        this.fecha = fecha;
        this.dniCliente = dniCliente;
        this.total = total;
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
