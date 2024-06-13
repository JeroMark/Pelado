package Model;

import java.util.Date;

public class DetalleReserva {
    private Date checkIn;
    private Date checkOut;
    private Habitacion habitacion;
    private int idReserva;

    public DetalleReserva(Habitacion habitacion, int idReserva, Date checkIn, Date checkOut) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.habitacion=habitacion;
        this.idReserva=idReserva;
    }

    public boolean libreEnRango(Date fechaInicio, Date fechaFin) {
        //Si el rango de fecha querido se superpone con una reserva devuelve false
        return (fechaInicio.after(checkOut)||fechaFin.before(checkIn));  //coca
    }
    public double getPrecioHabitacion(){
        return habitacion.getPrecioBase();
    }
    private boolean isDateBetween(Date dateToCheck, Date startDate, Date endDate) {
        return (dateToCheck.equals(startDate) || dateToCheck.after(startDate)) &&
                (dateToCheck.equals(endDate) || dateToCheck.before(endDate));
    }
}
