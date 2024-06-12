package Model;

import Model.Enum.TipoContacto;

public class Gerente extends Usuario {

    public Gerente(int dni, String nombre, String apellido, String telefono, String mail, TipoContacto tipoContacto,
            String contrasenia) {
        super(dni, nombre, apellido, telefono, mail, tipoContacto, contrasenia);
    }
}
