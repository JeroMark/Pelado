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
import Model.Interfaz.Observer;
import Model.Interfaz.ServicioMensajeria;
import Model.Interfaz.StateReserva;

public class Reserva {
    private static int id;
    private int idReserva;
    private MedioDePago medioPago;
    private EstadoReserva estadoReserva;
    private StateReserva stateReserva;
    private Date fechaCreacionReserva;
    private ArrayList<Observer> observers;
    private ArrayList<DetalleReserva> detalleReservas;
    private Cliente cliente;

    public Reserva(MedioDePago medioDePago, ArrayList<Habitacion> habitacions, Date checkIn, Date chekOut,
            Cliente cliente) {
        id++;
        this.idReserva = id;
        this.fechaCreacionReserva = getFechaActual();
        this.estadoReserva = EstadoReserva.Pendiente;
        this.cliente = cliente;
        this.medioPago = medioDePago;
        crearDetallesReserva(habitacions, checkIn, chekOut);
        observers.add(cliente);
        observers.add(AreaMarketing.getIntancia());
        observers.add(AreaContable.getInstance());
        setStateReserva(checkIn);
        cliente.notificarObserver(idReserva, estadoReserva);
    }

    public void agregarObserver(Observer o) {
        observers.add(o);
    }

    public double calcularPrecioFinal() {
        double precio = 0;
        for (DetalleReserva d : detalleReservas) {
            precio += d.getPrecioHabitacion();
        }
        return (stateReserva.calcularPrecioFinalReserva(precio));
    }

    private void setEstadoReserva(EstadoReserva estadoReserva) {
        this.estadoReserva = estadoReserva;
    }

    public EstadoReserva getEstadoReserva() {
        return estadoReserva;
    }

    public Date getFechaCreacionReserva() {
        return fechaCreacionReserva;
    }

    public Factura pagada() {
        setEstadoReserva(EstadoReserva.Pagada);
        notificarCambioDeEstado();
        Factura f = new Factura(cliente, medioPago, calcularPrecioFinal(), this.idReserva);
        enviarFactura(f);
        return f;

    }

    public void cancerlarReserva() {
        setEstadoReserva(EstadoReserva.Cancelada);
        cliente.notificarObserver(idReserva, estadoReserva);
    }

    public int getId() {
        return idReserva;
    }

    public void dias() {
        if (diferenciaHoras(getFechaActual()) > 24) {
            cancerlarReserva();
        }
    }

    //////////// METODOS
    //////////// PRIVADOS////////////////////////////////////////////////////////////////////////////////////////
    private void enviarFactura(Factura f) {
        if (cliente.getTipoContacto() == TipoContacto.Mail) {
            ServicioMensajeria notificador = new MensajeMail();
            notificador.enviarFactura(f, cliente.getMail());
        } else if (cliente.getTipoContacto() == TipoContacto.WhatsApp) {
            ServicioMensajeria notificador = new MensajeWPP();
            notificador.enviarFactura(f, cliente.getTelefono());
        } else {
            ServicioMensajeria notifiacor = new MensajeSms();
            notifiacor.enviarFactura(f, cliente.getTelefono());
        }
    }

    private void notificarCambioDeEstado() {
        for (Observer o : observers) {
            o.notificarObserver(idReserva, estadoReserva);
        }
    }

    private Date getFechaActual() {
        LocalDateTime localDateTime = LocalDateTime.now();
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        return date;
    }

    private void crearDetallesReserva(ArrayList<Habitacion> habitacions, Date chekIn, Date chekOut) {
        for (Habitacion h : habitacions) {
            detalleReservas.add(new DetalleReserva(h, idReserva, chekIn, chekOut));
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
