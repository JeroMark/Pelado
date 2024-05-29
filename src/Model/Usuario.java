package Model;

public abstract class Usuario {
    protected int dni;
    protected String nombre;
    protected String apellido;
    protected String telefono;
    protected String mail;
    protected TipoContacto tipoContacto;
    protected String contrasenia;
    public Usuario(int dni, String nombre, String apellido,String telefono, String mail, TipoContacto tipoContacto,String contrasenia){
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.mail = mail;
        this.tipoContacto = tipoContacto;
        this.contrasenia=contrasenia;
    }
    public int getDni(){
        return dni;
    }
    public TipoContacto getTipoContacto(){
        return tipoContacto;
    }
    public String getTelefono(){
        return telefono;
    }
    public String getMail(){
        return mail;
    }
    public String getNombre(){
        return nombre;
    }
    public String getApellido(){
        return apellido;
    }
    public String getContrasenia(){return contrasenia;}
}
