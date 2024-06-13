package Controller;

import Model.*;
import Model.Enum.*;
import Controller.Exception.BusquedaException;
import Controller.Exception.LogginException;
import Controller.Exception.ReservaException;
import Controller.Exception.SinPermisoException;
import View.HabitacionView;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Controlador {
    private static Controlador instancia;
    private final ArrayList<Gerente> gerentes;
    private final ArrayList<Cliente> clientes;
    private final ArrayList<Reserva> reservas;
    private final ArrayList<Habitacion> habitaciones;
    private final ArrayList<Filtro> filtros;
    private static Usuario user;

    private Controlador() {
        gerentes = new ArrayList<>();
        clientes = new ArrayList<>();
        reservas = new ArrayList<>();
        habitaciones = new ArrayList<>();
        filtros = new ArrayList<>();
        inicio();
    }
    public static Controlador getInstancia() {
        if (instancia == null) {
            instancia = new Controlador();
        }
        return instancia;
    }
    public void registrarGerente(int dni, String nombre, String apellido, String telefono, String mail,
            TipoContacto contacto, String contrasenia) {
        Gerente user = new Gerente(dni, nombre, apellido, telefono, mail, contacto, contrasenia);
        gerentes.add(user);
    }
    public void registrarCliente(int dni, String nombre, String apellido, String telefono, String mail,
            TipoContacto contacto, String contrasenia) {
        Cliente user = new Cliente(dni, nombre, apellido, telefono, mail, contacto, contrasenia);
        clientes.add(user);
    }
    public void iniciarSesion(int dni, String contrasenia) throws LogginException {
        Usuario u = buscarUsurio(dni);
        if (u == null) {
            throw new LogginException("El usuario no existe");
        }
        if (u != null) {
            if (u.getContrasenia() != contrasenia) {
                throw new LogginException("La contraseñia es incorrecta");
            } else
                user = u;
        }
    }
    public void crearHabitacion(HabitacionBuilderImpl hab) throws SinPermisoException {
        if (user instanceof Cliente) {
            throw new SinPermisoException("No tiene permiso para crear una habitacion");
        } else {
            Habitacion habitacion = hab.build();
            habitaciones.add(habitacion);
        }
    }
    public ArrayList<HabitacionView> buscarHabitacion(Filtro filtro) throws BusquedaException {
        ArrayList<HabitacionView> habitacionesCumplen = new ArrayList<>();
        for (Habitacion h : habitaciones) {
            if (h.CumpleFiltro(filtro)) {
                habitacionesCumplen.add(h.habitacionToView());
            }
        }
        if (habitacionesCumplen.isEmpty()) {
            throw new BusquedaException("No existe una habitacion para esos filtros");
        }
        return habitacionesCumplen;
    }
    public void crearReserva(ArrayList<Integer> idHabitacion, Date checkIn, Date checkOut, int idCliente,
            MedioDePago medioDePago) {
        ArrayList<Habitacion> habi = new ArrayList<>();
        for (int id : idHabitacion) {
            habi.add(habitaciones.get(id));
        }
        reservas.add(new Reserva(medioDePago, habi, checkIn, checkOut, buscarCliente(idCliente)));
    }
    public void cancelarReserva(int idReserva) throws ReservaException {
        Reserva r = buscarReserva(idReserva);
        if (r == null) {
            throw new ReservaException("La reserva no existe");
        } else
            r.cancelarReserva();
    }
    public void actualizarValores(ExtrasHabitacion extrasHabitacion, double nuevoValor) {
        Extra.getInstancia().actualizaValor(extrasHabitacion, nuevoValor);
    }
    public void verificarPagos() {
        for (Reserva r : reservas) {
            if(r.getEstadoReserva()==EstadoReserva.Pendiente){
                r.verificarVencimiento();
            }
        }
    }
    public HashMap generarReporte(Date inicio,Date fin){
        //Genera un diccionario con el estado de las habitaciones del hotel
        //si reporte devuelve true quiere decir que la habitacion esta libre en ese rango
        HashMap<Integer,String> repo=new HashMap<>();
        for(Habitacion h:habitaciones){
            if (h.reporte(inicio,fin)){
                repo.put(h.getId(),"Libre");
            }else {
                repo.put(h.getId(),"Reservada");
            }
        }
        return repo;
    }
    private Usuario buscarUsurio(int dni) {
        Usuario user = null;
        for (Usuario u : gerentes) {
            if (u.getDni() == dni) {
                user = u;
            }
        }
        if (user == null) {
            for (Usuario u : clientes) {
                if (u.getDni() == dni) {
                    user = u;
                }
            }
        }
        return user;
    }
    private Cliente buscarCliente(int dni) {
        for (Cliente c : clientes) {
            if (c.getDni() == dni) {
                return c;
            }
        }
        return null;
    }
    private Reserva buscarReserva(int idReserva) {
        for (Reserva r : reservas) {
            if (r.getId() == idReserva) {
                return r;
            }
        }
        return null;
    }

    //carga de datos
    private void inicio(){
        //registrar gerente
        registrarGerente(44655190,"joaquin","Morelli","12154844","j",TipoContacto.WhatsApp,"estoesboca");
        //registrar clientes
        registrarCliente(12345678, "Juan", "Pérez", "1234567890", "juan.perez@mail.com", TipoContacto.SMS, "contrasenia123");
        registrarCliente(87654321, "María", "Gómez", "0987654321", "maria.gomez@mail.com", TipoContacto.WhatsApp, "contrasenia456");
        registrarCliente(11223344, "Carlos", "López", "1122334455", "carlos.lopez@mail.com", TipoContacto.Mail, "contrasenia789");
        registrarCliente(44332211, "Ana", "Martínez", "2233445566", "ana.martinez@mail.com", TipoContacto.SMS, "contrasenia012");
        registrarCliente(55667788, "Luis", "Fernández", "3344556677", "luis.fernandez@mail.com", TipoContacto.WhatsApp, "contrasenia345");
        //se crea una instancia del habitacionBuilder y luego se le agregan las distintas habitaciones
        HabitacionBuilderImpl builder=new HabitacionBuilderImpl();
        //registra distintas habitaciones
        builder.conDespertador(); builder.conTv(); builder.cantidadDePersonas(4); builder.tipoDeHabitacion(TipoHabitacion.Estandar);
        habitaciones.add(builder.build());
        builder.conDespertador(); builder.conTv(); builder.cantidadDePersonas(2); builder.tipoDeHabitacion(TipoHabitacion.Estandar);
        habitaciones.add(builder.build());
        builder.conMiniBar(); builder.conTv(); builder.cantidadDePersonas(4); builder.tipoDeHabitacion(TipoHabitacion.Suite); habitaciones.add(builder.build());
        habitaciones.add(builder.build());
        builder.conDespertador(); builder.conInternet(); builder.conMiniBar(); builder.cantidadDePersonas(1); builder.tipoDeHabitacion(TipoHabitacion.Estandar);
        habitaciones.add(builder.build());
        builder.conTv(); builder.conMiniBar(); builder.cantidadDePersonas(3); builder.tipoDeHabitacion(TipoHabitacion.Suite);
        habitaciones.add(builder.build());
        builder.conInternet(); builder.conMiniBar(); builder.cantidadDePersonas(2); builder.tipoDeHabitacion(TipoHabitacion.Estandar);
        habitaciones.add(builder.build());
        builder.conDespertador(); builder.cantidadDePersonas(1); builder.tipoDeHabitacion(TipoHabitacion.Suite); habitaciones.add(builder.build());
        habitaciones.add(builder.build());
        builder.conTv(); builder.conInternet(); builder.conMiniBar(); builder.cantidadDePersonas(4); builder.tipoDeHabitacion(TipoHabitacion.Estandar);
        habitaciones.add(builder.build());
        builder.conDespertador(); builder.conTv(); builder.cantidadDePersonas(2); builder.tipoDeHabitacion(TipoHabitacion.Suite);
        habitaciones.add(builder.build());
        builder.conInternet(); builder.cantidadDePersonas(3); builder.tipoDeHabitacion(TipoHabitacion.Estandar);
        habitaciones.add(builder.build());
        builder.conDespertador(); builder.conTv(); builder.conInternet(); builder.conMiniBar(); builder.cantidadDePersonas(2); builder.tipoDeHabitacion(TipoHabitacion.Suite);
        habitaciones.add(builder.build());
    }
}

