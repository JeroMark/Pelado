package test;

import Controller.Controlador;
import Controller.Exception.BusquedaException;
import Controller.Exception.LogginException;
import Model.Enum.TipoHabitacion;
import Model.Filtro;
import View.HabitacionView;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class main {
    public static void main (String[] args){
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
        Controlador.getInstancia().generarReporte(new Date (2023,10,11),new Date(2024,3,1));


    }
}
