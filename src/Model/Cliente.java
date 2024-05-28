package Model;

import java.util.ArrayList;

public class Cliente extends Usuario{

    public Cliente (int dni, String nombre, String apellido,String telefono, String mail, TipoContacto tipoContacto){
        super(dni, nombre, apellido, telefono, ,mail, tipoContacto);
    }
    public void agregarTarjeta(){

    }
    public ArrayList getTarjetas(){

    }
}
