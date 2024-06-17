package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;

import Model.Enum.EstadoReserva;
import Model.Enum.MedioDePago;
import Model.Enum.TipoContacto;
import Model.Interfaz.ObserverReserva;
import Model.Interfaz.ServicioMensajeria;
import Model.Interfaz.StateReserva;
import View.ReservaView;

public class Reserva {
    private static int id;
    private final int idReserva;
    private MedioDePago medioPago;
    private EstadoReserva estadoReserva;
    private StateReserva stateReserva;
    private final Date fechaCreacionReserva;
    private ArrayList<DetalleReserva> detalleReservas;
    private final Cliente cliente;
    private final ReservaObserverAdmin adminObserver;

    public Reserva(MedioDePago medioDePago, ArrayList<Habitacion> habitacions, Date checkIn, Date checkOut,
            Cliente cliente) {
        id++;
        this.idReserva = id;
        this.fechaCreacionReserva = getFechaActual();
        this.estadoReserva = EstadoReserva.Pendiente;
        this.cliente = cliente;
        this.medioPago = medioDePago;
        this.adminObserver =new ReservaObserverAdmin();
        adminObserver.agregarObserver(this.cliente);
        detalleReservas=new ArrayList<>();
        crearDetallesReserva(habitacions, checkIn, checkOut);
        setStateReserva(checkIn);
        cliente.notificarObserver(idReserva, estadoReserva);
    }
    public void agregarObserver(ObserverReserva o) {
       adminObserver.agregarObserver(o);
    }
    public void eliminarObserver(ObserverReserva o){
        adminObserver.eliminarObserver(o);
    }
    public Date getFechaCreacionReserva() {
        return fechaCreacionReserva;
    }
    public EstadoReserva getEstadoReserva(){return estadoReserva;}
    public int getId() {
        return idReserva;
    }
    public double calcularPrecioFinal() {
        double precio = 0;
        for (DetalleReserva d : detalleReservas) {
            precio += d.getPrecioHabitacion();
        }
        return (stateReserva.calcularPrecioFinalReserva(precio));
    }
    public void pagada() {
        setEstadoReserva(EstadoReserva.Pagada);
        adminObserver.informarObservers(this.idReserva,this.estadoReserva);
        Factura f = new Factura(cliente, medioPago, calcularPrecioFinal(), this.idReserva);
        f.enviarFacturaCliente();
    }
    public void cancelarReserva() {
        setEstadoReserva(EstadoReserva.Cancelada);
        cliente.notificarObserver(idReserva, estadoReserva);
    }
    public void verificarVencimiento() {
        if (diferenciaHoras(getFechaActual()) > 24) {
            cancelarReserva();
        }
    }
    public ReservaView toView(){
        return new ReservaView(this.idReserva,this.fechaCreacionReserva,this.cliente,this.estadoReserva,this.medioPago);
    }
    //////////// METODOS
    //////////// PRIVADOS////////////////////////////////////////////////////////////////////////////////////////

    private void setEstadoReserva(EstadoReserva estadoReserva) {
        this.estadoReserva = estadoReserva;
    }
    private Date getFechaActual() {
        LocalDateTime localDateTime = LocalDateTime.now();
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        return date;
    }
    private void crearDetallesReserva(ArrayList<Habitacion> habitacions, Date checkIn, Date checkOut) {
        for (Habitacion h : habitacions) {
            DetalleReserva detalle= new DetalleReserva(h, idReserva, checkIn, checkOut);
            detalleReservas.add(detalle);
            h.setDetallesReserva(detalle);
        }
    }
    private void setStateReserva(Date checkIn) {
        int diferencia = DiferenciaDias(checkIn);
        if (diferencia < 15) {
            stateReserva = new StatePrecioDescuento();
        } else if (diferencia > 60) {
            stateReserva = new StatePrecioAumento();
        } else {
            stateReserva = new StatePrecioBase();
        }
    }
    private int DiferenciaDias(Date FechaCheckin) {
        LocalDate inicio = this.fechaCreacionReserva.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate fin = FechaCheckin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return (int) ChronoUnit.DAYS.between(inicio, fin);
    }
    private long diferenciaHoras(Date fin) {
        long diferenciaEnMilisegundos = Math.abs(fin.getTime() - fechaCreacionReserva.getTime());
        long diferenciaEnHoras = diferenciaEnMilisegundos / (60 * 60 * 1000);
        return diferenciaEnHoras;
    }
}
