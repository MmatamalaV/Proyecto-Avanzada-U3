/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto3.Programa;

/**
 *
 * @author mario
 */
public class Insumo
{
    private String codigo;
    private String insumo;
    private int stockInicial;
    private int stockMinimo;
    private String descripcion;

    public Insumo(String codigo, String insumo, int stockInicial, int stockMinimo, String descripcion)
    {
        this.codigo = codigo;
        this.insumo = insumo;
        this.stockInicial = stockInicial;
        this.stockMinimo = stockMinimo;
        this.descripcion = descripcion;
    }

    public String getCodigo()
    {
        return codigo;
    }

    public void setCodigo(String codigo)
    {
        this.codigo = codigo;
    }

    public String getInsumo()
    {
        return insumo;
    }

    public void setInsumo(String insumo)
    {
        this.insumo = insumo;
    }

    public int getStockInicial()
    {
        return stockInicial;
    }

    public void setStockInicial(int stockInicial)
    {
        this.stockInicial = stockInicial;
    }

    public int getStockMinimo()
    {
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo)
    {
        this.stockMinimo = stockMinimo;
    }

    public String getDescripcion()
    {
        return descripcion;
    }

    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }
}
