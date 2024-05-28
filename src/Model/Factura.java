package Model;

import java.util.Date;

public class Factura {
    private String apellidoCliente;
    private String nombreCliente;
    private MedioDePago medioDePago;
    private Date fecha;
    private int dniCliente;
    private float total;

    public Factura(String apellidoCliente, String nombreCliente, MedioDePago medioDePago, Date fecha, int dniCliente, float total) {
        this.apellidoCliente = apellidoCliente;
        this.nombreCliente = nombreCliente;
        this.medioDePago = medioDePago;
        this.fecha = fecha;
        this.dniCliente = dniCliente;
        this.total = total;
    }
    public int getDatosCliente(){
        return dniCliente;
    }

    public MedioDePago getMedioDePago() {
        return medioDePago;
    }
    public float getTotal(){
        return total;
    }
    public Date getFecha(){
        return fecha;
    }
}
