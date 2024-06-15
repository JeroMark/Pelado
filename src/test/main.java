package test;

import Controller.Controlador;
import Controller.Exception.BusquedaException;
import Controller.Exception.LogginException;
import Controller.Exception.SinPermisoException;
import Model.Enum.ExtrasHabitacion;
import Model.Enum.MedioDePago;
import Model.Enum.TipoHabitacion;
import Model.Filtro;
import View.HabitacionView;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class main {
    public static void main (String[] args){
        //Los meses es mas 1


    }







    private static void reporte(){
        Controlador.getInstancia().generarReporte(new Date (2023,10,11),new Date(2024,3,1));
    }
    private static void loggeoIncorrcto(){
        try {
            Controlador.getInstancia().iniciarSesion(44655190,"contrasenia345");
        }catch (LogginException e){
            System.out.println(e.getMessage());
        }
    }
    public static void sinPermiso(){
        try {
            Controlador.getInstancia().iniciarSesion(55667788,"contrasenia345");
        }catch (LogginException e){
            System.out.println(e.getMessage());
        }
        try {
            Controlador.getInstancia().actualizarValores(ExtrasHabitacion.Despertador,3);
        }catch (SinPermisoException e){
            System.out.println (e.getMessage());
        }
        //el que esta loggeado es un cliente, por lo tanto no puede hacer este tipo de cambio
    }
    public static void buscarHabitacionConDeterminadoFiltroYReservar(){
        //busca una habitaccion con tv, despertador, suite y de dos personas
        //desde el 21/12/24 hasta el 1/1/25
        //mostrar todas las reservas
        try {
            Controlador.getInstancia().iniciarSesion(55667788,"contrasenia345");
        }catch (LogginException e){
            System.out.println(e.getMessage());
        }
        Filtro filtro=new Filtro();
        filtro.setTv(); filtro.setDespertador(); filtro.setTipoDeHabitacion(TipoHabitacion.Suite); filtro.setCantidadDePersonas(2);filtro.setCheckIn(new Date(2024, 11, 21));
        filtro.setCheckOut(new Date(2025,0,1));
        try {
            ArrayList<HabitacionView> cumplen= Controlador.getInstancia().buscarHabitacion(filtro);
            for (HabitacionView habitacion:cumplen){
                System.out.println(habitacion.toString());
            }
        }catch (BusquedaException e){
            e.getMessage();
        }
        ArrayList<Integer> hab=new ArrayList<>(); hab.add(9);
        Controlador.getInstancia().crearReserva(hab,new Date(2024, 11, 21),new Date(2025,0,1),
                55667788, MedioDePago.Efectivo);
        //mostrar reservar
    }
    //hacer uno donde no encuentre una habitacion para los filtros
    //registrar habitacion--mostrar
    //cancelar una reserva mostrar todas antes y despues de cancelar
    //registrar chupapija
}
