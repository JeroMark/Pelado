package Controller;

import Model.*;
import Model.Enum.*;
import Model.Exception.BusquedaException;
import Model.Exception.LogginException;
import Model.Exception.ReservaException;
import Model.Exception.SinPermisoException;
import View.HabitacionView;

import java.util.ArrayList;
import java.util.Date;

public class Controlador {
    private static Controlador instancia;
    private ArrayList<Gerente> gerentes;
    private ArrayList<Cliente> clientes;
    private ArrayList<Reserva> reservas;
    private ArrayList<Habitacion> habitaciones;
    private ArrayList<Filtro> filtros;
    private static Usuario user;

    private Controlador() {
        gerentes = new ArrayList<>();
        clientes = new ArrayList<>();
        reservas = new ArrayList<>();
        habitaciones = new ArrayList<>();
        filtros = new ArrayList<>();
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

    public void generarPagos() {

    }

    public void verificarPagos() {
        for (Reserva r : reservas) {
            r.dias();
        }
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
}
