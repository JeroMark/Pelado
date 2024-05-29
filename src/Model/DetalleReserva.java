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

    public boolean cumpleFecha(Date fecha) {
        return isDateBetween(fecha, checkIn, checkOut);

    }
    public double getPrecioHabitacion(){
        return habitacion.getPrecioBase();
    }
    private boolean isDateBetween(Date dateToCheck, Date startDate, Date endDate) {
        return (dateToCheck.equals(startDate) || dateToCheck.after(startDate)) &&
                (dateToCheck.equals(endDate) || dateToCheck.before(endDate));
    }
}
