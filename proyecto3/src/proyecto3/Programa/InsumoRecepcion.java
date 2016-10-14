/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto3.Programa;

import java.io.Serializable;
/**
 *
 * @author mario
 */
public class InsumoRecepcion implements Serializable
{
    String codigoInsumo;
    String insumo;
    int cantidadEntregada;
    int valorUnitario;

    public InsumoRecepcion(String codigoInsumo, String insumo, int cantidadEntregada, int valorUnitario)
    {
        this.codigoInsumo = codigoInsumo;
        this.insumo = insumo;
        this.cantidadEntregada = cantidadEntregada;
        this.valorUnitario = valorUnitario;
    }

    public String getCodigoInsumo()
    {
        return codigoInsumo;
    }

    public void setCodigoInsumo(String codigoInsumo)
    {
        this.codigoInsumo = codigoInsumo;
    }

    public String getInsumo()
    {
        return insumo;
    }

    public void setInsumo(String insumo)
    {
        this.insumo = insumo;
    }

    public int getCantidadEntregada()
    {
        return cantidadEntregada;
    }

    public void setCantidadEntregada(int cantidadEntregada)
    {
        this.cantidadEntregada = cantidadEntregada;
    }

    public int getValorUnitario()
    {
        return valorUnitario;
    }

    public void setValorUnitario(int valorUnitario)
    {
        this.valorUnitario = valorUnitario;
    }
    
    
    
}
