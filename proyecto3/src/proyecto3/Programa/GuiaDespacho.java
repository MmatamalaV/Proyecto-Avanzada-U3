/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto3.Programa;

/**
 *
 * @author Luis
 */
public class GuiaDespacho
{
    String numGuia;
    String rut;
    String fecha;
    String proveedor;

    public GuiaDespacho(String numGuia, String rut, String fecha, String proveedor)
    {
        this.numGuia = numGuia;
        this.rut = rut;
        this.fecha = fecha;
        this.proveedor = proveedor;
    }

    public String getNumGuia()
    {
        return numGuia;
    }

    public void setNumGuia(String numGuia)
    {
        this.numGuia = numGuia;
    }

    public String getRut()
    {
        return rut;
    }

    public void setRut(String rut)
    {
        this.rut = rut;
    }

    public String getFecha()
    {
        return fecha;
    }

    public void setFecha(String fecha)
    {
        this.fecha = fecha;
    }

    public String getProveedor()
    {
        return proveedor;
    }

    public void setProveedor(String proveedor)
    {
        this.proveedor = proveedor;
    }
    
    
}
