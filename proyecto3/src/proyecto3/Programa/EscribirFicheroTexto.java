/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto3.Programa;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author mario
 */
public class EscribirFicheroTexto
{

    Calendar calendario = new GregorianCalendar();
    int hora =calendario.get(Calendar.HOUR_OF_DAY);
    int minutos = calendario.get(Calendar.MINUTE);
    int segundos = calendario.get(Calendar.SECOND);
    
    public EscribirFicheroTexto()
    {
    }
    
    void guardar(String rut,String nombre,String fecha,ArrayList<InsumoEntrega> insumo)
    {
        try
        {
        PrintWriter writer = new PrintWriter( new FileWriter(rut+" "+fecha+" "+hora+" "+minutos+" "+segundos+".txt") );
//        String sFichero =rut+" "+fecha+".txt"; 
//        File fichero = new File(sFichero);
        writer.println("rut:"+rut); 
        writer.println("nombre:"+nombre); 
        writer.println("fecha:"+fecha); 
       
            for (InsumoEntrega insumoito : insumo)
            {
                writer.println("");
                writer.println("codigo:"+insumoito.getCodigo());
                writer.println("nombre:"+insumoito.getNombre());
                writer.println("cantidad:"+insumoito.getCantidad());
            }
        writer.close();
        } 
        catch (IOException ex)
        {
            System.out.println("error");
        }
    }
    
    void guardarGuiaRecepcion(String rut,String numGuiaReturn,String fecha,String proveedor,ArrayList<InsumoRecepcion> insumo)
    {
        try
        {
        
        PrintWriter writer = new PrintWriter( new FileWriter(rut+" "+fecha+" "+hora+" "+minutos+" "+segundos+".txt") );
        
        writer.println("Rut:"+rut); 
        writer.println("Numero Guia:"+numGuiaReturn); 
        writer.println("fecha:"+fecha); 
        writer.println("proveedor:"+proveedor); 
       
            for (InsumoRecepcion insumoito : insumo)
            {
                writer.println("");
                writer.println("Codigo:"+insumoito.getCodigoInsumo());
                writer.println("Insumo:"+insumoito.getInsumo());
                writer.println("Cantidad Entregada:"+insumoito.getCantidadEntregada());
                writer.println("Valor Unitario:"+insumoito.getValorUnitario());
            }
        writer.close();
        } 
        catch (IOException ex)
        {
            System.out.println("error");
        }
    }
}
