package test;

import Controller.Controlador;
import Controller.Exception.BusquedaException;
import Controller.Exception.LogginException;
import Model.Enum.TipoHabitacion;
import Model.Filtro;
import View.HabitacionView;

import java.util.ArrayList;

public class main {
    public static void main (String[] args){
        try {
        Controlador.getInstancia().iniciarSesion(44655190,"estoesboca");
        }catch (LogginException e){
            System.out.println(e.getMessage());
        }
        Filtro filtro=new Filtro();
        filtro.setTv(); filtro.setDespertador(); filtro.setTipoDeHabitacion(TipoHabitacion.Estandar); filtro.setCantidadDePersonas(4);
        try {
           ArrayList<HabitacionView> cumplen= Controlador.getInstancia().buscarHabitacion(filtro);
           for (HabitacionView habitacion:cumplen){
               System.out.println(habitacion.toString());
           }
        }catch (BusquedaException e){
            e.getMessage();
        }
    }
}
