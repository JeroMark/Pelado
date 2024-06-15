package View;

import Model.Enum.TipoContacto;

public class ClienteView {
    private int dni;
    private String nombre;
    private String apellido;
    private String telefono;
    private String mail;
    private TipoContacto tipoContacto;

    public ClienteView(int dni, TipoContacto tipoContacto, String mail, String telefono, String apellido, String nombre) {
        this.dni = dni;
        this.tipoContacto = tipoContacto;
        this.mail = mail;
        this.telefono = telefono;
        this.apellido = apellido;
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Cliente " +
                "dni: " + dni +
                ", nombre: '" + nombre + '\'' +
                ", apellido: '" + apellido + '\'' +
                ", telefono: '" + telefono + '\'' +
                ", mail: '" + mail + '\'' +
                ", tipoContacto: " + tipoContacto;
    }
}

