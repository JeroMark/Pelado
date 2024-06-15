package test;

import Controller.Controlador;
import Controller.Exception.BusquedaException;
import Controller.Exception.LogginException;
import Controller.Exception.ReservaException;
import Controller.Exception.SinPermisoException;
import Model.Enum.ExtrasHabitacion;
import Model.Enum.MedioDePago;
import Model.Enum.TipoContacto;
import Model.Enum.TipoHabitacion;
import Model.Filtro;
import View.HabitacionView;
import java.util.ArrayList;
import java.util.Date;

public class main {
    public static void main (String[] args){
        //Los meses es mas 1 años -1900

       cancerlaReserva();



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
    private static void sinPermiso(){
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
    private static void buscarHabitacionConDeterminadoFiltroYReservar(){
        //busca una habitaccion con tv, despertador, suite y de dos personas
        //desde el 21/12/24 hasta el 1/1/25
        Controlador.getInstancia().mostrarReservas();
        try {
            Controlador.getInstancia().iniciarSesion(55667788,"contrasenia345");
        }catch (LogginException e){
            System.out.println(e.getMessage());
        }
        Filtro filtro=new Filtro();
        filtro.setTv(); filtro.setDespertador(); filtro.setTipoDeHabitacion(TipoHabitacion.Suite); filtro.setCantidadDePersonas(2);filtro.setCheckIn(new Date(124, 11, 21));
        filtro.setCheckOut(new Date(125,0,1));
        try {
            ArrayList<HabitacionView> cumplen= Controlador.getInstancia().buscarHabitacion(filtro);
            for (HabitacionView habitacion:cumplen){
                System.out.println("------------------Cumple---------------");
                System.out.println(habitacion.toString());
            }
        }catch (BusquedaException e){
            e.getMessage();
        }
        ArrayList<Integer> hab=new ArrayList<>(); hab.add(9);
        System.out.println("Crea la reserva");
        Controlador.getInstancia().crearReserva(hab,new Date(124, 11, 21),new Date(125,0,1),
                55667788, MedioDePago.Efectivo);
        Controlador.getInstancia().mostrarReservas();
        Controlador.getInstancia().PagarReserva(6);
    }
    private static void FiltroSinResultado(){
        Filtro filtro=new Filtro();
        filtro.setDespertador();
        filtro.setTv();
        filtro.setCantidadDePersonas(4);
        filtro.setTipoDeHabitacion(TipoHabitacion.Estandar);
        filtro.setCheckIn(new Date(124, 6, 24));
        filtro.setCheckOut(new Date(124, 6, 30));
        try {
            ArrayList<HabitacionView> cumplen= Controlador.getInstancia().buscarHabitacion(filtro);
            for (HabitacionView habitacion:cumplen){
                System.out.println("------------------Cumple---------------");
                System.out.println(habitacion.toString());
            }
        }catch (BusquedaException e){
            System.out.println(e.getMessage());
        }

    }
    private static void cancerlaReserva(){
        Controlador.getInstancia().mostrarReservas();
        try {
            Controlador.getInstancia().cancelarReserva(5);
        }catch (ReservaException e){
           System.out.println(e.getMessage());
        }
        Controlador.getInstancia().mostrarReservas();
    }
    private static void nuevoCliente(){
        Controlador.getInstancia().registrarCliente(45487245,"Jero","Bianchi","11545487",
                "estoEsBoca123", TipoContacto.Mail,"labombonera");
        Controlador.getInstancia().mostrarCliente(45487245);
    }

}
