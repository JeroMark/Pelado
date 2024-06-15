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
            habi.add(buscarHabitacion(id));
        }
        reservas.add(new Reserva(medioDePago, habi, checkIn, checkOut, buscarCliente(idCliente)));
    }
    public void cancelarReserva(int idReserva) throws ReservaException {
        Reserva r = buscarReserva(idReserva);
        if (r == null) {
            throw new ReservaException("La reserva no existe");
        } else if(r.getEstadoReserva()==EstadoReserva.Pendiente){
                r.cancelarReserva();
        }else throw new ReservaException("La reserva no puede ser cancelada");
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
    public void generarReporte(Date inicio,Date fin){
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
       imprimirDiccio(repo);
    }
    public void mostrarReservas(){
        for(Reserva r:reservas){
            System.out.println(r.toView().toString());
        }
    }
    public void PagarReserva(int id){
       Reserva r= buscarReserva(id);
       r.pagada();
    }
    public void mostrarCliente(int id){
        Cliente c=buscarCliente(id);
        System.out.println(c.clienteToView().toString());
    }
    private void imprimirDiccio(HashMap diccio){
        diccio.forEach((clave, valor) -> System.out.println("idHabitacion: " + clave + ", estado: " + valor));
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
        //habitacion 1
        builder.conDespertador(); builder.conTv(); builder.cantidadDePersonas(4); builder.tipoDeHabitacion(TipoHabitacion.Estandar);
        habitaciones.add(builder.build());
        //habitacion 2
        builder.conDespertador(); builder.conTv(); builder.cantidadDePersonas(2); builder.tipoDeHabitacion(TipoHabitacion.Estandar);
        habitaciones.add(builder.build());
        //habitacion 3
        builder.conMiniBar(); builder.conTv(); builder.cantidadDePersonas(4); builder.tipoDeHabitacion(TipoHabitacion.Suite);
        habitaciones.add(builder.build());
        //habitacion 4
        builder.conDespertador(); builder.conInternet(); builder.conMiniBar(); builder.cantidadDePersonas(1); builder.tipoDeHabitacion(TipoHabitacion.Estandar);
        habitaciones.add(builder.build());
        //habitacion 5
        builder.conTv(); builder.conMiniBar(); builder.cantidadDePersonas(3); builder.tipoDeHabitacion(TipoHabitacion.Suite);
        habitaciones.add(builder.build());
        //habitacion 6
        builder.conInternet(); builder.conMiniBar(); builder.cantidadDePersonas(2); builder.tipoDeHabitacion(TipoHabitacion.Estandar);
        habitaciones.add(builder.build());
        //habitacion 7
        builder.conDespertador(); builder.cantidadDePersonas(1); builder.tipoDeHabitacion(TipoHabitacion.Suite);
        habitaciones.add(builder.build());
        //habitacion 8
        builder.conTv(); builder.conInternet(); builder.conMiniBar(); builder.cantidadDePersonas(4); builder.tipoDeHabitacion(TipoHabitacion.Estandar);
        habitaciones.add(builder.build());
        //habitacion 9
        builder.conDespertador(); builder.conTv(); builder.cantidadDePersonas(2); builder.tipoDeHabitacion(TipoHabitacion.Suite);
        habitaciones.add(builder.build());
        //habitacion 10
        builder.conInternet(); builder.cantidadDePersonas(3); builder.tipoDeHabitacion(TipoHabitacion.Estandar);
        habitaciones.add(builder.build());
        //habitacion 11
        builder.conDespertador(); builder.conTv(); builder.conInternet(); builder.conMiniBar(); builder.cantidadDePersonas(2); builder.tipoDeHabitacion(TipoHabitacion.Suite);
        habitaciones.add(builder.build());
        //creacion de reservas
        //1reserva
        ArrayList<Integer> habi=new ArrayList();
        habi.add(1);
        habi.add(2);
        Date inicio = new Date(124, 6, 14);
        Date fin = new Date(124, 6, 24);
        crearReserva(habi,inicio,fin,55667788,MedioDePago.Efectivo);
        //2reserva
        habi.clear();
        habi.add(6);
        habi.add(9);
        Date inicio2 = new Date(124, 10, 20);
        Date fin2 = new Date(124, 11, 02);
        crearReserva(habi,inicio2,fin2,12345678,MedioDePago.TarjetaCredito);
        //3reserva
        habi.clear();
        habi.add(10);
        habi.add(9);
        Date inicio3 = new Date(124, 11, 03);
        Date fin3 = new Date(124, 11, 20);
        crearReserva(habi,inicio3,fin3,87654321,MedioDePago.Transferencia);
        //4reserva
        habi.clear();
        habi.add(5);
        Date inicio4 = new Date(122, 9, 30);
        Date fin4 = new Date(122, 10, 11);
        crearReserva(habi,inicio4,fin4,87654321,MedioDePago.TarjetaDebito);
        //5reserva
        habi.clear();
        habi.add(11);
        Date inicio5 = new Date(123, 9, 30);
        Date fin5 = new Date(123, 10, 11);
        crearReserva(habi,inicio5,fin5,55667788,MedioDePago.TarjetaDebito);
    }
}

