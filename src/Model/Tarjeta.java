package Model;

import java.util.Date;
import Enum.TipoTarjeta;

public class Tarjeta {
    private int numeroTarjeta;
    private Date vencimiento;
    private int codigoSeguridad;
    private String nombreTitular;
    private TipoTarjeta tipoTarjeta;

    public Tarjeta(int numeroTarjeta, Date vencimiento, int codigoSeguridad, String nombreTitular, TipoTarjeta tipoTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
        this.vencimiento = vencimiento;
        this.codigoSeguridad = codigoSeguridad;
        this.nombreTitular = nombreTitular;
        this.tipoTarjeta = tipoTarjeta;
    }

    public TipoTarjeta getTipoTarjeta() {
        return tipoTarjeta;
    }
}
